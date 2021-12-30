/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author menna
 */
public class validation {

    public static void main(String[] args) {

        if (args.length != 0) {
            String mainString = args[0];
            int periodNoInEntry = periodCount(mainString);

            //System.out.println(periodNoInEntry);
            if (periodNoInEntry == 3) {
                int counter = 1;
                while (mainString.indexOf('.') != -1) {

                    if (!isStringNumeric(mainString.substring(0, mainString.indexOf('.')))) {
                        System.out.println("Octet no. " + counter + " isn't numeric");
                        break;
                    }
                    int octet = parseOctetToInt(mainString.substring(0, mainString.indexOf('.')));
                    if (octet == -1) {
                        System.out.println("Wrong range for octet no. " + counter);
                        break;
                    }
                    System.out.println("Octet no." + counter + " : " + octet);
                    mainString = mainString.substring(mainString.indexOf('.') + 1);
                    counter++;
                    if (counter == 4) {
                        if (mainString.isEmpty()) {
                            System.out.println("Wrong range for octet no. 4 is Empty");
                        } else {
                            if (!isStringNumeric(mainString)) {
                                System.out.println("Octet no. " + counter + " isn't numeric");
                                break;
                            }
                            octet = parseOctetToInt(mainString);
                            if (octet != -1) {
                                System.out.println("Octet no." + counter + " : " + octet);
                            } else {
                                System.out.println("Wrong range for octet no. " + counter);
                            }
                        }
                    }

                }
            } else {
                System.out.println("False IP Format, IP should contain exactly 3 periods");
            }

        } else {
            System.out.println("Data Entry is empty.");
        }

    }

    public static boolean isStringNumeric(String operand) {

        Pattern pattern = Pattern.compile("-?\\d+");
        if (operand == null) {
            return false;
        }
        Matcher operandMatcher = pattern.matcher(operand);
        return operandMatcher.matches();
    }

    public static int periodCount(String entry) {
        int result = 0;
        for (int i = 0; i < entry.length(); i++) {
            if (entry.charAt(i) == '.') {
                result++;
            }
        }
        return result;
    }

    public static int parseOctetToInt(String octet) {
        
        int value = Integer.parseInt(octet);
        if (value > 0 && value <= 255) {
            return value;
        } else {
            return -1;
        }

    }

    static void usingSubString(String mainString) {

        while (mainString.indexOf('.') != -1) {
            System.out.println(mainString.substring(0, mainString.indexOf('.')));
            mainString = mainString.substring(mainString.indexOf('.') + 1);
        }
        System.out.println(mainString);
    }

}
