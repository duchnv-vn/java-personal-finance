import java.text.MessageFormat;
import java.util.Scanner;
import expense.Expense;
import shared.ExpenseTypes;

public class Main {
    static final ExpenseTypes TYPES = new ExpenseTypes();

    public static void main(String[] args) {
        Scanner keyboardScanner = new Scanner(System.in);

        String yearPrintLabelFormat = "Enter year or get current: ({0})";
        System.out.println(MessageFormat.format(yearPrintLabelFormat, "2024"));
        String name = keyboardScanner.nextLine();

        String monthPrintLabelFormat = "Enter month or get current: ({0})";
        System.out.println(MessageFormat.format(monthPrintLabelFormat, "05"));

        Expense expenseRecord = new Expense();

        keyboardScanner.close();
    }
}