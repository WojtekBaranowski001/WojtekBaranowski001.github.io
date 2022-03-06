package OODCwk;

import java.util.Scanner;

/**
 * Provide command line interface
 *
 * @author A.A.Marczyk
 * @version 06/10/21
 */
public class GameUI {
    private Scanner myIn = new Scanner(System.in);

    private void playGame() {
        int choice;
        String admiralName;
        int result = -1;
        System.out.println("Enter admiral's name");
        String s = myIn.nextLine();
        SWAT gp = new SpaceWars(s);
        choice = 100;
        while (choice != 0) {
            choice = getMenuItem();
            if (choice == 1)  //list UFFleet
            {
                System.out.println(gp.getUFFleet());
            } else if (choice == 2) //List active Star Fleet
            {
                System.out.println(gp.getASFleet());
            } else if (choice == 3) //get Force
            {
                System.out.println("Enter Force reference");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                System.out.println(gp.getForceDetails(ref));
            } else if (choice == 4) //activate Force
            {
                System.out.println("Enter Force reference");
                myIn.nextLine();
                String nme = (myIn.nextLine()).trim();
                if (!gp.isDefeated()) //optional
                {
                    result = gp.activateForce(nme);
                }
                System.out.println(activation(result) + "\nWar chest = " + gp.getWarchest());
            } else if (choice == 5) //engage in a battle
            {
                System.out.println("Enter number of the battle");
                int number = myIn.nextInt();
                if (gp.isBattle(number)) {
                    result = gp.doBattle(number);
                }

                System.out.println(battling(result) + "\nWar chest = " + gp.getWarchest());

            } else if (choice == 6) //recall force
            {
                System.out.println("Enter Force reference");
                myIn.nextLine();
                String ref = (myIn.nextLine()).trim();
                if (gp.getForceDetails(ref) != null && gp.isInASFleet(ref)) {
                    gp.recallForce(ref);
                    System.out.println("\nForce " + ref +
                            " recalled" + "\nWar Chest: " + gp.getWarchest());
                }
            } else if (choice == 7) //view game state
            {
                System.out.println(gp);
            }
            // Uncomment after task 7
            else if (choice == 8) // Task 7.3 only
            {
                System.out.println("Write to file");
                gp.saveGame("olenka.txt");
            } else if (choice == 9) // Task 7.3 only
            {
                System.out.println("Restore from file");
                gp = gp.restoreGame("olenka.txt");
                System.out.println(gp.toString());
            }
        }
        System.out.println("Thank-you");
    }

    private int getMenuItem() {
        int choice = 100;
        System.out.println("Main Menu");
        System.out.println("0. Quit");
        System.out.println("1. List forces in United Forces Fleet");
        System.out.println("2. List forces in admirals active Star fleet");
        System.out.println("3. View details of a force");
        System.out.println("4. Activate a force into dmirals active Star  fleet");
        System.out.println("5. Engage in a battle");
        System.out.println("6. Recall a force");
        System.out.println("7. View the state of the game");
//        Task 4.4 only
        System.out.println("8. Save this game");
        System.out.println("9. Restore a game");


        while (choice < 0 || choice > 9) {
            System.out.println("Enter the number of your choice");
            choice = myIn.nextInt();
        }
        return choice;
    }

    private static String activation(int code) {
        switch (code) {
            case 0:
                return "force is activated";
            case 1:
                return "force is not in the UFF";
            case 2:
                return "not enough money";
            case 3:
                return "no such force";
            default:
                return "Error";
        }
    }

    private String battling(int code) {
        switch (code) {
            case 0:
                return "OODCwk.Battle won";
            case 1:
                return "OODCwk.Battle lost as no suitable force available";
            case 2:
                return "OODCwk.Battle lost on battle strength, force destroyed";
            case 3:
                return "battle is lost and admiral completely defeated ";
        }
        return " no such battle ";
    }

    public static void main(String[] args) {
        GameUI myGame = new GameUI();
        myGame.playGame();
    }
}