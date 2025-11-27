import java.util.Random;
import java.util.Scanner;

//using template method
public abstract class BattleSystem {

    //factory design pattern for enemies
    protected abstract PlayerStats createMagicalGirlEnemy(PlayerStats playerStats);

    //final to make sure subclasses don't interfere
    public final void startBattle(PlayerStats playerStats) {

        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

        //create enemy from subclass regular battle atm
        PlayerStats magicalGirlEnemy = createMagicalGirlEnemy(playerStats);

        System.out.println("Your magical girl opponent " + magicalGirlEnemy.getName() + " appears...");
        //shows health of enemy magic girl
        System.out.println("Your opponent " + magicalGirlEnemy.getName() + " is at " + magicalGirlEnemy.getCurrentHealth() + "HP");


        //decides who goes first
        PlayerStats currentTurn = determineTurnOrder(playerStats, magicalGirlEnemy);

        //magical girl who's other turn it is
        PlayerStats otherPlayer;

        //if player is first the opponent goes afterward
        if (currentTurn == playerStats) {
            otherPlayer = magicalGirlEnemy;
        }
        //player goes 2nd
        else {
            otherPlayer = playerStats;
        }

        //loops and checks that everyone is alive
        while (playerStats.isAlive() && magicalGirlEnemy.isAlive()) {
            //mc's turn
            if (currentTurn == playerStats) {

                //if mc cc'd it skips a turn
                if (playerStats.skipTurn()) {

                    System.out.println(ANSI.YELLOW + "\nOh no! You have been concussed and you lose a turn! " + ANSI.DEFAULT);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                } else {
                    playerTurn(playerStats, magicalGirlEnemy, scanner); //handles player chosen action
                }
            }

            //magical girl opponent
            else {

                //if magical girl opponent is cc'd it skips their turn
                if (magicalGirlEnemy.skipTurn()) {
                    System.out.println(ANSI.YELLOW + "Aha! Your opponent is concussed and loses their turn!" + ANSI.YELLOW);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                } else {
                    enemyTurn(magicalGirlEnemy, playerStats); // magical girl opponent attacks the player
                }
            }

            //displays current hp player,then enemy
            printStatus(playerStats, magicalGirlEnemy);

            //change turns
            PlayerStats previousTurnPlayer = currentTurn; //previous magical girl
            currentTurn = otherPlayer; // switches turn to waiting
            otherPlayer = previousTurnPlayer; // changes magical girl to one whose turn it just was

        }
        sayWinner(playerStats, magicalGirlEnemy); //displays winner

    }

    //magical girl player's choices
    protected void playerTurn(PlayerStats playerStats, PlayerStats magicalGirlEnemy, Scanner scanner) {

        System.out.println("\nIt's your turn! How will you proceed...");
        System.out.println(ANSI.RED+"[1] Attack"); //damage
        System.out.println(ANSI.BLUE+"[2] Heal " + playerStats.getHealCharges() + " left"); //heal if available
        System.out.println(ANSI.YELLOW+"[3] Use CC " + playerStats.getCcCharges() + " left" + ANSI.DEFAULT);//concuss
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("Enter a number from [1-3]: ");

        //we avoid an exception if user inputs a string and not int
        int choice = 0;
        while (true) {
            while (true) {

                String choiceString = scanner.nextLine();

                try {
                    choice = Integer.parseInt(choiceString);
                    break;
                } catch (Exception e) {
                    System.out.println("[INVALID INPUT] Enter a number from 1-3: ");
                }
            }
            switch (choice) {

                //attack
                case 1:
                    int dmg = playerStats.Damage();
                    magicalGirlEnemy.getHit(dmg);
                    System.out.println(ANSI.RED + "You have hit your opponent for " + dmg + " damage!" + ANSI.DEFAULT);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    return;
                //heal
                case 2:
                    int healAmount = playerStats.heal();
                    System.out.println(ANSI.BLUE + "Phew!... You healed for " + healAmount + "HP !" + ANSI.DEFAULT);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    return;
                //cc
                case 3:
                    playerStats.useCC(magicalGirlEnemy);
                    System.out.println(ANSI.YELLOW + "You concussed your opponent with a hit to the head!" + ANSI.DEFAULT);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                    break;
            }
        }
    }

    protected void DecorateCharacter(PlayerStats player) {
        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);
        System.out.println("Congratulations! For besting your opponent in battle, the arena grants you an item!");
        System.out.println("Choose one of three power accessories: ");
        System.out.println("");

