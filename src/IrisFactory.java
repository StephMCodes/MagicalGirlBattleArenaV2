public class IrisFactory extends MagicalGirlFactory{

    @Override
    //create player stats from dark magic fight style
    public PlayerStats createPlayer(MagicalGirlFightStyle fightStyle) {

        return new PlayerStats(MagicalGirlFightStyle.darkMagic);
    }
}
