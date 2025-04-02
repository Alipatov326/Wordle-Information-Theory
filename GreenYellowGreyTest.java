public class GreenYellowGreyTest {
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
    public static void main(String[] args) {
        String targetWord = "aahed";
        String guessWord = "slate";

        // Test green function
        System.out.println("Green Test:");
        for (int i = 0; i < targetWord.length(); i++) {
            System.out.println("Index " + i + ": " + green(targetWord, guessWord, i));
        }

        // Test yellow function
        System.out.println("Yellow Test:");
        for (int i = 0; i < targetWord.length(); i++) {
            System.out.println("Index " + i + ": " + yellow(targetWord, guessWord, i));
        }

        // Test grey function
        System.out.println("Grey Test:");
        for (int i = 0; i < targetWord.length(); i++) {
            System.out.println("Index " + i + ": " + grey(targetWord, guessWord, i));
        }
    }
}
