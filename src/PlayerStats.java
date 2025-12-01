import java.util.Random;

public class PlayerStats {

    private ICombatStats fightStyle;
    public static MagicalGirlFightStyle ogStyle;
    private int currentHealth;
    private int healCharges = 3;
    private int healAmount = 20;
    private int ccCharges = 2;

    private boolean concussed = false;

    private static Random rnd = new Random();

    //constructor that sets up player stats implementing the interface ICombatStats
    public PlayerStats(ICombatStats fightStyle){
        this.fightStyle = fightStyle;
        this.currentHealth = fightStyle.getMaxHealth();
    }

    //it saves a copy of the player stats
    public PlayerSaveData createSaveData()
    {
        return new PlayerSaveData(currentHealth, healCharges, ccCharges);
    }

    //it restores back the player stats
    public void restoreSaveData(PlayerSaveData saveData)
    {
        this.currentHealth = saveData.getCurrentHealth();
        this.healCharges = saveData.getHealCharges();
        this.ccCharges = saveData.getCcCharges();
    }
    //get name of magical girl method
    public String getName(){
        return fightStyle.getName();
    }

    public ICombatStats getPlayerStyle()
    {
        return this.fightStyle;
    }

    public void setPlayerStyle(ICombatStats fightStyle)
    {
        this.fightStyle = fightStyle; //increase power of player
    }


    public int Damage(){
        //gets base strength value
        int base = fightStyle.getStrength();


        int randomExtraAttack = rnd.nextInt(6); //rnd bonus dmg between 0-5

        return base + randomExtraAttack; //adds base with random bonus damage

    }

    public void getHit(int damage){
        //health never drops below 0
        currentHealth = Math.max(0,currentHealth - damage); //compares result to 0 takes bigger value
    }

    public int heal(){
        //more than 0 heal charges. based on fight style get ur max health and health atm. Heals for 20
        if(healCharges <= 0){

            return 0; //healed nada
        }

        //stores health
        int previousHealth = currentHealth;

        currentHealth = Math.min(fightStyle.getMaxHealth(),currentHealth + healAmount); //min will take the smaller value of the two
        healCharges--;

        return currentHealth - previousHealth; // returns healed amount
    }

    //checks signs of life lol
    public boolean isAlive(){
        return currentHealth > 0;
    }

    //how many heals left
    public int getHealCharges(){
        return healCharges;
    }

    //gets the health atm
    public int getCurrentHealth(){
        return currentHealth;
    }

    //determines who goes first
    public int getAgility(){
        return fightStyle.getAgility();
    }

    //access from interface
    public int getStrength(){
        return fightStyle.getStrength();
    }
    public int getHealth(){
        return fightStyle.getMaxHealth();
    }

    //all opponents skip their next turn when cc'd
    public void useCC(PlayerStats magicalGirl){
        if (ccCharges <= 0 ){
            System.out.println("No more CC charges! Try something else... :(");
            return;
        }
        else {

            magicalGirl.concussed = true;
            //cc'd 1/2 of base attack dmg
            int ccDamage = fightStyle.getStrength() / 2;
            System.out.println(ANSI.YELLOW + "Concuss attack did " + ccDamage + " damage!" + ANSI.DEFAULT);
            magicalGirl.getHit(ccDamage);
            ccCharges--;
        }

    }

    //bool to check if cc'd then skips turn
    public boolean skipTurn(){
        if (concussed == true){
            concussed = false;
            return true;
        }
        return false;
    }

    public int getCcCharges(){
        return ccCharges;
    }

    //reset for new battle
    public void resetStats(){
        healCharges = 3;
        ccCharges = 2;
        currentHealth = fightStyle.getMaxHealth();
    }



}
