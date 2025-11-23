public class AstridFactory extends MagicalGirlFactory {

    @Override
    //create player stats from jumboHammer fight style
    public PlayerStats createPlayer() {

        return new PlayerStats(MagicalGirlFightStyle.jumboHammer);
    }
}
