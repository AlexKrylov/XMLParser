package sample;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;

public class Writer {
    public static void writeTagWithFileNameToTxt(ArrayList<String> cadNumListWithFileName, String fileName) throws Exception {
        FileWriter fileWriter = new FileWriter(Main.getOutPath() + fileName + ".txt");
        for (String aCadNumListWithFileName : cadNumListWithFileName) {
            fileWriter.write(aCadNumListWithFileName);
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.flush();
        }
        fileWriter.close();
    }

    public static void writeEqualsTagToTxt(ArrayList<String> cadNumListWithFileName) throws Exception {
        clearDirectory();
        FileWriter fileWriter = new FileWriter(Main.getOutPath() + "equals.txt");
        for (String aCadNumListWithFileName : cadNumListWithFileName) {
            fileWriter.write(aCadNumListWithFileName);
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.flush();
        }
        fileWriter.close();
    }

    public static void clearDirectory() {
        for (File file : Objects.requireNonNull(new File(Main.getOutPath()).listFiles())) if (file.isFile()) file.delete();
    }
}
