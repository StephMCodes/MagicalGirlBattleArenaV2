public class EvangelineFactory extends MagicalGirlFactory{
    @Override
    //create player stats from light magic fight style
    public PlayerStats createPlayer(MagicalGirlFightStyle fightStyle) {

        return new PlayerStats(MagicalGirlFightStyle.lightMagic);
    }
}
