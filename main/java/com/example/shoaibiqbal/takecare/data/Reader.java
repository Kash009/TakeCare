package com.example.shoaibiqbal.takecare.data;

/**
 * Created by Shoaib Iqbal on 18-Apr-16.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        import java.io.File;
        import java.io.IOException;
        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.parsers.ParserConfigurationException;
        import javax.xml.xpath.XPath;
        import javax.xml.xpath.XPathConstants;
        import javax.xml.xpath.XPathExpression;
        import javax.xml.xpath.XPathExpressionException;
        import javax.xml.xpath.XPathFactory;

        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.NamedNodeMap;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;
        import org.xml.sax.SAXException;

        import java.io.InputStream;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Set;
        import java.util.TreeSet;

/**
 *
 *  for(int itr =0; itr<nList.getLength(); itr++)
 {
 Node nNode = nList.item(itr);
 }
 * @author user
 */
public class Reader {

    public File fxmFile;
    public DocumentBuilderFactory dbFactory;
    public DocumentBuilder dBuilder;
    Document doc;

    public Reader(InputStream fileName) throws ParserConfigurationException, SAXException, IOException
    {
        //fxmFile = new File(fileName);
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();

        doc = dBuilder.parse(fileName);
        doc.getDocumentElement().normalize();

    }


    public List fetchDoctor(String category)
    {
        NodeList nList = doc.getElementsByTagName("Doctor");
        List<Node> result = new ArrayList<Node>();
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;

            if(category.compareTo(eElement.getAttribute("category"))==0)
            {

                result.add(nNode);

            }
        }

        return result;
    }



    public List fetchDoctorCategory()
    {
        NodeList nList = doc.getElementsByTagName("Doctor");
        List<String> result = new ArrayList<String>();
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;

            result.add(eElement.getAttribute("category"));

        }

        Set<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(result);
        result = new ArrayList<String>(set);

        return result;
    }

    public void fetchPharmacay()
    {
        NodeList pharmacy = doc.getElementsByTagName("pharmacy");
        for(int index=0; index<pharmacy.getLength(); index++)
        {
            Element pharmacyElement = (Element) pharmacy.item(index);
            System.out.println("Name : "+pharmacyElement.getAttribute("name"));
            System.out.println("Address : "+pharmacyElement.getElementsByTagName("address").item(0).getTextContent());
        }
    }

    public Node getDoctorNode(String id) throws XPathExpressionException {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//Doctor[@id=\"" + id + "\"]");
        Node nd = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return nd;
    }

    public String getDoctorName(Node in) {
        Element temp = (Element) in;
        return temp.getAttribute("name");
    }
    public String getDoctorPos(Node in) {
        Element temp = (Element) in;
        return temp.getAttribute("category");
    }

    public String getDoctorExp(Node in) {
        Element temp = (Element) in;
        return temp.getElementsByTagName("exp").item(0).getTextContent();
    }

    public String[] getDoctorQual(Node in) {
        Element temp = (Element) in;
        NodeList quali = temp.getElementsByTagName("qualification");
        String[] storage = new String[quali.getLength()];
        for (int i = 0; i < storage.length; i++) {
            Node n1Node = quali.item(i);
            storage[i] = n1Node.getTextContent();
        }
        return storage;
    }

    public String[] getDoctorSpec(Node in) {
        Element temp = (Element) in;
        NodeList quali = temp.getElementsByTagName("speciality");
        String[] storage = new String[quali.getLength()];
        for (int i = 0; i < storage.length; i++) {
            Node n1Node = quali.item(i);
            storage[i] = n1Node.getTextContent();
        }
        return storage;
    }
}







