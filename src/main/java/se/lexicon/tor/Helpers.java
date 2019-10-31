package se.lexicon.tor;

import java.util.Scanner;

public class Helpers {

    static Scanner sc = new Scanner(System.in);

    //startup

    public static void startMessage(String version){
        //talks to user
        Helpers.writeL("\n---Opening Tor's Calculator---\n---version eX." + version + "---\n\n"); //println

        Helpers.writeL("Available operators are: '+' '-' '/' '*' 'sqrt' (squareroot) 'power' (power of)\n");

        Helpers.writeL("Write 'quit' if you wish to exit\n");
    }

    //error message method

    public static void error(String text) {
        writeL("Please enter a valid " + text);
    }

    //writing methods, just less effort

    public static void write(String text) {
        System.out.print(text);
    }

    public static void writeL(String text) {
        System.out.println(text);
    }

    //getting input from user

    public static String validIn(String text, String errorText) {
        while (true) {
            write(text);
            String answer = sc.nextLine().toUpperCase();

            if (answer != null && !answer.isEmpty()) {
                return answer;
            } else error(errorText);
        }
    }

    public static String askAdvLine() {
        return validIn("(don't forget your parenthesis!)\nEnter a line of numbers and operands: ", "line");
    }

    //menu lines for restart and exit

    public static boolean askForContinue() {

        while (true) {

            writeL("Want to do more math? (y/n)");

            switch (sc.nextLine().toLowerCase()) {
                case "y":
                    writeL("Oh, that's nice, let's go then!");
                    return true;
                case "n":
                    write("Okay then, byebye");
                    return false;
                default:
                    writeL("\nEnter either \"y\" or \"n\"\n");
            }
        }
    }

    //padding method to make a scanner friendly String


    public static String paddedString(String toBePadded) {

        StringBuilder paddington = new StringBuilder();
        char last = 'x', next;

        for (int i = 0; i < toBePadded.length(); i++) {

            char current = toBePadded.charAt(i);
            try {
                next = toBePadded.charAt(i + 1);
            } catch (StringIndexOutOfBoundsException e) {
                next = 'x';
            }

            paddington.append(current);

            switch (current) {
                case 'S':
                case 'Q':
                case 'R':
                    switch (last) {
                        case 'E':
                            paddington.append(' ');
                            break;
                        case 'Q':
                            break;
                    }
                    break;
                case 'P':
                case 'O':
                case 'W':
                case 'E':
                    break;
                case '-':
                    switch (last) {
                        case '+':
                        case '/':
                        case '*':
                        case 'R':
                        case 'T':
                            break;
                        default:
                            paddington.append(' ');
                            break;
                    }
                    break;
                default:
                    if (Character.isDigit(current) && Character.isDigit((next))) {
                        break;
                    }
                    paddington.append(' ');
            }

            last = current;

        }

        return paddington.toString();
    }


}
