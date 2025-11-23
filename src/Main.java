import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

        //declaring player from player stats class
        PlayerStats player = null;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("WELCOME TO MAGICAL GIRL BATTLE ARENA");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

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
        while (true) {
            int characterSelect = scanner.nextInt();
            switch (characterSelect) {
                case 1:
                    //creates player and assign weapon(with saved stats) and ansi color
                    player = new PlayerStats(MagicalGirlFightStyle.gun);
                    System.out.println(ANSI.BLUE + "You chose Esme - Sharpshooter");

                    break; //exit the switch loop and the while loop!
                case 2:
                    player = new PlayerStats(MagicalGirlFightStyle.jumboHammer);
                    System.out.println(ANSI.RED + "You chose Astrid - Jumbo Hammer");
                    break;
                case 3:
                    player = new PlayerStats(MagicalGirlFightStyle.darkMagic);
                    System.out.println(ANSI.MAGENTA + "You chose Iris - Dark Mage");
                    break;
                case 4:
                    player = new PlayerStats(MagicalGirlFightStyle.lightMagic);
                    System.out.println(ANSI.YELLOW + "YOu chose Evangeline - Light Mage");

                    break;
                default:
                    System.out.println("[INVALID INPUT] Enter a number from 1-4: ");
                    continue;
            }
        break;
        }
    }
    }
