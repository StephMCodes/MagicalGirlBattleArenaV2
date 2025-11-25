import java.util.Scanner;

//using template method
public abstract class BattleSystem {

    //final to amke sure subclasses don't interfere
    public final void startBattle(PlayerStats playerStats){

        //create enemy from subclass regular battle atm
        PlayerStats magicalGirlEnemy = createMagicalGirlEnemy();

        System.out.println("Your 1st magical girl opponent appears...");
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

            //displays current hp
            printStatus(magicalGirlEnemy, playerStats);

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

        System.out.println("\n It's your turn! How will you proceed..." );
        System.out.println("[1] Attack"); //damage
        System.out.println("[2] Heal " + playerStats.getHealCharges() + " left"); //heal if available
        System.out.println("[3] Use CC " + playerStats.getCcCharges() + " left");//concuss

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

            default:
                System.out.println("[INVALID INPUT] Enter a number from 1-3:");
                break;

        }

    }

    //magical girl enemy turn
    protected void enemyTurn(PlayerStats magicalGirlEnemy, PlayerStats playerStats){
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

        //alive you win or die ya lose
        if (playerStats.isAlive()){
            System.out.println("\nYou won the battle! ");
        }
        else {
            System.out.println("\nYou were defeated");
        }

}



}
