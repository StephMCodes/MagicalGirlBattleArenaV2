public class PowerRingDecorator extends CharacterDecorator{

    public PowerRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    }

    public  int getStrength()
    {
        return super.getStrength() + 5;
    }
    @Override
    public String getName() {
        //returns magical girl name
        return combatStats.getName();
    }
}
