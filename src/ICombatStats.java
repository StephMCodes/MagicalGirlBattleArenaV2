public interface ICombatStats { //I cant extend an enum, so I make them share an interface for polymorphism

    int getMaxHealth();
    int getStrength();
    int getAgility();
    String getName();

}
