public enum MagicalGirlFightStyle implements ICombatStats {

    /* each fight style/ magical girl with respective stats*/
    gun(100, 10, 12, "Esme"), //higher
    jumboHammer(90, 18, 4, "Astrid"),
    darkMagic(95, 12, 10, "Iris"),
    lightMagic(85, 8, 15, "Evangeline");


    private int maxHealth;
    private int strength; //base attack strength
    private int agility; //turn order
    private String name;



    MagicalGirlFightStyle(int maxHealth, int strength, int agility, String name){
        this.maxHealth = maxHealth;
        this.strength = strength;
        this.agility = agility;
        this.name = name;
    }

    //getting these from the shared interface now
    public int getMaxHealth()
    {
        return maxHealth;
    }
    public int getStrength(){
        return strength;
    }
    public int getAgility()
    {
        return agility;
    }

    public String getName(){
        return name;
    }
}
