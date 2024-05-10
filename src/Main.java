import java.util.Scanner;
import java.text.MessageFormat;
import java.time.LocalDate;
import shared.ExpenseTypes;

public class Main {
    static final ExpenseTypes TYPES = new ExpenseTypes();

    public static void main(String[] args) {

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int date = now.getDayOfMonth();

        try (Scanner keyboardScanner = new Scanner(System.in)) {
            year = getTimeInput(year, "Enter year or get current: ({0})", keyboardScanner);
            month = getTimeInput(month, "Enter month or get current: ({0})", keyboardScanner);
            date = getTimeInput(date, "Enter month or get current: ({0})", keyboardScanner);
        }
    }

    static private int getTimeInput(int defaultValue, String labelFormat, Scanner scanner) {
        int result = defaultValue;

        while (true) {
            try {
                System.out.println(MessageFormat.format(labelFormat, String.valueOf(defaultValue)));
                String input = scanner.nextLine();

                if (!input.equals("")) {
                    result = Integer.parseInt(input);
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("----- [Must enter valid numberical value] -----");

            } catch (Exception e) {
                System.out.println("----- [There is some mistake. Try again] -----");
            }
        }

        return result;
    }
}