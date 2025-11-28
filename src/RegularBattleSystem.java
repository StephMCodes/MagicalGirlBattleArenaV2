public class RegularBattleSystem extends BattleSystem {

   //adding protected class method
   protected PlayerStats createMagicalGirlEnemy(PlayerStats playerStats){

        //creates randomized enemy factory
        RandomizedEnemyFactory enemyFactory = new RandomizedEnemyFactory(playerStats.getPlayerStyle());
        return enemyFactory.createPlayer(playerStats.ogStyle); //returns enemy as an object
    }
}
