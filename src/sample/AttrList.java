package sample;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.TreeSet;

public class AttrList {
    private static TreeSet<String> attrList = new TreeSet<>();

    public static TreeSet<String> listAttr(File file) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList entries = document.getElementsByTagName("*");
        for (int i = 0; i < entries.getLength(); i++) {
            Element node = (Element) entries.item(i);
            list(node);
        }
        return attrList;
    }

    public static void list(Element element) {
        NamedNodeMap listAttr = element.getAttributes();
        for (int j = 0; j < listAttr.getLength(); j++) {
            Attr attr = (Attr) listAttr.item(j);
            attrList.add(attr.getNodeName());
        }
    }
}
