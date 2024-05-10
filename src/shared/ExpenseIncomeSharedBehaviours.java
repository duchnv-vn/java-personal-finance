package shared;

import java.text.MessageFormat;

public abstract class ExpenseIncomeSharedBehaviours {
    /**
     * @implNote YYYY-MM-DD format
     **/
    protected String date = "";
    protected String type = "";
    protected String name = "";
    protected String description = "";
    protected int amount = 0;

    @Override
    public String toString() {
        String formatString = """
                -------------------------------
                    - date: {0}
                    - type: {1}
                    - name: {2}
                    - description: {3}
                    - amount: {4}
                -------------------------------
                    """;

        return MessageFormat.format(formatString, this.date, this.type, this.name, this.description, this.amount);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
