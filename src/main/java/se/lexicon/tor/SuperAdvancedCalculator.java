package se.lexicon.tor;

public class SuperAdvancedCalculator {

    public static final String version = "0.8";

    //starts running
    public static void main(String[] args) {
        run();
    }

    //method to run the menu loop
    private static void run(){

        Helpers.startMessage(version);

        while (true) { //keeps running the whole program

            String rawLine = Helpers.askAdvLine(); //input math line

            if (rawLine.equals("QUIT")) {
                Helpers.writeL("\n---Exiting Tor's Calculator---\n---version eX." + version + "---");
                break;
            } else {
                //call for solution and prints it
                Helpers.writeL(EquationHelpers.solveWithParenthesis(rawLine));
            }
        }
    }

}
