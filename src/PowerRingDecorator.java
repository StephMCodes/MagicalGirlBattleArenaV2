public class PowerRingDecorator extends CharacterDecorator{

    public PowerRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    } //it wraps around ICombatStats interface and not the enum

    public  int getStrength()
    {
        return super.getStrength() + 5; //every time we get the str, it will have a bonus
    }
    @Override
    public String getName() {
        //returns magical girl name
        return combatStats.getName();
    }
}
