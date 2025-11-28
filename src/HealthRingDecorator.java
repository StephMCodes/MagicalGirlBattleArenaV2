public class HealthRingDecorator extends CharacterDecorator {

    public HealthRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    }

    public int getMaxHealth() {
        return super.getMaxHealth() + 5;
    }
    @Override
    public String getName() {
        //returns magical girl name
        return combatStats.getName();
    }
}
