import Logic.JSONNumPrinter;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        File jsonFile = new File(currentDir+"\\test.json");
	    JSONNumPrinter printer = new JSONNumPrinter(jsonFile);
	    printer.printNums();
    }
}
