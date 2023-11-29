import java.util.HashMap;
import java.util.Map;

public class CryptarithmeticSolver {

    public static void main(String[] args) {
        String puzzle = "TO + GO = OUT";
        solveCryptarithmetic(puzzle);
    }

    public static void solveCryptarithmetic(String puzzle) {
        puzzle = puzzle.replaceAll("\\s", ""); // Remove spaces
        String[] words = puzzle.split("[+=]");

        String uniqueCharacters = getUniqueCharacters(puzzle);
        int[] digits = new int[uniqueCharacters.length()];

        if (backtrack(0, uniqueCharacters, digits, words, new HashMap<>())) {
            printSolution(uniqueCharacters, digits);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean backtrack(int index, String uniqueCharacters, int[] digits, String[] words, Map<Character, Integer> assignment) {
        if (index == uniqueCharacters.length()) {
            return checkSolution(words, assignment);
        }

        char currentChar = uniqueCharacters.charAt(index);

        for (int digit = 0; digit <= 9; digit++) {
            if (!assignment.containsValue(digit)) {
                assignment.put(currentChar, digit);
                digits[index] = digit;

                if (backtrack(index + 1, uniqueCharacters, digits, words, assignment)) {
                    return true;
                }

                assignment.remove(currentChar);
            }
        }

        return false;
    }

    private static boolean checkSolution(String[] words, Map<Character, Integer> assignment) {
        int sum = 0;

        for (String word : words) {
            int value = 0;

            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                int digit = assignment.get(currentChar);
                value = value * 10 + digit;
            }

            sum += value;
        }

        return sum == 0; // Check if the sum of the words equals zero
    }

    private static String getUniqueCharacters(String puzzle) {
        StringBuilder uniqueChars = new StringBuilder();

        for (int i = 0; i < puzzle.length(); i++) {
            char currentChar = puzzle.charAt(i);

            if (Character.isLetter(currentChar) && uniqueChars.indexOf(String.valueOf(currentChar)) == -1) {
                uniqueChars.append(currentChar);
            }
        }

        return uniqueChars.toString();
    }

    private static void printSolution(String uniqueCharacters, int[] digits) {
        for (int i = 0; i < uniqueCharacters.length(); i++) {
            char currentChar = uniqueCharacters.charAt(i);
            System.out.println(currentChar + " = " + digits[i]);
        }
    }
}
