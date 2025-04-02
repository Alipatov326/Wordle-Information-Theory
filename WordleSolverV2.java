import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class WordleSolverV2 {

    public static void main(String[] args) {
        ArrayList<Double> entropy = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        try {
            //File file = new File("src/wordle-answers-alphabetical.txt");
            File file = new File("src/valid-wordle-words.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String word = scanner.next();
                words.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }


        double totalEntropy = 0.0;
        int possible = 0;
        String tiles = "";
        String currentWord = "slate";
        //for (int z = 0; z < words.size(); z++) {
        currentWord = "slate";
        for (int A = 0; A < 3; A++) {
            for (int B = 0; B < 3; B++) {
                for (int C = 0; C < 3; C++) {
                    for (int D = 0; D < 3; D++) {
                        for (int E = 0; E < 3; E++) {
                            tiles = "" + A + B + C + D + E;
                            possible = 0;
                            for (int i = 0; i < words.size(); i++) {
                                int x = 0;
                                for (int j = 0; j < 5; j++) {
                                    // SWITCH currentWord and words.get(i) if doesnt work
                                    if (tiles.charAt(j) == '0' && grey(words.get(i), currentWord, j)) {
                                        x++;
                                    }
                                    else if (tiles.charAt(j) == '1' && yellow(words.get(i), currentWord, j)) {
                                        x++;
                                    }
                                    else if (tiles.charAt(j) == '2' && green(words.get(i), currentWord, j)) {
                                        x++;
                                    }


                                        /*
                                        if (tiles.charAt(j) == '0' && grey(currentWord, words.get(i), j)) {
                                            possible++;
                                        } else if (tiles.charAt(j) == '1' && yellow(currentWord, words.get(i), j)) {
                                            possible++;
                                        } else if (tiles.charAt(j) == '2' && green(currentWord, words.get(i), j)) {
                                            possible++;
                                        }

                                         */
                                }
                                if (x == 5) {
                                    possible++;
                                }
                            }
                            System.out.println(possible);
                            if (possible != 0) {
                                System.out.println((possible / (double) words.size()) * (logBase2(words.size() / (double) possible)));
                                totalEntropy += (possible / (double) words.size()) * (logBase2(words.size() / (double) possible));
                            }

                        }
                    }
                }
            }
        }
        entropy.add(totalEntropy);
        System.out.println(entropy);
        // System.out.println("Entropy for " + words.get(2628) + " is " + entropy.get(2628));
    }

    // System.out.println("Entropy for " + words.get(2628) + " is " + entropy.get(2628));
    //}

    /*
    public static int stringComp(String targetWord, ArrayList<String> words) {
        Map<String, Integer> combinationFits = checkCombinations(targetWord, words);
        int totalFits = 0;
        for (int fits : combinationFits.values()) {
            totalFits += fits;
        }
        return totalFits;
    }
    */
    /*
    public static Map<String, Integer> checkCombinations(String targetWord, ArrayList<String> words) {
        Map<String, Integer> combinationFits = new HashMap<>();
        int length = targetWord.length();
        int combinations = (int) Math.pow(3, length);

        for (int i = 0; i < combinations; i++) {
            StringBuilder feedback = new StringBuilder();
            int temp = i;
            for (int j = 0; j < length; j++) {
                int feedbackType = temp % 3;
                if (feedbackType == 0) {
                    feedback.append('G'); // Green
                } else if (feedbackType == 1) {
                    feedback.append('Y'); // Yellow
                } else {
                    feedback.append('X'); // Gray
                }
                temp /= 3;
            }
            int fits = countFits(targetWord, words, feedback.toString());
            combinationFits.put(feedback.toString(), fits);
        }
        return combinationFits;
    }/
     */

    public static boolean green(String targetWord, String guessWord, int index){
        return targetWord.charAt(index) == guessWord.charAt(index);
    }

    public static boolean yellow(String targetWord, String guessWord, int index) {
        if (green(targetWord, guessWord, index)) {
            return false;
        }
        if (index != 0) {
            for (int j = index - 1; j > -1; j--) {
                if (guessWord.charAt(j) == guessWord.charAt(index)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < targetWord.length(); i++) {
            if (targetWord.charAt(i) == guessWord.charAt(index)) {
                return true;
            }
        }
        return false;
    }

    public static boolean grey(String targetWord, String guessWord, int index){
        for (int i = 0; i < targetWord.length(); i++) {
            if (targetWord.charAt(i) == guessWord.charAt(index)) {
                return false;
            }
        }
        return true;
    }

    public static double logBase2(double number) {
        return Math.log(number) / Math.log(2);
    }
}