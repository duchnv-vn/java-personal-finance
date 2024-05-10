package expense;

import shared.ExpenseIncomeSharedBehaviours;

public class Expense extends ExpenseIncomeSharedBehaviours {

    // private String date = "";
    // private String type = "";
    // private String name = "";
    // private String description = "";
    // private int amount = 0;

    public Expense(String date, String type, String name, String description, int amount) {
        this.date = date;
        this.type = type;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
}
