package sample;

import java.io.File;
import java.util.Objects;

public class ConvertXmlToTxt {
    public static void parsing(String tag, String attr) throws Exception {
        File xmlDirectory = new File(Main.getXmlPath());
        Reader.tagListFromXmlWithFileName(Objects.requireNonNull(xmlDirectory.listFiles()), tag, attr);
    }
}
