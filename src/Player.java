import java.util.Random;
import java.util.random.RandomGenerator;

public class Player {
    public String name;
    public int health;
    public boolean hasBonusAction;
    public int defense;
    public int strength;
    public int numberOfAttacks;
    public int numberOfDefends;
    public int numberOfTrains;
    public int numberOfMissedKO;
    public int numberOfKO;

    public Player(String name){
        this.name = name;
        this.health = 100;
        this.hasBonusAction = false;
        this.defense = 0;
        this.strength = 0;
        this.numberOfAttacks = 0;
        this.numberOfDefends = 0;
        this.numberOfTrains = 0;
        this.numberOfMissedKO = 0;
        this.numberOfKO = 0;

    }
    public Player(String name, int health, int defense, int strength){
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.strength = strength;
        this.hasBonusAction = false;
        this.numberOfAttacks = 0;
        this.numberOfDefends = 0;
        this.numberOfTrains = 0;
        this.numberOfMissedKO = 0;
        this.numberOfKO = 0;
    }
    //region functions
    public boolean attack(Player Opponent){
        Random random = new Random();
        int randInt = random.nextInt(1, 10 + this.strength);
        int damage = randInt - Opponent.defense;
        if (damage <= 0){
            damage = 0;
            System.out.println("You tried to attack... but your opponent blocked it all!");
        }
        else{
            Opponent.health -= damage;
            if (Opponent.health <= 0){
                win(this);
                return true;
            }
            else {
                System.out.println("Your opponent had " + Opponent.defense + " defense, so you dealt " +  damage +
                        " damage to your opponent, leaving them with " + Opponent.health + " health");
            }
        }
        this.hasBonusAction = false;
        return false;
    }
    public void defend(){
        Random random = new Random();
        int softCap = random.nextInt(0, this.defense/5 + 1);
        int baseDefense = random.nextInt(1, 4) - softCap;
        if (baseDefense <= 0){
            System.out.println("You have reached the max defense!");
        }
        else{
            this.defense += baseDefense;
            System.out.println("You have increased your defense by " + baseDefense + ", to " + this.defense);
        }
        this.hasBonusAction = false;
    }
    public boolean knockout(Player Opponent){
        Random random = new Random();
        int koChance= random.nextInt(1, 21);
        if (koChance == 1){
            Opponent.health = (Opponent.health/2) - 10;
            System.out.println("You have successfully KO'd " + Opponent.name + ", they now have " + Opponent.health +
                    " health left!");
            if (Opponent.health <= 0){
                win(this);
                return true;
            }
            else{
                System.out.println("Your turn again, " + this.name + "!");
                this.hasBonusAction = true;
            }

        }
        else {
            System.out.println("You missed!");
        }
        this.hasBonusAction = false;
        return false;
    }
    public void train(){
        Random random = new Random();
        int train = random.nextInt(1, 4);
        this.strength += train;
        System.out.println("You have increased your strength by " + train + ", to " + this.strength);
        this.hasBonusAction = false;
    }
    public void win(Player Winner){
        double percentHit = Winner.numberOfKO/(Winner.numberOfMissedKO + Winner.numberOfKO);
        System.out.println("Congratulations, " + Winner.name + "! You won, here are your statistics...");
        System.out.println("-----------------");
        System.out.println("Number of Attacks:" + Winner.numberOfAttacks);
        System.out.println("Number of Defends:" + Winner.numberOfDefends);
        System.out.println("Number of Trains:" + Winner.numberOfTrains);
        System.out.println("Number of Missed KOs:" + Winner.numberOfMissedKO);
        System.out.println("Number of Hit KOs:" + Winner.numberOfKO);
        System.out.println("Percentage of KOs Hit:" + percentHit + "%");
        System.out.println("-----------------");
        System.out.println("Thanks for playing!");
    }
    //endregion
}
