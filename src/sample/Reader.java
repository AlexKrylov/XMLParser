package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reader {
    public static ArrayList<String> readTxtWithTagToArrayList(String path) throws Exception {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> list = new ArrayList<>();
        String str;
        while ((str = reader.readLine()) != null) {
            if (!str.isEmpty()) {
                list.add(str);
            }
        }
        reader.close();
        return list;
    }

    public static void tagListFromXmlWithFileName(File[] file, String tag, String attr) throws Exception {
        Writer.clearDirectory();
        for (File aFile : file) {
            ArrayList<String> cadNumListWithFileName = new ArrayList<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(aFile);
            NodeList parcel = document.getElementsByTagName(tag);
            for (int i = 0; i < parcel.getLength(); i++) {
                Element elem = (Element) parcel.item(i);
                cadNumListWithFileName.add(elem.getTagName() + " " + attr + " " + elem.getAttribute(attr));
            }
            Writer.writeTagWithFileNameToTxt(cadNumListWithFileName, aFile.getName());
        }
    }

    public static void searchEqualsTag(ArrayList<String> fileWithCadNumber ,HashMap<String, String>  fileFromXml) throws Exception {
        ArrayList<String> listEqualsCadNumber = new ArrayList<>();
        for (String aFileFromXml : fileWithCadNumber) {
            for (Map.Entry<String, String> entry : fileFromXml.entrySet()) {
                if (entry.getKey().equals(aFileFromXml)) {
                    listEqualsCadNumber.add(entry.getValue() + " " + aFileFromXml);
                }
            }
        }
        Writer.writeEqualsTagToTxt(listEqualsCadNumber);
    }

    public static HashMap<String, String> tagListTxt(File[] file, String tag, String attr) throws Exception {
        Writer.clearDirectory();
        HashMap<String, String> cadNumList = new HashMap<>();
        for (File aFile : file) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(aFile);
            NodeList parcel = document.getElementsByTagName(tag);
            for (int i = 0; i < parcel.getLength(); i++) {
                Element elem = (Element) parcel.item(i);
                cadNumList.put(elem.getAttribute(attr), aFile.getName());
            }
        }
        return cadNumList;
    }
}


