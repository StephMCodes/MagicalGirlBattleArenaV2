import java.util.Random;

public class RandomizedEnemyFactory extends MagicalGirlFactory {

    //chosen magical girl stats
    private ICombatStats fightStyle;

    //constructor gets and saves player fight type
    public RandomizedEnemyFactory(ICombatStats fightStyle){
        this.fightStyle = fightStyle; //saves pick
    }

    @Override
    public PlayerStats createPlayer() {
        //array of magical girl fight styles
        MagicalGirlFightStyle[] fightStyles = MagicalGirlFightStyle.values();
        Random rnd = new Random();

        MagicalGirlFightStyle randomFightStyle;

        do{
            randomFightStyle = fightStyles[rnd.nextInt(fightStyles.length)]; //randomize enemies from fightstyles

        } while(randomFightStyle == fightStyle); //makes sure it's not the same as player's fight style

        //returns built magical girl enemy
        return new PlayerStats(randomFightStyle);
    }
}
