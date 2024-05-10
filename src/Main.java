import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.time.LocalDate;
import expense.Expense;

public class Main {
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
        int year = now.getYear();
        int month = now.getMonthValue();
        int date = now.getDayOfMonth();

        try (Scanner keyboardScanner = new Scanner(System.in)) {
            year = getNumberInput(keyboardScanner, "Enter year or get current: ({0})", year);
            month = getNumberInput(keyboardScanner, "Enter month or get current: ({0})", month);
            date = getNumberInput(keyboardScanner, "Enter month or get current: ({0})", date);

            writeFile(keyboardScanner, year, month, date);
        }
    }

    static private void writeFile(Scanner scanner, int year, int month, int date) {
        String dateFormat = "{0}-{1}-{2}";
        String datetime = MessageFormat.format(dateFormat, String.valueOf(year),
                String.valueOf(month), String.valueOf(date));
        String filePathFormat = "./{0}/{1}.csv";
        String filePath = MessageFormat.format(filePathFormat, CSV_DIR_NAME, datetime);

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("----- [Create new file failed] -----");
                return;
            }
        }

        try {
            while (true) {
                String type = getTypeInput(scanner);
                String name = getStringInput(scanner, "Enter expense name", true);
                String description = getStringInput(scanner, "Enter expense description", false);
                int amount = getNumberInput(scanner, "Enter expense amount", 0);

                String row = type + "," + name + "," + description + "," + amount + "\n";
                Files.write(Paths.get(filePath), row.getBytes(), StandardOpenOption.APPEND);

                Expense expense = new Expense(datetime, type, name, description, amount);
                System.out.println("New created expense: \n" + expense.toString());

                System.out.println("Does add more expense? (y/n)");
                String isContinue = "y";
                isContinue = scanner.nextLine();

                if (isContinue.equals("n")) {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("----- [Write file failed] -----");
            return;
        }
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
                System.out.println("----- [There is some mistake] -----");
            }
        }

        return result;
    }

    static private String getTypeInput(Scanner scanner) {
        System.out.println("Select expense type index number");
        for (int i = 0; i < EXPENSE_TYPES.length; i++) {
            System.out.println("[" + i + "]" + " " + EXPENSE_TYPES[i]);
        }

        String result;

        while (true) {
            try {
                int index = scanner.nextInt();
                result = EXPENSE_TYPES[index];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("----- [Invalid index value] -----");

            } catch (NumberFormatException e) {
                System.out.println("----- [Must enter valid numberical value] -----");

            } catch (Exception e) {
                System.out.println("----- [There is some mistake] -----");
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

                if (!input.equals("")) {
                    result = Integer.parseInt(input);
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("----- [Must enter valid numberical value] -----");

            } catch (Exception e) {
                System.out.println("----- [There is some mistake] -----");
            }
        }

        return result;
    }
}