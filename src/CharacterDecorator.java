public abstract class CharacterDecorator implements ICombatStats {

    protected ICombatStats combatStats; //the object we are decorating

    //constructor
    public CharacterDecorator(ICombatStats combatStats){
        this.combatStats=combatStats;
    }

    //methods
    //just reference to the actual stats to handle this
    public int getMaxHealth() {
        return combatStats.getMaxHealth();
    }
    public int getStrength() {
        return combatStats.getStrength();
    }

    public int getAgility() {
        return combatStats.getAgility();
    }
}
