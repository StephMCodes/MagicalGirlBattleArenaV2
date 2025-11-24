public class HealthRingDecorator extends CharacterDecorator {

    public HealthRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    }

    public int getMaxHealth() {
        return super.getMaxHealth() + 5;
    }
}
