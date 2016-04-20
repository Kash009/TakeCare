package com.example.shoaibiqbal.takecare.data;

/**
 * Created by Shoaib Iqbal on 18-Apr-16.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.example.shoaibiqbal.takecare.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author user
 */
public class ReadXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {


//*********************** Doctor k liye **************************************

        Reader doctors = new Reader(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        }); //my class

        List<String> categories = doctors.fetchDoctorCategory(); //function is in class
        //fetches all cateogries but not distinct

        //easy way to remove repeatative categories
        //currently categories contains 5 string of Cardiologist, 5 string of Dentist so on...
        // to get non-repeatative list
//
//        Set<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
//        set.addAll(categories);
//        categories = new ArrayList<String>(set);

        //now categories list have 1 string of cardiologist and so oon...


        for(int index=0; index<categories.size(); index++)
        {

            System.out.println(index + " : "+categories.get(index));
        }




        //Searching doctor on basis of category
        List<Node> result = doctors.fetchDoctor("Dermatologist"); //search by category.. function is in  ReaderClass

        //list<Node> Node is a built in class its a xmlNode... not custom made



        // function only return desire nodes in a List
        //how to extract data from list returned by function....

        //yeh kaam main me kiya hai meine ... tumhari marzi tum isko function banalo class ka etc

        for(int i=0; i<result.size(); i++)
        {
            Node nNode = result.get(i);
            Element eElement = (Element) nNode;

            //showing Doctor's data
            System.out.println("Doctor id : " + eElement.getAttribute("id"));
            System.out.println("First Name : " + eElement.getAttribute("name"));
            System.out.println("Category : " + eElement.getAttribute("category"));
            System.out.println("Salary : " + eElement.getElementsByTagName("exp").item(0).getTextContent());
            System.out.println("Qualification : " + eElement.getElementsByTagName("qualification").item(0).getTextContent());

            //Multiple speciality tags so...
            NodeList speciality = eElement.getElementsByTagName("speciality");
            for(int index=0; index<speciality.getLength() ; index++)
            {
                Node n1Node = speciality.item(index);
                System.out.println("Speciality "+ n1Node.getTextContent());

            }
            System.out.println("fee : " + eElement.getElementsByTagName("fee").item(0).getTextContent());

            //Schedule
            //creating new Nodelist
            NodeList Schedule = eElement.getElementsByTagName("schedule");
            Element scheduleElement = (Element) Schedule.item(0);
            //System.out.println("Clinic : " + scheduleElement.getAttribute("name"));
            System.out.println("Hospital : " + eElement.getElementsByTagName("location").item(0).getTextContent());
            System.out.println("location : " + scheduleElement.getElementsByTagName("loc").item(0).getTextContent());

            //for days
            //Creating NEw NodeList

            NodeList days = scheduleElement.getElementsByTagName("day");
            for(int dayItr=0; dayItr<days.getLength(); dayItr++)
            {
                Element dayElement = (Element) days.item(dayItr);
                System.out.println("day : " + dayElement.getAttribute("str"));
                System.out.println("timings : " + dayElement.getElementsByTagName("time").item(0).getTextContent());

            }

        }





//*********************************Pharmacy**********************************************

        //Reader pharmacyRead = new Reader("pharmacies.xml");
        //pharmacyRead.fetchPharmacay();



    }


}
