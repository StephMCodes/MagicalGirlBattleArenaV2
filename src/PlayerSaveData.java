public class PlayerSaveData {
    private final int currentHealth;
    private final int healCharges;
    private final int ccCharges;

    public PlayerSaveData(int currentHealth, int healCharges, int ccCharges)
    {
        this.currentHealth = currentHealth;
        this.healCharges = healCharges;
        this.ccCharges = ccCharges;

    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }
    public int getHealCharges()
    {
        return healCharges;
    }

    public int getCcCharges()
    {
        return ccCharges;
    }
}
