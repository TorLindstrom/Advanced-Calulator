package se.lexicon.tor;

public class CalcHelpers {

    //math operands

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double sub(double num1, double num2) {
        return num1 - num2;
    }

    public static double mul(double num1, double num2) {
        return num1 * num2;
    }

    public static double div(double num1, double num2) {
        return num1 / num2;
    }

    public static double pow(double num1, double num2) {
        return Math.pow(num1, num2);
    }

    public static String sqrtString(String num1) {
        //string to double for math to string, works fine
        return String.valueOf(Math.sqrt(Double.parseDouble(num1)));
    }

    public static String calculateString(String num1In, String num2In, String op) {

        double num1 = Double.parseDouble(num1In);
        double num2 = Double.parseDouble(num2In);
        double sum = 0;

        switch (op) {
            case "+":
                sum = CalcHelpers.add(num1, num2);
                break;
            case "-":
                sum = CalcHelpers.sub(num1, num2);
                break;
            case "/":
                if (num2 != 0) {
                    sum = CalcHelpers.div(num1, num2);
                } else {
                    Helpers.writeL("Don't even try that");
                }
                break;
            case "*":
                sum = CalcHelpers.mul(num1, num2);
                break;
            case "POWER":
                sum = CalcHelpers.pow(num1, num2);
                break;
            default:
                Helpers.writeL("Something is wrong, no valid operator in calculateString method");
        }
        return String.valueOf(sum);
    }
}
