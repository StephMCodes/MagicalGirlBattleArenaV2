import java.util.Random;

public class RandomizedEnemyFactory extends MagicalGirlFactory {

    @Override
    public PlayerStats createPlayer() {
        //array of magicalgirl fightstyles
        MagicalGirlFightStyle[] fightStyles = MagicalGirlFightStyle.values();

        Random rnd = new Random();
        int selectedStyle = rnd.nextInt(fightStyles.length); //randomize enemies from fightstyles

        //returns built magical girl enemy
        return new PlayerStats(fightStyles[selectedStyle]);
    }
}
