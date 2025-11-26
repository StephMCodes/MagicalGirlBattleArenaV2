public class GameManager //singleton design pattern
{
    private static GameManager instance;

    private boolean gameWon;
    private boolean gameLose;

    private String playerName;

    private PlayerSaveData saveData;

    private GameManager()
    {
        gameWon = false;
        gameLose = false;
        playerName = "";
    }

    public static GameManager getInstance()
    {
        if(instance == null)
        {
            instance = new GameManager();
        }
        return instance;
    }

    public void setPlayerName(String name)
    {
        this.playerName = name;
    }

    public void saveGame(PlayerStats player)
    {
        saveData = player.createSaveData();
        System.out.println(" Game has been saved!!");

    }

    public void loadGame(PlayerStats player)
    {
        if(saveData == null)
        {
            System.out.println("Game has been not found. Please save a game first.");
            return;
        }
        player.restoreSaveData(saveData);
        System.out.println("Loading the game!!");
    }

    //winning logic
    public void winGame()
    {
        gameWon = true;
        gameLose = true;
        System.out.println( playerName + " WINS THE BATTLE! :D ");
    }

    //losing logic
    public void LoseGame()
    {
        gameWon = false;
        gameLose = true;
        System.out.println( playerName + " LOST THE BATTLE...:( ");
    }

    public boolean isGameOver()
    {
        return gameLose;
    }
}
