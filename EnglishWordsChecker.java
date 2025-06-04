import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/*
 * ID: 20230201
 * Name: Abdelrahman Ahmed Abdelbaky Mahmoued
 * Section: S3
 */

public class EnglishWordsChecker {  

    /**
    * Displays the program menu and prompts the user for input.
    * Ensures that the user enters at least 2 letters.
    * @return The validated string of letters entered by the user.
    */
    public static String menu(){
        System.out.println("******FCAI*******");
        System.out.println("This is a word checker program");
        System.out.println();
    
        Scanner input = new Scanner(System.in); // Creating object scanner
        System.out.print("enter random letters: "); 
        String letters = input.nextLine(); 

        while(letters.length() < 2) {
            System.out.println("There isn't an English Word that is smaller than 2 letters");
            letters = input.nextLine();
        }
        input.close();
        return letters;
    }

    public static void main(String[] args){
        // Get input form the user
        String letters = menu();
        List<String> WORDS = new ArrayList<>();
        List<String> permutations = getPermutations(letters);

        // Load dictionary only once instead of every is_valid() call
        List<String> validWords = loadDictionary("20k_filtered_sorted.txt");

        // Check if permutation is a valid word
        for (String perm : permutations) {
            if (is_valid(perm, validWords) && !WORDS.contains(perm)) {  // Avoid duplicates
                WORDS.add(perm);
            }
        }

        // Displays results
        if (WORDS.isEmpty()) {
            System.out.println("No valid words found");
        } else {
            System.out.println("Valid words found are: ");
            for(String word : WORDS){
                System.out.println(word);
            }
        }
    }

    /**
     * Checks if a given word exists in the word list.
     * @param word The word to validate.
     * @param dictionary The loaded dictionary list.
     * @return true if the word exists in the dictionary file, false otherwise.
     */
    public static boolean is_valid(String word, List<String> dictionary){
        return Collections.binarySearch(dictionary, word) >= 0;
    }

    /**
     * Loads words from a file and returns them as a sorted list for binary search.
     * @param filename The path to the dictionary file.
     * @return A sorted list of valid English words.
     */
    public static List<String> loadDictionary(String filename) {
        List<String> wordList = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(filename));
            while (reader.hasNextLine()) {
                wordList.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dictionary file not found.");
            e.printStackTrace();
        }

        // Sorting for the binary search 
        Collections.sort(wordList); 
        return wordList;
    }

    /**
     * Returns list of all possible combinations of the letters.
     * @param str The input string.
     * @return A list of all possible letter combinations.
     */
    public static List<String> getPermutations(String str) {
        Set<String> result = new HashSet<>();
        All_possible_combinations(str, "", result);
        return new ArrayList<>(result);
    }

    /**
     * Generating all possible combinations for the inputted letters using recursion.
     * @param str Remaining letters.
     * @param current The current word being formed.
     * @param result The result set to store all unique combinations.
     */
    private static void All_possible_combinations(String str, String current, Set<String> result) {
        if (!current.isEmpty()) { // Avoid adding an empty subset
            result.add(current);
        }

        for (int i = 0; i < str.length(); i++) {
            String remaining = str.substring(0, i) + str.substring(i + 1);
            All_possible_combinations(remaining, current + str.charAt(i), result);
        }
    }
}
