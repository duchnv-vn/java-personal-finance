package utils;

import enums.PrintMessage;

public class MsgUtils {
    public static void printMsg(PrintMessage msg) {
        System.out.println("\n" + msg);
    }

    public static void printMsg(String msg) {
        System.out.println("\n" + msg);
    }

    public static String getprintMsg(PrintMessage msg) {
        return "\n" + msg;
    }

    public static String getprintMsg(String msg) {
        return "\n" + msg;
    }
}
