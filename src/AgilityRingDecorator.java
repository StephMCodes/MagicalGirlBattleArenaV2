public class AgilityRingDecorator extends CharacterDecorator{

    public AgilityRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    }

    public  int getAgility()
    {
        return super.getAgility() + 5;
    }
}
