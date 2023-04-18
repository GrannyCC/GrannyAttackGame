import java.util.Scanner;
import java.util.Timer;

public class Program {
    public static void main(String[] args) {
        boolean startScreen = true;
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();
        double gameDurationDouble;

        while (startScreen){
            System.out.println("Hello, this is my (Granny's) attack game, what would you like to do?");
            System.out.println("-> Start (s) \n-> Learn more (l)");
            String startInput = scanner.nextLine();
            if (startInput.toLowerCase().equals("s") || startInput.toLowerCase().equals("start")){
                startScreen = false;
            }
            else if (startInput.toLowerCase().equals("l") || startInput.toLowerCase().equals("learn more")){
                explanation();
            }
            else{
                System.out.println("Please only enter one of the options");
            }
        }
        System.out.println("Howdy player one, please enter the name you would like to be shown as.");
        Player playerOne = new Player(scanner.nextLine());
        System.out.println("Howdy player two, please enter the name you would like to be shown as.");
        Player playerTwo = new Player(scanner.nextLine());
        Game game = new Game(playerOne, playerTwo);
        game.run(timer);
        if (game.duration >= 60){
            gameDurationDouble = Math.round(game.duration/60);
            System.out.println("The game ended with a time of " + gameDurationDouble + " minutes");
        }
        else{
            System.out.println("The game ended with a time of " + game.duration + " minutes");
        }

    }
    public static void explanation() {
        System.out.println("When it's your turn, you have");
        //off session explanation
    }
}
