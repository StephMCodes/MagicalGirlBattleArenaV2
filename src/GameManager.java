public class GameManager
{
    private static GameManager instance;

    private boolean gameWon;
    private boolean gameLose;

    private String playerName;

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

    public void winGame()
    {
        gameWon = true;
        gameLose = true;
        System.out.println( playerName + "YOU WIN THE BATTLE! :D ");
    }

    public void LoseGame()
    {
        gameWon = false;
        gameLose = true;
        System.out.println( playerName + " YOU LOST THE BATTLE...:( ");
    }

    public boolean isGameOver()
    {
        return gameLose;
    }
}
