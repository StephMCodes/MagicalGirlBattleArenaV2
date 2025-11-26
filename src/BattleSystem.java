import java.util.Random;
import java.util.Scanner;

//using template method
public abstract class BattleSystem {

    //final to amke sure subclasses don't interfere
    public final void startBattle(PlayerStats playerStats){

        System.out.println("Press [P] anytime you want to pause!");
        //create enemy from subclass regular battle atm
        PlayerStats magicalGirlEnemy = createMagicalGirlEnemy();

        System.out.println("Your magical girl opponent appears..." + magicalGirlEnemy);
        //shows health of enemy magic girl
        System.out.println("Your opponents hp is "+ magicalGirlEnemy.getCurrentHealth());

        //decides who goes first
        PlayerStats currentTurn = determineTurnOrder(playerStats,magicalGirlEnemy);

        //magical girl who's other turn it is
        PlayerStats otherPlayer;

        //if player is first the opponent goes afterward
        if(currentTurn == playerStats){
            otherPlayer = magicalGirlEnemy;
        }
        //player goes 2nd
        else {
            otherPlayer = playerStats;
        }

        Scanner scanner = new Scanner(System.in);

        //loops and checks that everyone is alive
        while (playerStats.isAlive()&& magicalGirlEnemy.isAlive()){

            //mc's turn
            if(currentTurn == playerStats){

                //if mc cc'd it skips a turn
                if(playerStats.skipTurn()){

                    System.out.println("\nYou have been concussed and you lose a turn! ");
                }
                else{
                    playerTurn(playerStats,magicalGirlEnemy,scanner); //handles player chosen action
                }
            }

            //magical girl opponent
            else{

                //if magical girl opponent is cc'd it skips their turn
                if(magicalGirlEnemy.skipTurn()) {
                    System.out.println("Your opponent is concussed and loses their turn");
                }
                else{
                    enemyTurn(magicalGirlEnemy,playerStats); // magical girl opponent attacks the player
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

    //factory design pattern for enemies
    protected abstract PlayerStats createMagicalGirlEnemy();

    //magical girl player's choices
    protected void playerTurn(PlayerStats playerStats, PlayerStats magicalGirlEnemy,Scanner scanner){

       // String input = scanner.nextLine();

       // if(input.equalsIgnoreCase("P"))
       // {
        //    GameManager.getInstance().Pause(playerStats);
        //    input = scanner.nextLine();
       // }


        System.out.println("\n It's your turn! How will you proceed..." );
        System.out.println("[1] Attack"); //damage
        System.out.println("[2] Heal " + playerStats.getHealCharges() + " left"); //heal if available
        System.out.println("[3] Use CC " + playerStats.getCcCharges() + " left");//concuss
        System.out.println("[4] Save Game");
        System.out.println("[5] Load Game");

        int choice = scanner.nextInt();

        switch (choice){

            //attack
            case 1:
                int dmg = playerStats.Damage();
                magicalGirlEnemy.getHit(dmg);
                System.out.println("You have hit your opponent for " + dmg +" damage");
                break;
            //heal
            case 2:
                playerStats.heal();
                System.out.println("You healed");
                break;
            //cc
            case 3:
                playerStats.useCC(magicalGirlEnemy);
                System.out.println("You concussed your opponent!");
                break;

                //save during game
            case 4:
                GameManager.getInstance().saveGame(playerStats);
                break;
            //load during game
            case 5:
                GameManager.getInstance().loadGame(playerStats);
                break;

            default:
                System.out.println("[INVALID INPUT] Enter a number from 1-5:");
                break;

        }

    }

    protected void DecorateCharacter(PlayerStats player) {
        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

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

        while (true) {
            int itemSelect = scanner.nextInt();

            switch (itemSelect) {
                case 1:
                    System.out.println(ANSI.MAGENTA + "You chose the health power-up." + ANSI.DEFAULT);
                    //create decorator
                    ICombatStats addHealthRing = new HealthRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addHealthRing);
                    break; //exit the switch loop and the while loop!
                case 2:
                    System.out.println(ANSI.RED + "You chose the strength power-up." + ANSI.DEFAULT);
                    ICombatStats addPowerRing = new PowerRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addPowerRing);
                    break;
                case 3:
                    System.out.println(ANSI.BLUE + "You chose the agility power-up." + ANSI.DEFAULT);
                    ICombatStats addAgilityRing = new AgilityRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addAgilityRing);
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
    protected void enemyTurn(PlayerStats magicalGirlEnemy, PlayerStats playerStats){

        //rnd enemy action probability out of 100
        int enemyAction = new Random().nextInt(100);

        //if cc is 20 or less + have a charge left it attacks
        if (enemyAction <= 20 && magicalGirlEnemy.getCcCharges() > 0){
            magicalGirlEnemy.useCC(playerStats);
            System.out.println("Your opponent used concuss on you. You skip a turn");

            return;

        }

        int dmg = magicalGirlEnemy.Damage(); //enemy calculates damage
        playerStats.getHit(dmg);
        System.out.println("The magical girl opponent attacked you for " + dmg + " damage!");
}

    //turn order
    protected PlayerStats determineTurnOrder(PlayerStats playerStats, PlayerStats magicalGirlEnemy){

        if (playerStats.getAgility() >= magicalGirlEnemy.getAgility()){ // compares agility
            System.out.println("\nYou have the upper-hand you get to go first!");
            return playerStats;
        }
        else{
            System.out.println("\n Your opponent goes first");
            return magicalGirlEnemy;
        }
}

    //displays health after each turn
    protected void printStatus(PlayerStats playerStats, PlayerStats magicalGirlEnemy){
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~ HEALTH STATUS ~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Your HP: " + playerStats.getCurrentHealth());
        System.out.println("Opponent HP: " + magicalGirlEnemy.getCurrentHealth());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
}

    //win or lose display
    protected void sayWinner(PlayerStats playerStats, PlayerStats magicalGirlEnemy){

        GameManager gm = GameManager.getInstance();
        //alive you win or die ya lose
        if (playerStats.isAlive()){
            gm.winGame(); // display the message when you win from Game Manager
        }
        else {
           gm.LoseGame(); //display the message when you lose from Game Manager
        }

}


}
