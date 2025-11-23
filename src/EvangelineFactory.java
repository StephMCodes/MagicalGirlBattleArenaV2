public class EvangelineFactory extends MagicalGirlFactory{
    @Override
    //create player stats from light magic fight style
    public PlayerStats createPlayer() {

        return new PlayerStats(MagicalGirlFightStyle.lightMagic);
    }
}
