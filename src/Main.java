import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

        DisplayMenu();

    }

    static PlayerStats MakeRandomCharacter()
    {
        //no choice yet
        MagicalGirlFactory magicalGirlFactory = null;

        int randomCharacter = new Random().nextInt(4); //FIXED
        switch (randomCharacter)
        {
            case 0:
                magicalGirlFactory = new EsmeFactory();
                GameManager.getInstance().setPlayerName("Esme");
                PlayerStats.ogStyle = MagicalGirlFightStyle.gun;
                System.out.println(ANSI.BLUE + "Your random character is... ESME" + ANSI.DEFAULT);
                return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.gun);

            case 1:
                magicalGirlFactory = new AstridFactory();
                GameManager.getInstance().setPlayerName("Astrid");
                PlayerStats.ogStyle = MagicalGirlFightStyle.jumboHammer;
                System.out.println(ANSI.RED + "Your random character is... ASTRID!" + ANSI.DEFAULT);
                return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.jumboHammer);

            case 2:
                magicalGirlFactory = new IrisFactory();
                GameManager.getInstance().setPlayerName("Iris");
                PlayerStats.ogStyle = MagicalGirlFightStyle.darkMagic;
                System.out.println(ANSI.MAGENTA + "Your random character is... IRIS!" + ANSI.DEFAULT);
                return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.darkMagic);

            case 3:
                magicalGirlFactory = new EvangelineFactory();
                GameManager.getInstance().setPlayerName("Evangeline");
                PlayerStats.ogStyle = MagicalGirlFightStyle.lightMagic;
                System.out.println(ANSI.YELLOW + "Your random character is... EVANGELINE!" + ANSI.DEFAULT);

                return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.lightMagic);

        }
        System.out.println(ANSI.YELLOW + "DEBUG");
        return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.lightMagic);

    }
    static PlayerStats MakeCharacter() {
        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose one of four Magical Girl to incarnate in the ring1: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("");

        System.out.println(ANSI.BLUE + "[1] Esme - Sharpshooter");
        System.out.println("HP: 100, Attack: 10, Agility: 12");
        System.out.println("Fun fact: A natural leader with effortless cool.");
        System.out.println("");

        System.out.println(ANSI.RED + "[2] Astrid - Jumbo Hammer");
        System.out.println("HP: 90, Attack: 18, Agility: 4");
        System.out.println("Fun fact: Her temper is as big as her heart!");
        System.out.println("");

        System.out.println(ANSI.MAGENTA + "[3] Iris - Dark Mage");
        System.out.println("HP: 95, Attack: 12, Agility: 10");
        System.out.println("Fun fact: Always has her nose in a book, plus a great sense of fashion.");
        System.out.println("");

        System.out.println(ANSI.YELLOW + "[4] Evangeline - Light Mage");
        System.out.println("HP: 85, Attack: 8, Agility: 15");
        System.out.println("Fun fact: She likes a good cup of tea!" + ANSI.DEFAULT);
        System.out.println("");


        System.out.println("Enter a number from [1-4]: ");

        //no choice yet
        MagicalGirlFactory magicalGirlFactory = null;
//we avoid an exception if user inputs a string and not in
        int characterSelect = 0;
        while (true) {
            while (true) {

                String characterSelectString = scanner.nextLine();

                try {
                    characterSelect = Integer.parseInt(characterSelectString);
                    break;
                } catch (Exception e) {
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");

                }
            }


            switch (characterSelect) {
                case 1:
                    magicalGirlFactory = new EsmeFactory();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(ANSI.BLUE + "You chose Esme - Sharpshooter");
                    System.out.println("'Nothing I can't do.'" + ANSI.DEFAULT);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Are you ready to prove you are the strongest magical girl of all?");
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();

                    GameManager.getInstance().setPlayerName("Esme");
                    PlayerStats.ogStyle = MagicalGirlFightStyle.gun;

                    return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.gun);
                //exit the switch loop and the while loop!
                case 2:
                    magicalGirlFactory = new AstridFactory();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(ANSI.RED + "You chose Astrid - Jumbo Hammer");
                    System.out.println("'ALRIGHT, LET'S GET KICKING!!!'" + ANSI.DEFAULT);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Are you ready to prove you are the strongest magical girl of all?");
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();

                    GameManager.getInstance().setPlayerName("Astrid");
                    PlayerStats.ogStyle = MagicalGirlFightStyle.jumboHammer;
                    return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.jumboHammer);

                case 3:
                    magicalGirlFactory = new IrisFactory();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(ANSI.MAGENTA + "You chose Iris - Dark Mage");
                    System.out.println("'I plan for every outcome.'" + ANSI.DEFAULT);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Are you ready to prove you are the strongest magical girl of all?");
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();

                    GameManager.getInstance().setPlayerName("Iris");
                    PlayerStats.ogStyle = MagicalGirlFightStyle.darkMagic;

                    return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.darkMagic);

                case 4:
                    magicalGirlFactory = new EvangelineFactory();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(ANSI.YELLOW + "You chose Evangeline - Light Mage");
                    System.out.println("'Let's show them what I got!'" + ANSI.DEFAULT);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Are you ready to prove you are the strongest magical girl of all?");
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    GameManager.getInstance().setPlayerName("Evangeline");
                    PlayerStats.ogStyle = MagicalGirlFightStyle.lightMagic;

                    return magicalGirlFactory.createPlayer(MagicalGirlFightStyle.lightMagic);
                default:
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                    break;
            }
            //magical girl factory chosen now creates player
        }
    }

    static void DisplayMenu()
    {
        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("WELCOME TO MAGICAL GIRL BATTLE ARENA");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose one out of four magical girl characters, each with their own name, stats and abilities, to enter into the arena.");
        System.out.println("You then battle the remaining characters to determine who is the champion that will walk away with a magical artefact!");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( ANSI.BLUE + "[1] Start Base Game");
        System.out.println(ANSI.RED + "[2] Start Endless Mode");
        System.out.println(ANSI.MAGENTA + "[3] Start Chaos Mode");
        System.out.println(ANSI.YELLOW + "[4] View Credits");
        System.out.println(""+ ANSI.DEFAULT);

        //we avoid an exception if user inputs a string and not in
        int menuSelect = 0;

        while (true) {

            System.out.println("Enter [1-4]: ");
            String menuSelectString = scanner.nextLine();

            while (true) {
                try {
                    menuSelect = Integer.parseInt(menuSelectString);
                    break;
                } catch (Exception e) {
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                    menuSelectString = scanner.nextLine();

                }
            }

            switch (menuSelect) {
                case 1:
                    //start game
                    //make our player
                    PlayerStats playerStats = MakeCharacter();

                    //creates battle system
                    BattleSystem battleArena = new RegularBattleSystem();

                    boolean battleWon = false;

                    System.out.println("You enter the arena, ready to face the other combatants for 3 battles!");

                    //battle loop 3 times
                    for (int i = 1; i <= 3; i++) {

                        System.out.println("\n~~~~~~~~~~~~~BATTLE " + i + "~~~~~~~~~~~~~");

                        battleWon = battleArena.startBattle(playerStats); //runs battle with template design pattern

                        if (battleWon == true) {
                            //decorate after resetting your health
                            battleArena.DecorateCharacter(playerStats); //decorates stats
                            playerStats.resetStats(); //resets heals and cc's
                        } else {
                            playerStats.resetStats(); //resets heals and cc's
                            DisplayMenu();
                            break;
                        }
                    }
                    break; //exit the switch loop and the while loop!
                case 2:
                    //start game mode 2
                    //ENDLESS!!!!!!!!!
                    playerStats = MakeCharacter();

                    //creates battle system
                    battleArena = new RegularBattleSystem();

                    int battleAmount = 1;
                    battleWon = true;
                    //battle loop 3 times
                    System.out.println("The crowd roars as you enter the arena, battling until you drop!");

                    do
                    {

                        System.out.println("\n~~~~~~~~~~~~~BATTLE " + battleAmount + "~~~~~~~~~~~~~");

                        battleWon = battleArena.startBattle(playerStats); //runs battle with template design pattern

                        if (battleWon == true )
                        {
                            //you won
                            battleArena.DecorateCharacter(playerStats); //decorates stats
                            playerStats.resetStats(); //resets heals and cc's
                            battleAmount++;
                        }

                    }while (battleWon==true);
                    //you lost
                    playerStats.resetStats(); //resets heals and cc's
                    System.out.println("Battles fought in ENDLESS MODE: " + battleAmount);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    DisplayMenu();
                    break;

                case 3:
                    //creates battle system
                    battleArena = new RegularBattleSystem();

                    battleAmount = 1;
                    battleWon = true;
                    //battle loop 3 times
                    System.out.println("The arena is covered in hexes that swap you and the other combatant's forms randomly! How long will you last?");

                    do
                    {
                        playerStats = MakeRandomCharacter();

                        System.out.println("\n~~~~~~~~~~~~~BATTLE " + battleAmount + "~~~~~~~~~~~~~");

                        battleWon = battleArena.startBattle(playerStats); //runs battle with template design pattern

                        if (battleWon == true )
                        {
                            //you won
                            //battleArena.DecorateCharacter(playerStats); //decorates stats
                            playerStats.resetStats(); //resets heals and cc's
                            battleAmount++;
                        }

                    }while (battleWon==true);
                    //you lost
                    playerStats.resetStats(); //resets heals and cc's
                    System.out.println("Battles fought in CHAOS MODE: " + battleAmount);
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    DisplayMenu();
                    break;
                case 4:
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("PROGRAMMERS:");
                    System.out.println(ANSI.MAGENTA + "Bella Perez");
                    System.out.println(ANSI.RED + "Ruth Garcia");
                    System.out.println(ANSI.BLUE + "Stephanie Michiu" + ANSI.DEFAULT);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    System.out.println("Thank you for playing our game.");
                    System.out.println("Press [ENTER] to continue.");
                    scanner.nextLine();
                    DisplayMenu();
                    break;
                default:
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                    continue;

            }
            break;
        }
    }
    }

