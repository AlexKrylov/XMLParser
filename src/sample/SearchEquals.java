package sample;

import java.io.File;
import java.util.Objects;

public class SearchEquals {
    public static void searchEquals(String tag, String attr) throws Exception {
        File xmlDirectory = new File(Main.getXmlPath());
        Reader.searchEqualsTag(Reader.readTxtWithTagToArrayList(Controller.getSelectedFilePath()), Reader.tagListTxt(Objects.requireNonNull(xmlDirectory.listFiles()), tag, attr));
    }
}
