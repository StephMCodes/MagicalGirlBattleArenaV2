import java.util.Scanner;

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

    public void Pause(PlayerStats playerStats)
    {
        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.println("\n === PAUSE MENU ===");
            System.out.println("[1] Resume Game");
            System.out.println("[2] Exit Game");

            String input = scanner.nextLine();

            switch (input)
            {
                case "1": System.out.println("Resuming Game...");
                return;

                case "2": System.out.println("Thank you for playing! You can come back anytime :D!");
                System.exit(0);
                default: System.out.println("Invalid, please enter 1-2.");
            }
        }
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
