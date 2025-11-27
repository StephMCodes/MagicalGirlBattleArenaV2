public enum MagicalGirlFightStyle implements ICombatStats {

        /* each fight style/ magical girl with respective stats*/
        gun(100, 10, 12), //higher
        jumboHammer(90, 18, 4),
        darkMagic(95, 12, 10),
        lightMagic(85, 8, 15);


        private int maxHealth;
        private int strength; //base attack strength
        private int agility; //turn order


        MagicalGirlFightStyle(int maxHealth, int strength, int agility){
            this.maxHealth = maxHealth;
            this.strength = strength;
            this.agility = agility;
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
}
