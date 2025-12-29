import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        boolean playAgain = true;

        System.out.println(" Welcome to the Guess the Number Game!");

        while (playAgain) {
            System.out.println("\nChoose Difficulty Level:");
            System.out.println("1. Easy (1–50)");
            System.out.println("2. Medium (1–100)");
            System.out.println("3. Hard (1–500)");
            System.out.print("Enter your choice (1/2/3): ");

            int choice = input.nextInt();
            int maxRange;

            switch (choice) {
                case 1:
                    maxRange = 50;
                    break;
                case 2:
                    maxRange = 100;
                    break;
                case 3:
                    maxRange = 500;
                    break;
                default:
                    System.out.println("Invalid choice! Defaulting to Medium (1–100)");
                    maxRange = 100;
            }

            int numberToGuess = rand.nextInt(maxRange) + 1;
            int userGuess = 0;
            int attempts = 0;

            System.out.println("\nI picked a number between 1 and " + maxRange);

            while (userGuess != numberToGuess) {
                System.out.print("Enter your guess: ");
                userGuess = input.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Correct! You guessed it in " + attempts + " attempts!");
                }
            }

            System.out.print("\nPlay again? (yes/no): ");
            String response = input.next().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
        }

        System.out.println("\nThanks for playing!");
        input.close();
    }
}
