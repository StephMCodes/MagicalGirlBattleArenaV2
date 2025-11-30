public class AgilityRingDecorator extends CharacterDecorator{

    public AgilityRingDecorator(ICombatStats combatStats) {
        super(combatStats);
    } //it wraps around ICombatStats interface and not the enum

    public  int getAgility()
    {
        return super.getAgility() + 5;
    } //every time we get the agility, it will have a bonus

    @Override
    public String getName() {
        //returns magical girl name
        return combatStats.getName();
    }
}
