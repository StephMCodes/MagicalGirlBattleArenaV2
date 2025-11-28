public class AgilityRingDecorator extends CharacterDecorator{

    public AgilityRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    }

    public  int getAgility()
    {
        return super.getAgility() + 5;
    }

    @Override
    public String getName() {
        //returns magical girl name
        return combatStats.getName();
    }
}
