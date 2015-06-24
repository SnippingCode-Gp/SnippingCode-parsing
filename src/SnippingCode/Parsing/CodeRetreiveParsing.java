package SnippingCode.Parsing;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import SnippingCode.Domain.Code;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CodeRetreiveParsing {
    private String path;
    private List<Code> codes;

    public CodeRetreiveParsing(String path){
        this.path = path;
    }


    public List<Code> parse() {
        codes = new ArrayList<Code>();

        try {

            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("code");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Code code = new Code();
                    Element eElement = (Element) nNode;
//                    System.out.println("name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
//                    System.out.println("version : " + eElement.getElementsByTagName("version").item(0).getTextContent());
//                    System.out.println("*******************");
                    code.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    code.setVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
                    codes.add(code);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codes;
    }
}


// thanks to links http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
