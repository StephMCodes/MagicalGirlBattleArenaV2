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

        System.out.println(playerStats.getPlayerStyle());
        System.out.println(playerStats.getPlayerStyle().getMaxHealth());
        //custom intro
        if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.gun) {
            System.out.println(ANSI.BLUE + "Your magical girl opponent " + magicalGirlEnemy.getName() + " appears...");
            System.out.println("She has sharp aim, be careful!" + ANSI.DEFAULT);

        } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.jumboHammer) {
            System.out.println(ANSI.RED + "Your magical girl opponent " + magicalGirlEnemy.getName() + " appears...");
            System.out.println("She has a fiery spirit, be careful!" + ANSI.DEFAULT);

        } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.darkMagic) {

            System.out.println(ANSI.MAGENTA + "Your magical girl opponent " + magicalGirlEnemy.getName() + " appears...");
            System.out.println("She has lots of knowledge, be careful!" + ANSI.DEFAULT);
        } else {
            System.out.println(ANSI.YELLOW + "Your magical girl opponent " + magicalGirlEnemy.getName() + " appears...");
            System.out.println("She has a kind smile, but is a veteran, be careful!" + ANSI.DEFAULT);

        }
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
                    System.out.println(ANSI.YELLOW + "Aha! Your opponent is concussed and loses their turn!" + ANSI.DEFAULT);
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
        System.out.println(ANSI.RED + "[1] Attack"); //damage
        System.out.println(ANSI.BLUE + "[2] Heal " + playerStats.getHealCharges() + " left"); //heal if available
        System.out.println(ANSI.YELLOW + "[3] Use CC " + playerStats.getCcCharges() + " left" + ANSI.DEFAULT);//concuss
        System.out.println("[4] Save Game");
        System.out.println("[5] Load Game");
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
                    //custom attack
                    if (playerStats.ogStyle == MagicalGirlFightStyle.gun) {
                        System.out.println(ANSI.BLUE + "You aim is true! BANG!" + ANSI.DEFAULT);

                    } else if (playerStats.ogStyle == MagicalGirlFightStyle.jumboHammer) {
                        System.out.println(ANSI.RED + "Your hammer comes down with a BAM!" + ANSI.DEFAULT);

                    } else if (playerStats.ogStyle == MagicalGirlFightStyle.darkMagic) {

                        System.out.println(ANSI.MAGENTA + "You call unseen forces to hex your opponent!" + ANSI.DEFAULT);

                    } else {
                        System.out.println(ANSI.YELLOW + "Your radiant beam strikes with cleansing power!" + ANSI.DEFAULT);
                    }
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
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    return;

                //save during game
                case 4:
                    GameManager.getInstance().saveGame(playerStats);
                    break;
                //load during game
                case 5:
                    GameManager.getInstance().loadGame(playerStats);
                    break;

                default:
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                    break;
            }
        }
    }

    protected void DecorateCharacter(PlayerStats player) {
        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Congratulations! For besting your opponent in battle, the arena grants you an item!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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
                    System.out.println(ANSI.MAGENTA + "You chose the Health power-up." + ANSI.DEFAULT);
                    //create decorator
                    ICombatStats addHealthRing = new HealthRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addHealthRing);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    break; //exit the switch loop and the while loop!
                case 2:
                    System.out.println(ANSI.RED + "You chose the Strength power-up." + ANSI.DEFAULT);
                    ICombatStats addPowerRing = new PowerRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addPowerRing);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println(ANSI.BLUE + "You chose the Agility power-up." + ANSI.DEFAULT);
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
            magicalGirlEnemy.useCC(magicalGirlEnemy);
            System.out.println(ANSI.YELLOW + "Your opponent " + magicalGirlEnemy.getName() + " used concuss on you. You skip a turn!" + ANSI.DEFAULT);
            System.out.println("Press [ENTER] to continue.");
            scanner.nextLine();
            return;

        }

        int dmg = magicalGirlEnemy.Damage(); //enemy calculates damage
        playerStats.getHit(dmg);
        //custom attack enemy
        if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.gun) {
            System.out.println(ANSI.BLUE + "BANG! Too slow, it hits you!" + ANSI.DEFAULT);

        } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.jumboHammer) {
            System.out.println(ANSI.RED + "It's a dizzying hit from her hammer, and the ground cracks beneath it!" + ANSI.DEFAULT);

        } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.darkMagic) {

            System.out.println(ANSI.MAGENTA + "You feel your life force drain from you! How can a book hurt so much?" + ANSI.DEFAULT);

        } else {
            System.out.println(ANSI.YELLOW + "They may be shiny, but those sparkles sting!" + ANSI.DEFAULT);
        }
        System.out.println(ANSI.RED + "The magical girl opponent " + magicalGirlEnemy.getName() + " attacked you for " + dmg + " damage!" + ANSI.DEFAULT);
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
        System.out.println(ANSI.MAGENTA + "Your HP: " + playerStats.getCurrentHealth());
        System.out.println("Opponent HP: " + magicalGirlEnemy.getCurrentHealth());
        System.out.println(ANSI.DEFAULT + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    //win or lose display
    protected void sayWinner(PlayerStats playerStats, PlayerStats magicalGirlEnemy) {
//to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);
        //alive you win or die ya lose
        if (playerStats.isAlive()) {
            System.out.println("\nYou won the battle against " + magicalGirlEnemy.getName() + "!");
            //custom msg enemy
            if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.gun) {
                System.out.println(ANSI.BLUE + "Esme pushes up her glasses with a disappointed look on her face." + ANSI.DEFAULT);

            } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.jumboHammer) {
                System.out.println(ANSI.RED + "Astrid immediately starts breaking things, only taking a moment to shake your hand good-naturedly before breaking more stuff." + ANSI.DEFAULT);

            } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.darkMagic) {

                System.out.println(ANSI.MAGENTA + "Iris remains on the ground, hurriedly writing down how she can improve next time. Best not to disturb her..." + ANSI.DEFAULT);

            } else {
                System.out.println(ANSI.YELLOW + "Evangeline has a teary sheen to her eyes, but she smiles and congratulates you on your victory." + ANSI.DEFAULT);
            }
            System.out.println("Press [ENTER] to continue.");
            scanner.nextLine();

        } else {
            System.out.println("\nYou were defeated by " + magicalGirlEnemy.getName() + " ...");
            //custom msg enemy
            if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.gun) {
                System.out.println(ANSI.BLUE + "Esme helps you up and tells you to keep trying. It's hard to be mad when she's so cool..." + ANSI.DEFAULT);

            } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.jumboHammer) {
                System.out.println(ANSI.RED + "Astrid laughs boisterously, already asking you for another go." + ANSI.DEFAULT);

            } else if (magicalGirlEnemy.getPlayerStyle() == MagicalGirlFightStyle.darkMagic) {

                System.out.println(ANSI.MAGENTA + "Iris offers you a dark flower as consolation and some notes on your fighting." + ANSI.DEFAULT);

            } else {
                System.out.println(ANSI.YELLOW + "Evangeline offers you consolation sugar cookies." + ANSI.DEFAULT);
            }
            System.out.println("Press [ENTER] to continue.");
            scanner.nextLine();
        }

    }


}
