import Logic.NumPrinter;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        File jsonFile = new File(currentDir+"\\test.json");
	    NumPrinter printer = new NumPrinter(jsonFile);
	    printer.printNums();
    }
}
