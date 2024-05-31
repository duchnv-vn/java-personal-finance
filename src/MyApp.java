import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import enums.CustomDateFormat;
import enums.PrintMessage;
import expense.Expense;

import static utils.MsgUtils.*;
import static utils.InputUtils.*;

public class MyApp {
    static final String CSV_DIR_NAME = "csvOutput";
    static final String FILE_DIR_FORMAT = "./{0}/{1}.csv";
    static final String CSV_HEADER = "date,type,name,amount,description\n";

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        var year = now.getYear();
        var month = now.getMonthValue();
        var date = now.getDayOfMonth();

        try (var keyboardScanner = new Scanner(System.in)) {
            year = getNumberInput(keyboardScanner, getprintMsg(PrintMessage.ENTER_YEAR), year);
            month = getNumberInput(keyboardScanner, getprintMsg(PrintMessage.ENTER_MONTH), month);
            date = getNumberInput(keyboardScanner, getprintMsg(PrintMessage.ENTER_DATE), date);

            var yyyymmddDate = MessageFormat.format(
                    CustomDateFormat.YYYYMMDD.getFormat(), String.valueOf(year),
                    String.valueOf(month), String.valueOf(date));
            var yyyymmDate = MessageFormat.format(
                    CustomDateFormat.YYYYMM
                            .getFormat(),
                    String.valueOf(year),
                    String.valueOf(month));

            var expenses = createExpense(keyboardScanner, yyyymmddDate);

            var totalAmount = writeFile(yyyymmDate, yyyymmddDate, expenses);
            System.out.println(MessageFormat.format(
                    getprintMsg(PrintMessage.CREATE_SUCCESS),
                    yyyymmddDate,
                    totalAmount));
        } catch (Exception e) {
        }
    }

    static private ArrayList<Expense> createExpense(Scanner scanner, String yyyymmddDate) {
        try {
            var expenses = new ArrayList<Expense>();

            while (true) {
                var type = getTypeInput(scanner);
                if (checkToStop(type))
                    break;
                if (checkToReset(type))
                    continue;

                var name = getStringInput(scanner, getprintMsg(PrintMessage.ENTER_EXPENSE_NAME), true);
                if (checkToStop(name))
                    break;
                if (checkToReset(name))
                    continue;

                var amount = getNumberInput(scanner, getprintMsg(PrintMessage.ENTER_EXPENSE_AMOUNT), 0);
                if (checkToStop(amount))
                    break;
                if (checkToReset(amount))
                    continue;

                var description = getStringInput(scanner, getprintMsg(PrintMessage.ENTER_EXPENSE_DESC), false);
                if (checkToStop(description))
                    break;
                if (checkToReset(description))
                    continue;

                var expense = new Expense(yyyymmddDate, type, name, description, amount);
                expenses.add(expense);
                printMsg(PrintMessage.NEW_EXPENSE + "\n" + expense.toString());

                printMsg(PrintMessage.ADD_MORE_EXPENSE);
                var isContinue = scanner.nextLine();

                if (isContinue.equals("n")) {
                    break;
                }
            }

            return expenses;
        } catch (Exception e) {
            printMsg(PrintMessage.CREATE_FAIL);
            throw e;
        }
    }

    private static int writeFile(String yyyymmDate, String yyyymmddDate, ArrayList<Expense> expenses) throws Exception {
        var filePath = MessageFormat.format(FILE_DIR_FORMAT, CSV_DIR_NAME, yyyymmDate);

        var file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Files.write(Paths.get(filePath), CSV_HEADER.getBytes(), StandardOpenOption.APPEND);

            } catch (Exception e) {
                printMsg(PrintMessage.CREATE_NEW_FILE_FIELD);
                throw e;
            }
        }

        int totalAmount = 0;
        for (Expense expense : expenses) {
            try {
                var row = yyyymmddDate + ","
                        + expense.getType() + "," + expense.getName() + "," + expense.getAmount() + ","
                        + expense.getDescription()
                        + "\n";
                totalAmount += expense.getAmount();

                Files.write(Paths.get(filePath), row.getBytes(), StandardOpenOption.APPEND);
            } catch (Exception e) {
                printMsg(PrintMessage.WRITE_FILE_FIELD);
                throw e;
            }
        }
        return totalAmount;
    }
}
