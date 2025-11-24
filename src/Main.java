import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("WELCOME TO MAGICAL GIRL BATTLE ARENA");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //IF NO SAVED GAME
        //make our player
        PlayerStats playerStats = MakeCharacter();

        //after we finish a battle let the player get a powerup of choice
        DecorateCharacter(playerStats);


        System.out.println("Player Base Style: " + playerStats.getPlayerStyle());
        System.out.println("Max Health: " + playerStats.getHealth());
        System.out.println("Current Health: " + playerStats.getCurrentHealth());
        System.out.println("Strength: " + playerStats.getStrength());
        System.out.println("Agility: " + playerStats.getAgility());
        System.out.println("Heal Charges: " + playerStats.getHealCharges());
        System.out.println("CC Charges: " + playerStats.getCcCharges());


    }

    static PlayerStats MakeCharacter()
    {
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

        while (true) {
            int characterSelect = scanner.nextInt();

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

    static void DecorateCharacter(PlayerStats player) {
        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose one of three power accessories: ");
        System.out.println("");

        System.out.println(ANSI.MAGENTA + "[1] Health Ring");
        System.out.println("HP + 5");
        System.out.println("Fun fact: The gem is shaped like a heart.");
        System.out.println("");

        System.out.println(ANSI.RED + "[2] Strength Ring");
        System.out.println("Strength + 5");
        System.out.println("Fun fact: It glows like fire.");
        System.out.println("");

        System.out.println(ANSI.BLUE + "[3] Agility Ring");
        System.out.println("Agility + 5");
        System.out.println("Fun fact: It feels weightless.");
        System.out.println("" + ANSI.DEFAULT);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("Enter a number from [1-3]: ");

        //no choice yet
        MagicalGirlFactory magicalGirlFactory = null;

        while (true) {
            int itemSelect = scanner.nextInt();

            switch (itemSelect) {
                case 1:
                    System.out.println(ANSI.MAGENTA + "You chose the health power-up." + ANSI.DEFAULT);
                    //create decorator
                    ICombatStats addHealthRing = new HealthRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addHealthRing);
                    break; //exit the switch loop and the while loop!
                case 2:
                    System.out.println(ANSI.RED + "You chose the strength power-up." + ANSI.DEFAULT);
                    ICombatStats addPowerRing = new PowerRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addPowerRing);
                    break;
                case 3:
                    System.out.println(ANSI.BLUE + "You chose the agility power-up." + ANSI.DEFAULT);
                    ICombatStats addAgilityRing = new AgilityRingDecorator(player.getPlayerStyle());
                    player.setPlayerStyle(addAgilityRing);
                    break;
                default:
                    System.out.println("[INVALID INPUT] Enter a number from 1-3: ");
                    continue;
            }
            break;
        }

        //wrap around your stats

    }
    }

