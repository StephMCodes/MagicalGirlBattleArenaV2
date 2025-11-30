public class HealthRingDecorator extends CharacterDecorator {

    public HealthRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    } //it wraps around ICombatStats interface and not the enum

    public int getMaxHealth() {
        return super.getMaxHealth() + 5; //every time we get the hp, it will have a bonus
    }
    @Override
    public String getName() {
        //returns magical girl name
        return combatStats.getName();
    }
}
