public class EsmeFactory  extends MagicalGirlFactory{

    @Override
    //create player stats from gun fight style
    public PlayerStats createPlayer(MagicalGirlFightStyle fightStyle) {

        return new PlayerStats(MagicalGirlFightStyle.gun);
    }
}
