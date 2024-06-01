package enums;

public enum PrintMessage {
    ENTER_YEAR("Enter year or get current: ({0})"),
    ENTER_MONTH("Enter month or get current: ({0})"),
    ENTER_DATE("Enter month or get current: ({0})"),

    SELECT_EXPENSE_TYPE("Select expense type index number"),
    ENTER_EXPENSE_NAME("Enter expense name"),
    ENTER_EXPENSE_AMOUNT("Enter expense amount"),
    ENTER_EXPENSE_DESC("Enter expense description"),
    NEW_EXPENSE("New expense:"),
    ADD_MORE_EXPENSE("Add more expense? (y/n)"),

    CREATE_FAIL("Create expense failed"),
    CREATE_NEW_FILE_FAIL("Create new file failed"),
    WRITE_FILE_FIELD("Write file failed"),
    THERE_IS_SOME_MISSTAKE("There is some mistake"),
    INVALID_TYPE_INDEX("Invalid index value"),
    MUST_BE_NUMBER_INPUT_VALUE("Must enter valid numberical value"),

    CREATE_SUCCESS("Created expenses for {0}: {1}");

    private String msg;

    PrintMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
