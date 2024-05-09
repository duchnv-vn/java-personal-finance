import java.nio.charset.MalformedInputException;
import java.util.Scanner;
import java.text.MessageFormat;
import java.time.LocalDate;
import expense.Expense;
import shared.ExpenseTypes;

public class Main {
    static final ExpenseTypes TYPES = new ExpenseTypes();

    public static void main(String[] args) throws MalformedInputException {
        LocalDate now = LocalDate.now();
        Scanner keyboardScanner = new Scanner(System.in);

        int year = now.getYear();
        String yearPrintLabelFormat = "Enter year or get current: ({0})";
        System.out.println(MessageFormat.format(yearPrintLabelFormat, String.valueOf(year)));
        String yearInput = keyboardScanner.nextLine();
        if (yearInput != "") {
            year = convertInputToInt(yearInput);
        }

        int month = now.getMonthValue();
        String monthPrintLabelFormat = "Enter month or get current: ({0})";
        System.out.println(MessageFormat.format(monthPrintLabelFormat, String.valueOf(month)));
        String monthInput = keyboardScanner.nextLine();
        if (monthInput != "") {
            month = convertInputToInt(monthInput);
        }

        int date = now.getDayOfMonth();
        String datePrintLabelFormat = "Enter month or get current: ({0})";
        System.out.println(MessageFormat.format(datePrintLabelFormat, String.valueOf(date)));
        String dateInput = keyboardScanner.nextLine();
        if (dateInput != "") {
            date = convertInputToInt(dateInput);
        }

        keyboardScanner.close();
    }

    static private int convertInputToInt(String input) {
        try {
            int parsedYearInput = Integer.parseInt(input);
            return parsedYearInput;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("----- [Must enter valid numberic year value] -----");
        }
    }
}