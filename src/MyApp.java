import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import expense.Expense;

public class MyApp {
    static final String CSV_DIR_NAME = "csvOutput";
    static final String[] EXPENSE_TYPES = {
            "food",
            "moving",
            "family",
            "beauty",
            "household",
            "entertainment",
            "education",
            "bill",
            "others",
    };

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        var year = now.getYear();
        var month = now.getMonthValue();
        var date = now.getDayOfMonth();

        try (var keyboardScanner = new Scanner(System.in)) {
            year = getNumberInput(keyboardScanner, "\nEnter year or get current: ({0})", year);
            month = getNumberInput(keyboardScanner, "\nEnter month or get current: ({0})", month);
            date = getNumberInput(keyboardScanner, "\nEnter month or get current: ({0})", date);

            var yyyymmddFormat = "{0}-{1}-{2}";
            var yyyymmFormat = "{0}-{1}";
            var yyyymmddDate = MessageFormat.format(yyyymmddFormat, String.valueOf(year),
                    String.valueOf(month), String.valueOf(date));
            var yyyymmDate = MessageFormat.format(
                    yyyymmFormat, String.valueOf(year),
                    String.valueOf(month));

            var expenses = createExpense(keyboardScanner, yyyymmddDate);

            var totalAmount = writeFile(yyyymmDate, yyyymmddDate, expenses);
            System.out.println(MessageFormat.format("\n----- [Created expenses for {0}: {1}] -----", yyyymmddDate,
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

                var name = getStringInput(scanner, "\nEnter expense name", true);
                if (checkToStop(name))
                    break;
                if (checkToReset(name))
                    continue;

                var amount = getNumberInput(scanner, "\nEnter expense amount", 0);
                if (checkToStop(amount))
                    break;
                if (checkToReset(amount))
                    continue;

                var description = getStringInput(scanner, "\nEnter expense description", false);
                if (checkToStop(description))
                    break;
                if (checkToReset(description))
                    continue;

                var expense = new Expense(yyyymmddDate, type, name, description, amount);
                System.out.println("\nNew expense:\n" + expense.toString());
                expenses.add(expense);

                System.out.println("\nSdd more expense? (y/n)");
                var isContinue = scanner.nextLine();

                if (isContinue.equals("n")) {
                    break;
                }
            }

            return expenses;
        } catch (Exception e) {
            System.out.println("\n----- [Create expense failed] -----");
            throw e;
        }
    }

    static private int writeFile(String yyyymmDate, String yyyymmddDate, ArrayList<Expense> expenses) throws Exception {
        var filePathFormat = "./{0}/{1}.csv";
        var filePath = MessageFormat.format(filePathFormat, CSV_DIR_NAME, yyyymmDate);

        var file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                var header = "date,type,name,amount,description\n";
                Files.write(Paths.get(filePath), header.getBytes(), StandardOpenOption.APPEND);

            } catch (Exception e) {
                System.out.println("\n----- [Create new file failed] -----");
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
                System.out.println("\n----- [Write file failed] -----");
                throw e;
            }
        }
        return totalAmount;
    }

    static private String getStringInput(Scanner scanner, String label, boolean isRequired) {
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
                System.out.println("\n----- [There is some mistake] -----");
            }
        }

        return result;
    }

    static private String getTypeInput(Scanner scanner) {
        System.out.println("\nSelect expense type index number");
        for (int i = 0; i < EXPENSE_TYPES.length; i++) {
            System.out.println("[" + i + "]" + " " + EXPENSE_TYPES[i]);
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
                result = EXPENSE_TYPES[index];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\n----- [Invalid index value] -----");

            } catch (NumberFormatException e) {
                System.out.println("\n----- [Must enter valid numberical value] -----");

            } catch (Exception e) {
                System.out.println("\n----- [There is some mistake] -----");
            }
        }

        return result;
    }

    static private int getNumberInput(Scanner scanner, String labelFormat, int defaultValue) {
        int result = defaultValue;

        while (true) {
            try {
                System.out.println(MessageFormat.format(labelFormat, String.valueOf(defaultValue)));
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
                System.out.println("\n----- [Must enter valid numberical value] -----");

            } catch (Exception e) {
                System.out.println("\n----- [There is some mistake] -----");
            }
        }

        return result;
    }

    static private boolean checkToStop(String value) {
        return value.equals("stop");
    }

    static private boolean checkToStop(int value) {
        return value == -1;
    }

    static private boolean checkToReset(String value) {
        return value.equals("reset");
    }

    static private boolean checkToReset(int value) {
        return value == -2;
    }

}
