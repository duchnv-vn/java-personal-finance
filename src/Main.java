import java.io.*;
import java.awt.GraphicsEnvironment;

public class Main {
    public static void main(String[] args) throws IOException {
        Console console = System.console();
        if (console == null && !GraphicsEnvironment.isHeadless()) {
            String filename = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime()
                    .exec(new String[] { "cmd", "/c", "start", "cmd", "/k", "java -jar \"" + filename + "\"" });
        } else {
            MyApp.main(args);
        }
    }
}
