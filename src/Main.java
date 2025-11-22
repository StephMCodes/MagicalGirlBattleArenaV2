import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        //to take user input we use scanner class
        Scanner scanner = new Scanner(System.in);

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
                    //assign player character and ansi colour
                    break; //exit the switch loop and the while loop!
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("[INVALID INPUT] Enter a number from 0-3: ");
                    continue;
            }
        break;
        }
    }
    }
