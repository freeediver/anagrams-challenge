import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AnagramChecker {

    private static Map<String, Set<String>> recordAnagrams = new HashMap<>();

    //A) function which check if two string are anagrams or not
    public static boolean isAnagram(String string1, String string2) {
        if (string1.length() != string2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        
        // Count occurrences of characters in string1
        for (int i = 0; i < string1.length(); i++) {
            char c = string1.charAt(i);
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        
        // Count occurrences of characters in string2
        for (int i = 0; i < string2.length(); i++) {
            char c = string2.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        
        return map1.equals(map2);
    } 

    // accept only letters inside input data and convert each of them to lower case, accordingly all no letters characters are ignored 
    public static String processInput(String input) {
        return input.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    // check if the parameter << input >> is an anagram of an existing key of the <recordAnagrams> Map. Otherwise let's add it as a new entry/key if doesn't match any previous text.  
    public static void addOrUpdateRecord(String input) {
        for (String key : recordAnagrams.keySet()) {
            boolean b =isAnagram(key, input);
            if (b) {
                recordAnagrams.get(key).add(input);
                return;
            }
        }
        recordAnagrams.put(input, new HashSet<>());
    }

    //B) get the anagrams of a given <<input>> based on the previous dataset simulation 
    public static Set<String> getAnagrams(String input){
        for (String key : recordAnagrams.keySet()) {
            if (input.equals(key)) {
                return recordAnagrams.get(key);
            }else if(recordAnagrams.get(key).contains(processInput(input))){
                Set<String> anagrams= new HashSet<>();
                anagrams.add(key);
                anagrams.addAll(recordAnagrams.get(key));
                anagrams.remove(processInput(input));
                return anagrams;
            }
        }
         return  new HashSet<>();
    }

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        System.out.println("##### Anagram Challenge #####");

        while (true) {
            System.out.println("\nChoose one of the following three options:");
            System.out.println("1/ Check if two texts are anagrams");
            System.out.println("2/ Retrieve anagrams of a given string");
            System.out.println("3/ Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.println("Enter the first text:");
                    String text1 = scanner.nextLine();
                    System.out.println("Enter the second text:");
                    String text2 = scanner.nextLine();
                    addOrUpdateRecord(processInput(text1));
                    addOrUpdateRecord(processInput(text2));
                    if (isAnagram(processInput(text1), processInput(text2))) {
                        System.out.println("OK! These texts are anagrams of each other");
                    } else {
                        System.out.println("KO! These texts are not anagrams of each other");
                    }
                    break;
                case 2:
                    System.out.println("Enter your input:");
                    String input = scanner.nextLine();
                    Set<String> anagrams = getAnagrams(processInput(input));
                    System.out.println("The anagrams for '" + input + "' : " + anagrams);
                    break;
                case 3:
                    System.out.println("Thank you and see you again!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid ! Please choose between one of these options 1, 2 or 3.");
            }
        }
    }
}

