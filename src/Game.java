import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Game {
    public Timer timer;
    public Player playerOne;
    public Player playerTwo;
    public boolean isItPlayerOne;
    public boolean isGameRunning;
    public int duration;
    public Game(){
        this.timer = new Timer();
        this.playerOne = new Player("Player One");
        this.playerTwo = new Player("Player Two");
        this.isItPlayerOne = true;
        this.isGameRunning = false;
        this.duration = 0;

    }
    public Game(Player playerOne, Player playerTwo){
        this.timer = new Timer();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.isItPlayerOne = true;
        this.isGameRunning = false;
        this.duration = 0;
    }
    public void setTimerStatus(Timer timer, boolean doEnableTimer)
    {
        if (doEnableTimer) {
            int startTime = 0;
            int interval = 1000; // 1 second in milliseconds

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    duration++;
                }
            }, startTime, interval);
        }
        else {
            timer.cancel();
        }
    }
    public void run(Timer timer){
        setTimerStatus(timer, true);
        this.isGameRunning = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Alright, lets get started then!");
        System.out.println("Player one goes first.");
        while (isGameRunning == true){
            Player player = new Player("");
            if (isItPlayerOne){
                player = this.playerOne;
                if (this.playerOne.hasBonusAction){
                    this.playerOne.hasBonusAction = false;
                }
            }
            else {
                player = this.playerTwo;
                if (this.playerTwo.hasBonusAction){
                    this.playerTwo.hasBonusAction = false;
                }
            }
            System.out.println(player.name + ", what action would you like to do?");
            System.out.println("-> Attack \n-> Defend \n-> Train \n-> Knockout");
            String action = scanner.nextLine();

            if (action.toLowerCase().equals("attack")){
                if (isItPlayerOne) {
                    boolean isPlayerDead = playerOne.attack(playerTwo);
                    if (isPlayerDead) {
                        break;
                    }
                }
                else {
                    boolean isPlayerDead = playerTwo.attack(playerOne);
                    if (isPlayerDead) {
                        break;
                    }
                }
            }
            else if (action.toLowerCase().equals("defend")){
                if (isItPlayerOne)
                    playerOne.defend();
                else
                    playerTwo.defend();
            }
            else if (action.toLowerCase().equals("train")){
                if (isItPlayerOne)
                    playerOne.train();
                else
                    playerTwo.train();
            }
            else if (action.toLowerCase().equals("knockout") || action.toLowerCase().equals("ko")){
                if (isItPlayerOne){
                    boolean isPlayerDead = playerOne.knockout(playerTwo);
                    if (isPlayerDead) {
                        break;
                    }
                    if (playerOne.hasBonusAction){
                        continue;
                    }
                }
                else {
                    boolean isPlayerDead = playerTwo.knockout(playerOne);
                    if (isPlayerDead) {
                        break;
                    }
                    if (playerTwo.hasBonusAction){
                        continue;
                    }
                }
            }
            else {
                System.out.println("Please only enter attack, defend, train, or knockout");
                continue;
            }

            isItPlayerOne = !isItPlayerOne;
        }
        setTimerStatus(timer, false);
    }
}