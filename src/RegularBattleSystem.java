public class RegularBattleSystem extends BattleSystem {

   //adding protected class method
    protected PlayerStats createMagicalGirlEnemy(){

        //creates randomized enemy factory
        RandomizedEnemyFactory enemyFactory = new RandomizedEnemyFactory();
        return enemyFactory.createPlayer(); //returns enemy as an object
    }
}
