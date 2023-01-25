import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;

public class Calculator { // class declaration
   static final String restrictionInt = "(10|[1-9])(\\s[-+*/]\\s10|\\s[-+*/]\\s[1-9])"; // regex (restriction) to 1-10 integer input
   static final String restrictionRoman = "(X|IX|IV|V?I{0,3})(\\s[-+*/]\\sX|\\s[-+*/]\\sIX|\\s[-+*/]\\sIV|\\s[-+*/]\\sV?I{1,3}|\\s[-+*/]\\sV)"; // regex (restriction) to I-X roman input

    public static void main(String[] args) throws ScannerException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        if (Pattern.matches(restrictionInt, input) || Pattern.matches(restrictionRoman, input)) {
            String result = calc(input);
            System.out.println(result);
        } else {
            throw new ScannerException("Wrong input");
        }
    }

    public static String calc(String input) throws ScannerException {
        byte result;
        String[] stringArray = input.split(" ");
        if(Pattern.matches(restrictionInt, input)) {
            switch (stringArray[1].charAt(0)) {
            case '+' -> result = (byte) (Integer.parseInt(stringArray[0]) + Integer.parseInt(stringArray[2]));
            case '-' -> result = (byte) (Integer.parseInt(stringArray[0]) - Integer.parseInt(stringArray[2]));
            case '/' -> result = (byte) (Integer.parseInt(stringArray[0]) / Integer.parseInt(stringArray[2]));
            case '*' -> result = (byte) (Integer.parseInt(stringArray[0]) * Integer.parseInt(stringArray[2]));
            default -> throw new ScannerException("Wrong input");
            }
            return String.valueOf(result);
        } else if (Pattern.matches(restrictionRoman, input)) {
            switch (stringArray[1].charAt(0)) {
                case '+' -> result = (byte) (romanToInt(stringArray[0]) + romanToInt(stringArray[2]));
                case '-' -> result = (byte) (romanToInt(stringArray[0]) - romanToInt(stringArray[2]));
                case '/' -> result = (byte) (romanToInt(stringArray[0]) / romanToInt(stringArray[2]));
                case '*' -> result = (byte) (romanToInt(stringArray[0]) * romanToInt(stringArray[2]));
                default -> throw new ScannerException("Wrong input");
            }
            if (result <= 0) {
                throw new ScannerException("Roman numbers can't be below or equal to 0");
            } else {
                return intToRoman(result);
            }
        } else throw new ScannerException("Wrong input");
    }

    static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        {
            map.put('I', 1);
            map.put('V', 5);
            map.put('X', 10);
        }
        int result = 0;
        int previous = 0;
        for(int i = s.length()-1; i>=0; i--) {
            int current = map.get(s.charAt(i));
            if(current < previous) {
                result -= current;
            } else {
                result += current;
            }
            previous = current;
        }
        return result;
    }

    static String intToRoman(int a) {
        int [] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String [] romanChars = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();

        for(int i = 0; i<values.length; i++) {
            while (a >= values[i]) {
                a -= values[i];
                roman.append(romanChars[i]);
            }
        }
        return roman.toString();
    }
}