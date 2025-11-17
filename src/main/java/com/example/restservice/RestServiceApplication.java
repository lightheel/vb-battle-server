package com.example.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import java.util.ArrayList;
import com.example.vb_battle_server.Character;

@SpringBootApplication
@PropertySource(value = {"file:./secrets.properties", "file:./config/secrets.properties"}, ignoreResourceNotFound = true)
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);

        //Setup server references for Digi stats - automatically add all characters using reflection
        addAllCharactersFromClass(VBRookieStats.class, VBRookieStats.rookieArray);
        addAllCharactersFromClass(VBChampionStats.class, VBChampionStats.championArray);
        addAllCharactersFromClass(VBUltimateStats.class, VBUltimateStats.ultimateArray);
        addAllCharactersFromClass(VBMegaStats.class, VBMegaStats.megaArray);
        
        // Debug output to verify character counts
        System.out.println("Loaded " + VBRookieStats.rookieArray.size() + " Rookie characters");
        System.out.println("Loaded " + VBChampionStats.championArray.size() + " Champion characters");
        System.out.println("Loaded " + VBUltimateStats.ultimateArray.size() + " Ultimate characters");
        System.out.println("Loaded " + VBMegaStats.megaArray.size() + " Mega characters");
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