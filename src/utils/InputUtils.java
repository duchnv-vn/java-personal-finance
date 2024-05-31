package utils;

import java.text.MessageFormat;
import java.util.Scanner;

import enums.ExpenseType;
import enums.PrintMessage;

import static utils.MsgUtils.printMsg;

public class InputUtils {
    public static String getStringInput(Scanner scanner, String label, boolean isRequired) {
        System.out.println(label);

        String result = "";

        while (true) {
            try {
                result = scanner.nextLine();

                if (isRequired && result.equals("")) {
                    continue;
                }

                break;
            } catch (Exception e) {
                printMsg(PrintMessage.THERE_IS_SOME_MISSTAKE);
            }
        }

        return result;
    }

    public static String getTypeInput(Scanner scanner) {
        printMsg(PrintMessage.SELECT_EXPENSE_TYPE);
        for (var type : ExpenseType.values()) {
            printMsg(MessageFormat.format("[{0}] {1}", type.ordinal(), type.name()));
        }

        String result;

        while (true) {
            try {
                var input = scanner.nextLine();

                if (checkToStop(input)) {
                    result = "stop";
                    break;
                }
                if (checkToReset(input)) {
                    result = "reset";
                    break;
                }

                int index = Integer.parseInt(input);
                result = ExpenseType.values()[index].name();
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                printMsg(PrintMessage.INVALID_TYPE_INDEX);

            } catch (NumberFormatException e) {
                printMsg(PrintMessage.MUST_BE_NUMBER_INPUT_VALUE);

            } catch (Exception e) {
                printMsg(PrintMessage.THERE_IS_SOME_MISSTAKE);
            }
        }

        return result;
    }

    public static int getNumberInput(Scanner scanner, String labelFormat, int defaultValue) {
        int result = defaultValue;

        while (true) {
            try {
                printMsg(MessageFormat.format(labelFormat, String.valueOf(defaultValue)));
                String input = scanner.nextLine();

                if (checkToStop(input)) {
                    result = -1;
                    break;
                }
                if (checkToReset(input)) {
                    result = -2;
                    break;
                }

                if (!input.equals("")) {
                    result = Integer.parseInt(input);
                }

                break;
            } catch (NumberFormatException e) {
                printMsg(PrintMessage.MUST_BE_NUMBER_INPUT_VALUE);

            } catch (Exception e) {
                printMsg(PrintMessage.THERE_IS_SOME_MISSTAKE);
            }
        }

        return result;
    }

    public static boolean checkToStop(String value) {
        return value.equals("stop");
    }

    public static boolean checkToStop(int value) {
        return value == -1;
    }

    public static boolean checkToReset(String value) {
        return value.equals("reset");
    }

    public static boolean checkToReset(int value) {
        return value == -2;
    }
}
