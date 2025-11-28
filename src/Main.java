import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

        DisplayMenu();

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
                    PlayerStats.ogStyle = "gun";

                    return magicalGirlFactory.createPlayer();
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
                    PlayerStats.ogStyle = "hammer";
                    return magicalGirlFactory.createPlayer();

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
                    PlayerStats.ogStyle = "darkMagic";

                    return magicalGirlFactory.createPlayer();

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
                    PlayerStats.ogStyle = "lightMagic";

                    return magicalGirlFactory.createPlayer();
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
        System.out.println(ANSI.RED + "[2] Start Double Game Mode");
        System.out.println(ANSI.MAGENTA + "[3] Start Endless Game Mode");
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



                    //after we finish a battle let the player get a powerup of choice
                    //DecorateCharacter(playerStats);

                    /*System.out.println("Player Base Style: " + playerStats.getPlayerStyle());
                    System.out.println("Max Health: " + playerStats.getHealth());
                    System.out.println("Current Health: " + playerStats.getCurrentHealth());
                    System.out.println("Strength: " + playerStats.getStrength());
                    System.out.println("Agility: " + playerStats.getAgility());
                    System.out.println("Heal Charges: " + playerStats.getHealCharges());
                    System.out.println("CC Charges: " + playerStats.getCcCharges());*/

                    //creates battle system
                    BattleSystem battleArena = new RegularBattleSystem();

                    //battle loop 3 times
                    for(int i = 1; i<=3; i++){

                        System.out.println("\n~~~~~~~~~~~~~BATTLE " + i + "~~~~~~~~~~~~~");

                        battleArena.startBattle(playerStats); //runs battle with template design pattern
                        //decorate after resetting your health
                        battleArena.DecorateCharacter(playerStats); //decorates stats
                        playerStats.resetStats(); //resets heals and cc's

                }
                    break; //exit the switch loop and the while loop!
                case 2:
                    //start game mode 2

                    break;
                case 3:
                    //start game mode 3

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