        System.out.println(ANSI.MAGENTA + "[1] Health Ring");
        System.out.println("HP + 5");
        System.out.println("Fun fact: The gem is shaped like a heart.");
        System.out.println("");

        System.out.println(ANSI.RED + "[2] Strength Ring");
        System.out.println("Strength + 5");
        System.out.println("Fun fact: It glows like fire.");
        System.out.println("");

        System.out.println(ANSI.BLUE + "[3] Agility Ring");
        System.out.println("Agility + 5");
        System.out.println("Fun fact: It feels weightless.");
        System.out.println("" + ANSI.DEFAULT);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("Enter a number from [1-3]: ");

        //no choice yet
        MagicalGirlFactory magicalGirlFactory = null;

        //we avoid an exception if user inputs a string and not in
        int itemSelect = 0;

        while (true) {

            String itemString = scanner.nextLine();

                try {
                    itemSelect = Integer.parseInt(itemString);
                    break;
                } catch (Exception e) {
                    System.out.println("[INVALID INPUT] Enter a number from 1-3: ");
                }
            }



            while (true) {

                switch (itemSelect) {
                    case 1:
                        System.out.println(ANSI.MAGENTA + "You chose the health power-up." + ANSI.DEFAULT);
                        //create decorator
                        ICombatStats addHealthRing = new HealthRingDecorator(player.getPlayerStyle());
                        player.setPlayerStyle(addHealthRing);
                        System.out.println("Press [ENTER] to continue.");
                        scanner.nextLine();
                        break; //exit the switch loop and the while loop!
                    case 2:
                        System.out.println(ANSI.RED + "You chose the strength power-up." + ANSI.DEFAULT);
                        ICombatStats addPowerRing = new PowerRingDecorator(player.getPlayerStyle());
                        player.setPlayerStyle(addPowerRing);
                        System.out.println("Press [ENTER] to continue.");
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println(ANSI.BLUE + "You chose the agility power-up." + ANSI.DEFAULT);
                        ICombatStats addAgilityRing = new AgilityRingDecorator(player.getPlayerStyle());
                        player.setPlayerStyle(addAgilityRing);
                        System.out.println("Press [ENTER] to continue.");
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("[INVALID INPUT] Enter a number from 1-3: ");
                        continue;
                }
                break;

            }
            //wrap around your stats
        }


    //magical girl enemy turn
    protected void enemyTurn(PlayerStats magicalGirlEnemy, PlayerStats playerStats) {
//to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

        //rnd enemy action probability out of 100
        int enemyAction = new Random().nextInt(100);

        //if cc is 20 or less + have a charge left it attacks
        if (enemyAction <= 20 && magicalGirlEnemy.getCcCharges() > 0) {
            magicalGirlEnemy.useCC(playerStats);
            System.out.println(ANSI.YELLOW + "Your opponent " + magicalGirlEnemy.getName() + " used concuss on you. You skip a turn!" + ANSI.DEFAULT);
            System.out.println("Press [ENTER] to continue.");
            scanner.nextLine();
            return;

        }

        int dmg = magicalGirlEnemy.Damage(); //enemy calculates damage
        playerStats.getHit(dmg);
        System.out.println(ANSI.RED + "The magical girl opponent " +magicalGirlEnemy.getName() + "attacked you for " + dmg + " damage!" + ANSI.DEFAULT);
        System.out.println("Press [ENTER] to continue.");
        scanner.nextLine();
    }

    //turn order
    protected PlayerStats determineTurnOrder(PlayerStats playerStats, PlayerStats magicalGirlEnemy) {

        if (playerStats.getAgility() >= magicalGirlEnemy.getAgility()) { // compares agility
            System.out.println("\nYou have the upper-hand and get to go first!");
            return playerStats;
        } else {
            System.out.println("\nToo slow! Your opponent goes first.");
            return magicalGirlEnemy;
        }
    }

    //displays health after each turn
    protected void printStatus(PlayerStats playerStats, PlayerStats magicalGirlEnemy) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~ HEALTH STATUS ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(ANSI.MAGENTA +"Your HP: " + playerStats.getCurrentHealth());
        System.out.println("Opponent HP: " + magicalGirlEnemy.getCurrentHealth());
        System.out.println(ANSI.DEFAULT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    //win or lose display
    protected void sayWinner(PlayerStats playerStats, PlayerStats magicalGirlEnemy) {

        //alive you win or die ya lose
        if (playerStats.isAlive()) {
            System.out.println("\nYou won the battle against" + magicalGirlEnemy.getName() + "!");
        } else {
            System.out.println("\nYou were defeated by " + magicalGirlEnemy.getName() + " ...");
        }

    }


}
