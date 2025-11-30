public class EndlessBattleSystem  extends BattleSystem{

    @Override
    protected PlayerStats createMagicalGirlEnemy(PlayerStats playerStats) {
        //creates randomized enemy factory
        RandomizedEnemyFactory enemyFactory = new RandomizedEnemyFactory(playerStats.getPlayerStyle());
        return enemyFactory.createPlayer(playerStats.ogStyle); //returns enemy as an object
    }

    //moved from main to here for clarity and runs the specific game mode
    @Override
    public void gameMode(PlayerStats playerStats) {

        int battleAmount = 1;
        boolean battleWon = true;
        //battle loop 3 times
        System.out.println("The crowd roars as you enter the arena, battling until you drop!");

        do
        {

            System.out.println("\n~~~~~~~~~~~~~BATTLE " + battleAmount + "~~~~~~~~~~~~~");

            battleWon = startBattle(playerStats); //runs battle with template design pattern

            if (battleWon == true )
            {
                //you won
                DecorateCharacter(playerStats); //decorates stats
                playerStats.resetStats(); //resets heals and cc's
                battleAmount++;
            }

        }while (battleWon==true);
        //you lost
        playerStats.resetStats(); //resets heals and cc's
        System.out.println("Battles fought in ENDLESS MODE: " + battleAmount);
        System.out.println("Press [ENTER] to continue.");

    }
}
