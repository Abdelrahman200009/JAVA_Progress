import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;

/*
 * ID: 20230201
 * Name:Abdelrahman Ahmed Abdelbaky Mahmoued
 * Section:S3
 */


public class EnglishWordsChecker{  

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
            System.out.println("There isn't an English letter that is smaller than 2 letters");
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
        
        // Check if permutaion is a vaild word
        for (String perm : permutations) {
            if (is_valid(letters, perm) && !WORDS.contains(perm)) {  // Avoid duplicates
                WORDS.add(perm);
            }
        }

        // Displays results
        if (WORDS.isEmpty()) {
            System.out.println("No valid words found");
        } else {
            System.out.println("Valid words found are: ");
        }
        
        for(String word:WORDS){
            System.out.println(word);
        }

    }

    
    /**
     * Checks if a given word exists in the appropriate word list based on the input length.
     * @param letters The original set of letters entered by the user.
     * @param word The word to validate.
     * @return true if the word exists in the dictionary file, false otherwise.
     */

    public static boolean is_valid(String letters,String word){
        List<String> vaild_words = new ArrayList<>();  // Initialize list

        // Check which file to open base on the inputs size
        
            // Using try catch method to avoid errors
            try {
                Scanner myReader = new Scanner(new File("20k_filtered_sorted.txt"));
                while (myReader.hasNextLine()) {
                    vaild_words.add(myReader.nextLine());
                }
                // Avoiding leakage
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        // Sorting for the binary search 
        Collections.sort(vaild_words); 
        // If the word is vaild and in the file returns true
        return Collections.binarySearch(vaild_words,word) >= 0;
    }

        
   
   
   // Returing list of all possible combinations of the word
   public static List<String> getPermutations(String str) {
    List<String> result = new ArrayList<>();
    All_possible_combinations(str, "", result);
    //System.out.println(result);
    return result;
}

   
   
    // Generating all possible combinations for the inputted letters using recursion
    private static void All_possible_combinations(String str, String current, List<String> result) {
        if (!current.isEmpty()) { // Avoid adding an empty subset
            result.add(current);
        }
    
        for (int i = 0; i < str.length(); i++) {
            String remaining = str.substring(0, i) + str.substring(i + 1);
            All_possible_combinations(remaining, current + str.charAt(i), result);
        }
    }



}