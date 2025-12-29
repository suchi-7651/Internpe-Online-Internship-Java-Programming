import java.util.*;

class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addPoint() {
        score++;
    }
}

class SmartAI {
    private Map<String, Integer> playerMoveCount = new HashMap<>();
    private Random rand = new Random();
    private String[] moves = {"rock", "paper", "scissor"};

    public String getAIMove(boolean smartMode) {
        if (!smartMode || playerMoveCount.isEmpty()) {
            return moves[rand.nextInt(moves.length)];
        }

        String mostUsedMove = Collections.max(
                playerMoveCount.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();

        if (mostUsedMove.equals("rock")) return "paper";
        if (mostUsedMove.equals("paper")) return "scissor";
        return "rock";
    }

    public void recordPlayerMove(String move) {
        playerMoveCount.put(move, playerMoveCount.getOrDefault(move, 0) + 1);
    }
}

public class RockPaperScissorGame {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Rock Paper Scissor Game!");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        Player player = new Player(name);
        Player computer = new Player("Computer");
        SmartAI ai = new SmartAI();

        System.out.println("\n1. Normal Mode");
        System.out.println("2. Smart AI Mode");
        System.out.print("Choose mode: ");
        int mode = sc.nextInt();

        boolean smartMode = (mode == 2);
        String again;

        do {
            playRound(player, computer, ai, smartMode);
            System.out.print("\nPlay again? (yes/no): ");
            again = sc.next().toLowerCase();
        } while (again.equals("yes"));

        System.out.println("\nFinal Score");
        System.out.println(player.getName() + ": " + player.getScore());
        System.out.println("Computer: " + computer.getScore());
    }

    static void playRound(Player player, Player computer, SmartAI ai, boolean smartMode) {
        System.out.print("\nEnter move (rock/paper/scissor): ");
        String userMove = sc.next().toLowerCase();

        if (!userMove.equals("rock") &&
            !userMove.equals("paper") &&
            !userMove.equals("scissor")) {
            System.out.println("Invalid move!");
            return;
        }

        ai.recordPlayerMove(userMove);
        String aiMove = ai.getAIMove(smartMode);

        System.out.println("Computer chose: " + aiMove);

        if (userMove.equals(aiMove)) {
            System.out.println("Draw!");
        } else if (
            (userMove.equals("rock") && aiMove.equals("scissor")) ||
            (userMove.equals("paper") && aiMove.equals("rock")) ||
            (userMove.equals("scissor") && aiMove.equals("paper"))
        ) {
            System.out.println("You win!");
            player.addPoint();
        } else {
            System.out.println("You lose!");
            computer.addPoint();
        }
    }
}
