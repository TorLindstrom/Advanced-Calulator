package se.lexicon.tor;

import java.util.Scanner;

public class EquationHelpers {


    //advanced mode helpers

    public static String solveWithParenthesis(String segment) {

        //pads the string with spaces
        segment = Helpers.paddedString(segment);

        String solved, remainder;

        //last parenthesis in equation
        int lastParenthesis = segment.lastIndexOf('(');

        //while there are any parenthesis pairs:
        while (lastParenthesis > -1) {

            StringBuilder compiler1 = new StringBuilder();

            //saves and separates the first segment up to the selected pair
            compiler1.append(segment.substring(0, lastParenthesis));

            int lastClosingParenthesis = segment.indexOf(')', lastParenthesis);

            String SelectedPair = segment.substring(lastParenthesis, lastClosingParenthesis + 1);

            //solves the second pair
            solved = solveString(SelectedPair);

            //save the remainder
            remainder = segment.substring(lastClosingParenthesis + 1);

            //append the solved part
            compiler1.append(solved);

            //appends the remaining part of the input segment
            compiler1.append(remainder);

            segment = compiler1.toString();

            lastParenthesis = segment.lastIndexOf('(');
        }

        //gets a really simple string containing nothing but a single sum, at the end of a successful recursion loop
        return solveString(segment);

    }

    public static String solveString(String segment) {
        String[] order = new String[50];
        Scanner unpacker = new Scanner(segment);
        int i = 0;

        //unpacks and removes ( and )
        while (unpacker.hasNext()) {
            String current = unpacker.next();
            if (current.equals("(")) { //skips the first (
                current = unpacker.next();
            } else if (current.equals(")")) { //end on the finishing )
                order[i] = "x";
            } //else
            order[i] = current;
            i++;
        }
        //unpacked into array

        //stop sign, sets end of equation
        order[i] = "x";

        /*checks what to do first
        picks an operator, sends that and the numbers around it to be calculated
        returns sum, sets the first of the sent pieces as the sum
        moves the ones on the right to the left two (or one) steps
         */

        //check if sqrt, if sqrt then calculate and shorten appropriately
        //does all sqrts in segment
        for (int k = 0; k < order.length; ) {
            String piece = order[k];
            if (piece.equals("x")) {
                break;
            }
            if (piece.equals("SQRT")) {
                order[k] = CalcHelpers.sqrtString(order[k + 1]);

                order = shortenAfterSQRT(order, k + 2);

            } else {
                k++;
            }
        }

        //check if * / or POWER, if yes then calc, shorten
        //does all * / power in segment
        for (int k = 0; k < order.length; ) {
            String piece = order[k];
            if (piece.equals("x")) {
                break;
            }
            if (piece.equals("*") || piece.equals("/") || piece.equals("POWER")) {
                order[k - 1] = CalcHelpers.calculateString(order[k - 1], order[k + 1], order[k]);

                order = shortenEquation(order, k + 2);

            } else {
                k++;
            }
        }

        //check if + or -, if yes then calc, shorten
        //and lastly does all + - in segment
        for (int k = 0; k < order.length; ) {
            String piece = order[k];
            if (piece.equals("x")) {
                break;
            }
            if (piece.equals("+") || piece.equals("-")) {
                order[k - 1] = CalcHelpers.calculateString(order[k - 1], order[k + 1], order[k]);

                order = shortenEquation(order, k + 2);

            } else {
                k++;
            }
        }

        //the sum is at the beginning of the pieces, after a successful solution, can make it a double real easy
        return order[0];

    }

    public static String[] shortenEquation(String[] equation, int pos) {

        //the piece after the three counted pieces, the first untouched of the rest
        String current = equation[pos];

        //while the one to be moved is worth moving, move it
        while (!current.equals("x")) {
            //shifts the untouched to fill in after the shortened equation
            equation[pos - 2] = equation[pos];
            pos++;
            current = equation[pos];
        }

        //only to be used after a successful equation

        //makes it two spaces shorter, l being the first "x", at the back of the equation
        equation[pos - 1] = "x";
        equation[pos - 2] = "x";

        return equation;
    }

    public static String[] shortenAfterSQRT(String[] equation, int pos) {

        //the piece after the two counted pieces, the first untouched of the rest
        String current = equation[pos];

        //while the one to be moved is worth moving, move it
        while (!current.equals("x")) {
            //shifts the untouched to fill in after the shortened equation
            equation[pos - 1] = equation[pos];
            pos++;
            current = equation[pos];
        }

        //only to be used after a successful equation

        //makes it one spaces shorter, l being the first "x", at the back of the equation
        equation[pos - 1] = "x";

        return equation;
    }

}
