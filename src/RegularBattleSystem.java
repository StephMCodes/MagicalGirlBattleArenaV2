public class RegularBattleSystem extends BattleSystem {

   //adding protected class method
   protected PlayerStats createMagicalGirlEnemy(PlayerStats playerStats){

        //creates randomized enemy factory
        RandomizedEnemyFactory enemyFactory = new RandomizedEnemyFactory(playerStats.getPlayerStyle());
        return enemyFactory.createPlayer(playerStats.ogStyle); //returns enemy as an object
    }

    //moved from main to here for clarity and runs the specific game mode
    @Override
    public void gameMode(PlayerStats playerStats) {

        boolean battleWon = false;

        System.out.println("You enter the arena, ready to face the other combatants for 3 battles!");

        //battle loop 3 times
        for (int i = 1; i <= 3; i++) {

            System.out.println("\n~~~~~~~~~~~~~BATTLE " + i + "~~~~~~~~~~~~~");

            battleWon = startBattle(playerStats); //runs battle with template design pattern

            if (battleWon == true) {
                //decorate after resetting your health
                DecorateCharacter(playerStats); //decorates stats
                playerStats.resetStats(); //resets heals and cc's
            } else {
                playerStats.resetStats(); //resets heals and cc's

                break;
            }
        }

    }
}
