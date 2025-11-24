import java.util.Scanner;

//using template method
public abstract class BattleSystem {

    //final to amke sure subclasses don't interfere
    public final void startBattle(PlayerStats playerStats){

        //create enemy from subclass regular battle atm
        PlayerStats magicalGirlEnemy = createMagicalGirlEnemy();



    }

    private PlayerStats createMagicalGirlEnemy() {
        return null;
    }

}
