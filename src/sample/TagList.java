package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.TreeSet;

public class TagList {
    public static TreeSet<String> listTag(File file) throws Exception {
        TreeSet<String> tagList = new TreeSet<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList nodeList = document.getElementsByTagName("*");
        for (int j = 0; j < nodeList.getLength(); j++) {
            Element node = (Element) nodeList.item(j);
            tagList.add(node.getTagName());
        }
        return tagList;
    }
}
