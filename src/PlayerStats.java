import java.util.Random;

public class PlayerStats {

    private ICombatStats fightStyle;
    private int currentHealth;
    private int healCharges = 3;
    private int healAmount = 20;
    private int ccCharges = 2;

    private boolean concussed = false;

    private static Random rnd = new Random();

    public PlayerStats(ICombatStats fightStyle){
        this.fightStyle = fightStyle;
        this.currentHealth = fightStyle.getMaxHealth();
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

    public void heal(){
        //more than 0 heal charges. based on fight style get ur max health and health atm. Heals for 20
        if(healCharges > 0){
            currentHealth = Math.min(fightStyle.getMaxHealth(),currentHealth + healAmount); //min will take the smaller value of the two
            healCharges--;
        }
    }

    //checks signs of life lol
    public boolean isAlive(){
        return currentHealth > 0;
    }

    //how many heals left
    public int getHealCharges(){
        return healCharges;
    }
    //resets for new battle
    public void resetHeals(){
        healCharges = 3;
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
    public void useCC(PlayerStats magicalGirls){
        if (ccCharges <= 0 ){
            System.out.println("no cc's left :( ");
            return;
        }
        magicalGirls.concussed = true;
        ccCharges--;

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
