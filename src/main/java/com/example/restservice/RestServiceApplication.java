package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import java.util.ArrayList;
import java.util.List;
import com.example.vb_battle_server.Character;

@SpringBootApplication
@PropertySource(value = {"file:./secrets.properties", "file:./config/secrets.properties"}, ignoreResourceNotFound = true)
public class RestServiceApplication {

    /** Roster of Digimon offered as opponents per stage (subset of full catalog). Lookup still uses *Stats arrays. */
    public static final ArrayList<Character> rookieRoster = new ArrayList<>();
    public static final ArrayList<Character> championRoster = new ArrayList<>();
    public static final ArrayList<Character> ultimateRoster = new ArrayList<>();
    public static final ArrayList<Character> megaRoster = new ArrayList<>();

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

        //Setup server references for Digi stats - automatically add all characters using reflection
        addAllCharactersFromClass(VBRookieStats.class, VBRookieStats.rookieArray);
        addAllCharactersFromClass(VBChampionStats.class, VBChampionStats.championArray);
        addAllCharactersFromClass(VBUltimateStats.class, VBUltimateStats.ultimateArray);
        addAllCharactersFromClass(VBMegaStats.class, VBMegaStats.megaArray);

        // Populate opponent rosters from initial charaIds (references into catalog; no copies)
        initRosters();

        // Debug output to verify character counts
        System.out.println("Loaded " + VBRookieStats.rookieArray.size() + " Rookie characters");
        System.out.println("Loaded " + VBChampionStats.championArray.size() + " Champion characters");
        System.out.println("Loaded " + VBUltimateStats.ultimateArray.size() + " Ultimate characters");
        System.out.println("Loaded " + VBMegaStats.megaArray.size() + " Mega characters");
        System.out.println("Roster sizes: Rookie=" + rookieRoster.size() + ", Champion=" + championRoster.size()
            + ", Ultimate=" + ultimateRoster.size() + ", Mega=" + megaRoster.size());
    }

    private static void initRosters() {
        for (String charaId : ROOKIE_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBRookieStats.rookieArray, charaId);
            if (c != null) rookieRoster.add(c);
        }
        for (String charaId : CHAMPION_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBChampionStats.championArray, charaId);
            if (c != null) championRoster.add(c);
        }
        for (String charaId : ULTIMATE_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBUltimateStats.ultimateArray, charaId);
            if (c != null) ultimateRoster.add(c);
        }
        for (String charaId : MEGA_INITIAL_CHARA_IDS) {
            Character c = findCharacterByCharaId(VBMegaStats.megaArray, charaId);
            if (c != null) megaRoster.add(c);
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