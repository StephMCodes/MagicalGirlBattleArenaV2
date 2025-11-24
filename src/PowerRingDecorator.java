public class PowerRingDecorator extends CharacterDecorator{

    public PowerRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    }

    public  int getStrength()
    {
        return super.getStrength() + 5;
    }
}
