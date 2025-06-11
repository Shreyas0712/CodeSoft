import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 7;  // Number of tries per round
        int score = 0;        // Rounds won
        boolean playAgain = true;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        while (playAgain) {
            int generatedNumber = random.nextInt(100) + 1;
            int attemptsLeft = maxAttempts;
            boolean guessedCorrectly = false;

            System.out.println("\n🔢 A number between 1 and 100 has been chosen.");
            System.out.println("You have " + maxAttempts + " attempts to guess it!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == generatedNumber) {
                    System.out.println("🎉 Correct! You guessed the number.");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Too high! Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've used all your attempts. The number was: " + generatedNumber);
            }

            System.out.println("🏆 Your current score: " + score);

            // Ask to play again
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\nThanks for playing! 🎮 Final Score: " + score);
        scanner.close();
    }
}
