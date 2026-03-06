package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import java.util.ArrayList;
import java.util.List;
import com.example.vb_battle_server.Character;

@SpringBootApplication
@PropertySource(value = {"file:./secrets.properties", "file:./config/secrets.properties"}, ignoreResourceNotFound = true)
public class RestServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(RestServiceApplication.class);

    public static final int MAX_ROSTER_PER_STAGE = 100;
    public static final int MAX_PER_USER_PER_STAGE = 3;

    /** Roster of Digimon offered as opponents per stage (RosterEntry = character + timestamp). Lookup still uses *Stats arrays. */
    public static final ArrayList<RosterEntry> rookieRoster = new ArrayList<>();
    public static final ArrayList<RosterEntry> championRoster = new ArrayList<>();
    public static final ArrayList<RosterEntry> ultimateRoster = new ArrayList<>();
    public static final ArrayList<RosterEntry> megaRoster = new ArrayList<>();

    /** Initial 10 charaIds per stage to populate roster on first boot (until load/save from file). */
    private static final List<String> ROOKIE_INITIAL_CHARA_IDS = List.of(
        "dim020_mon03", "dim012_mon03", "dim013_mon03", "dim007_mon03", "dim017_mon03",
        "dim021_mon03", "dim018_mon03", "dim000_mon03", "dim023_mon03", "dim034_mon04");
    private static final List<String> CHAMPION_INITIAL_CHARA_IDS = List.of(
        "dim020_mon04", "dim012_mon04", "dim013_mon04", "dim007_mon04", "dim017_mon06",
        "dim021_mon04", "dim018_mon04", "dim000_mon04", "dim023_mon04", "dim034_mon06");
    private static final List<String> ULTIMATE_INITIAL_CHARA_IDS = List.of(
        "dim011_mon08", "dim005_mon13", "dim030_mon09", "dim007_mon08", "dim017_mon12",
        "dim021_mon05", "dim018_mon08", "dim000_mon08", "dim023_mon08", "dim034_mon10");
    private static final List<String> MEGA_INITIAL_CHARA_IDS = List.of(
        "dim020_mon07", "dim012_mon14", "dim013_mon14", "dim007_mon16", "dim017_mon14",
        "dim021_mon06", "dim018_mon14", "dim000_mon14", "dim023_mon12", "dim034_mon15");

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    /** Runs after the application context is up so catalog and rosters are ready before any requests. */
    @Bean
    public ApplicationRunner catalogAndRosterInitializer() {
        return (ApplicationArguments args) -> {
            addAllCharactersFromClass(VBRookieStats.class, VBRookieStats.rookieArray);
            addAllCharactersFromClass(VBChampionStats.class, VBChampionStats.championArray);
            addAllCharactersFromClass(VBUltimateStats.class, VBUltimateStats.ultimateArray);
            addAllCharactersFromClass(VBMegaStats.class, VBMegaStats.megaArray);
            initRosters();
            System.out.println("Loaded " + VBRookieStats.rookieArray.size() + " Rookie characters");
            System.out.println("Loaded " + VBChampionStats.championArray.size() + " Champion characters");
            System.out.println("Loaded " + VBUltimateStats.ultimateArray.size() + " Ultimate characters");
            System.out.println("Loaded " + VBMegaStats.megaArray.size() + " Mega characters");
            System.out.println("Roster sizes: Rookie=" + rookieRoster.size() + ", Champion=" + championRoster.size()
                + ", Ultimate=" + ultimateRoster.size() + ", Mega=" + megaRoster.size());
        };
    }

    private static void initRosters() {
        for (String charaId : ROOKIE_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBRookieStats.rookieArray, charaId);
            if (c != null) rookieRoster.add(new RosterEntry(c, 0L));
        }
        for (String charaId : CHAMPION_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBChampionStats.championArray, charaId);
            if (c != null) championRoster.add(new RosterEntry(c, 0L));
        }
        for (String charaId : ULTIMATE_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBUltimateStats.ultimateArray, charaId);
            if (c != null) ultimateRoster.add(new RosterEntry(c, 0L));
        }
        for (String charaId : MEGA_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBMegaStats.megaArray, charaId);
            if (c != null) megaRoster.add(new RosterEntry(c, 0L));
        }
    }

    /** Returns the roster list for the given stage (0=rookie, 1=champion, 2=ultimate, 3=mega). */
    public static ArrayList<RosterEntry> getRosterForStage(int stage) {
        return switch (stage) {
            case 0 -> rookieRoster;
            case 1 -> championRoster;
            case 2 -> ultimateRoster;
            case 3 -> megaRoster;
            default -> new ArrayList<>();
        };
    }

    /**
     * Adds a winner (copy with owner set) to the roster for its stage.
     * userId: stable id for roster limits (max 3 per user, no duplicate charaId per user).
     * displayName: human-readable name for client (ownerUsername / displayName in API).
     */
    public static void addWinnerToRoster(Character winnerCopy, String userId, String displayName) {
        final String uid = (userId != null) ? userId : "";
        final String label = (displayName != null && !displayName.isEmpty()) ? displayName : uid;
        int stage = winnerCopy.getStage();
        if (stage < 0 || stage > 3) {
            logger.warn("addWinnerToRoster: skipped invalid stage={} for winner={}", stage, winnerCopy.getName());
            return;
        }
        ArrayList<RosterEntry> roster = getRosterForStage(stage);
        String stageName = switch (stage) { case 0 -> "rookie"; case 1 -> "champion"; case 2 -> "ultimate"; case 3 -> "mega"; default -> "?"; };
        String winnerCharaId = winnerCopy.getCharaId();

        winnerCopy.setOwnerUserId(uid);
        winnerCopy.setOwnerUsername(label);

        synchronized (roster) {
            int sizeBefore = roster.size();
            // Remove any existing entry for this user with the same charaId (no duplicate same-Digimon per user)
            if (!uid.isEmpty()) {
                roster.removeIf(e -> {
                    String ownerId = e.character().getOwnerUserId();
                    return ownerId != null && ownerId.equals(uid) && winnerCharaId.equals(e.character().getCharaId());
                });
            }
            // Count this user's entries and find their oldest
            RosterEntry userOldest = null;
            int userCount = 0;
            for (RosterEntry e : roster) {
                String ownerId = e.character().getOwnerUserId();
                if (ownerId != null && ownerId.equals(uid)) {
                    userCount++;
                    if (userOldest == null || e.addedAt() < userOldest.addedAt()) {
                        userOldest = e;
                    }
                }
            }

            // If user already has 3, remove their oldest (we will add the new one)
            if (userCount >= MAX_PER_USER_PER_STAGE && userOldest != null) {
                roster.remove(userOldest);
                logger.info("addWinnerToRoster: removed user's oldest (user had {}), stage={}, winner={}, user={}", userCount, stageName, winnerCopy.getName(), label);
            } else if (roster.size() >= MAX_ROSTER_PER_STAGE) {
                // At capacity: remove globally oldest
                RosterEntry oldest = roster.get(0);
                for (RosterEntry e : roster) {
                    if (e.addedAt() < oldest.addedAt()) oldest = e;
                }
                roster.remove(oldest);
                logger.info("addWinnerToRoster: evicted globally oldest (roster full), stage={}, winner={}", stageName, winnerCopy.getName());
            }

            roster.add(new RosterEntry(winnerCopy, System.currentTimeMillis()));
            logger.info("addWinnerToRoster: added winner={}, stage={}, displayName={}, rosterSize before={} after={}", winnerCopy.getName(), stageName, uid.isEmpty() ? "(no user)" : label, sizeBefore, roster.size());
        }
    }

    private static Character findCharacterByCharaId(ArrayList<Character> catalog, String charaId) {
        for (Character c : catalog) {
            if (charaId.equals(c.getCharaId())) return c;
        }
        return null;
    }
    
    // Helper method to add all Character fields from a stats class to an array
    private static void addAllCharactersFromClass(Class<?> statsClass, ArrayList<Character> targetArray) {
        try {
            java.lang.reflect.Field[] fields = statsClass.getDeclaredFields();
            int characterCount = 0;
            for (java.lang.reflect.Field field : fields) {
                if (field.getType() == Character.class && java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    Character character = (Character) field.get(null);
                    if (character != null) {
                        targetArray.add(character);
                        characterCount++;
                    }
                }
            }
            System.out.println("Added " + characterCount + " characters from " + statsClass.getSimpleName());
        } catch (IllegalAccessException e) {
            // Handle exception - could log or throw runtime exception
            System.err.println("Error accessing fields from " + statsClass.getSimpleName() + ": " + e.getMessage());
        }
    }

}