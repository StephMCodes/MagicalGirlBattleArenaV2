public class IrisFactory extends MagicalGirlFactory{

    @Override
    //create player stats from dark magic fight style
    public PlayerStats createPlayer() {

        return new PlayerStats(MagicalGirlFightStyle.darkMagic);
    }
}
