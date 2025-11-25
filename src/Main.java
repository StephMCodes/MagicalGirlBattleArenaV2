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

        System.out.println("Choose one of four Magical Girl to incarnate: ");
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

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("Enter a number from [1-4]: ");

        //no choice yet
        MagicalGirlFactory magicalGirlFactory = null;
//we avoid an exception if user inputs a string and not in
        int characterSelect = 0;

        while (true) {

            String characterSelectString = scanner.nextLine();

            while (true) {
                try {
                    characterSelect = Integer.parseInt(characterSelectString);
                    break;
                } catch (Exception e) {
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                    characterSelectString = scanner.nextLine();

                }
            }
            while (true) {

                switch (characterSelect) {
                    case 1:
                        magicalGirlFactory = new EsmeFactory();
                        System.out.println(ANSI.BLUE + "You chose Esme - Sharpshooter");
                        System.out.println("'Nothing I can't do.'" + ANSI.DEFAULT);


                        GameManager.getInstance().setPlayerName("Esme");

                        break; //exit the switch loop and the while loop!
                    case 2:
                        magicalGirlFactory = new AstridFactory();
                        System.out.println(ANSI.RED + "You chose Astrid - Jumbo Hammer");
                        System.out.println("'ALRIGHT, LET'S GET KICKING!!!'" + ANSI.DEFAULT);


                        GameManager.getInstance().setPlayerName("Astrid");

                        break;
                    case 3:
                        magicalGirlFactory = new IrisFactory();
                        System.out.println(ANSI.MAGENTA + "You chose Iris - Dark Mage");
                        System.out.println("'I plan for every outcome.'" + ANSI.DEFAULT);


                        GameManager.getInstance().setPlayerName("Iris");

                        break;
                    case 4:
                        magicalGirlFactory = new EvangelineFactory();
                        System.out.println(ANSI.YELLOW + "You chose Evangeline - Light Mage");
                        System.out.println("'Let's show them what I got!'" + ANSI.DEFAULT);

                        GameManager.getInstance().setPlayerName("Evangeline");

                        break;
                    default:
                        System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                        continue;
                }
                break;
            }

            //magical girl factory chosen now creates player
            return magicalGirlFactory.createPlayer();
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
        System.out.println("[1] Start Base Game");
        System.out.println("[2] Start Double Game Mode");
        System.out.println("[3] Start Endless Game Mode");
        System.out.println("[4] View Credits");
        System.out.println("");

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

                    System.out.println("Player Base Style: " + playerStats.getPlayerStyle());
                    System.out.println("Max Health: " + playerStats.getHealth());
                    System.out.println("Current Health: " + playerStats.getCurrentHealth());
                    System.out.println("Strength: " + playerStats.getStrength());
                    System.out.println("Agility: " + playerStats.getAgility());
                    System.out.println("Heal Charges: " + playerStats.getHealCharges());
                    System.out.println("CC Charges: " + playerStats.getCcCharges());

                    //creates battle system
                    BattleSystem battleArena = new RegularBattleSystem();

                    //battle loop 3 times
                    for(int i = 1; i<=3; i++){

                        System.out.println("\n~~~~~~~~~~~~~BATTLE " + i + "~~~~~~~~~~~~~");

                        battleArena.startBattle(playerStats); //runs battle with template design pattern
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
                    System.out.println("PROGRAMMERS:");
                    System.out.println(ANSI.MAGENTA + "Bella Perez");
                    System.out.println(ANSI.RED + "Ruth Garcia");
                    System.out.println(ANSI.BLUE + "Stephanie Michiu" + ANSI.DEFAULT);

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

//    static void DecorateCharacter(PlayerStats player) {
//        //to take user input we use scanner class
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Choose one of three power accessories: ");
//        System.out.println("");
//
//        System.out.println(ANSI.MAGENTA + "[1] Health Ring");
//        System.out.println("HP + 5");
//        System.out.println("Fun fact: The gem is shaped like a heart.");
//        System.out.println("");
//
//        System.out.println(ANSI.RED + "[2] Strength Ring");
//        System.out.println("Strength + 5");
//        System.out.println("Fun fact: It glows like fire.");
//        System.out.println("");
//
//        System.out.println(ANSI.BLUE + "[3] Agility Ring");
//        System.out.println("Agility + 5");
//        System.out.println("Fun fact: It feels weightless.");
//        System.out.println("" + ANSI.DEFAULT);
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//        System.out.println("Enter a number from [1-3]: ");
//
//        //no choice yet
//        MagicalGirlFactory magicalGirlFactory = null;
//
//        while (true) {
//            int itemSelect = scanner.nextInt();
//
//            switch (itemSelect) {
//                case 1:
//                    System.out.println(ANSI.MAGENTA + "You chose the health power-up." + ANSI.DEFAULT);
//                    //create decorator
//                    ICombatStats addHealthRing = new HealthRingDecorator(player.getPlayerStyle());
//                    player.setPlayerStyle(addHealthRing);
//                    break; //exit the switch loop and the while loop!
//                case 2:
//                    System.out.println(ANSI.RED + "You chose the strength power-up." + ANSI.DEFAULT);
//                    ICombatStats addPowerRing = new PowerRingDecorator(player.getPlayerStyle());
//                    player.setPlayerStyle(addPowerRing);
//                    break;
//                case 3:
//                    System.out.println(ANSI.BLUE + "You chose the agility power-up." + ANSI.DEFAULT);
//                    ICombatStats addAgilityRing = new AgilityRingDecorator(player.getPlayerStyle());
//                    player.setPlayerStyle(addAgilityRing);
//                    break;
//                default:
//                    System.out.println("[INVALID INPUT] Enter a number from 1-3: ");
//                    continue;
//            }
//            break;
//        }
//
//        //wrap around your stats
//
//    }
    }

