package com.mec.xml_parser.test;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestForXmlParser {
	private static DocumentBuilder db;
	static {
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			InputStream is = Class.class.getResourceAsStream("/student.xml");
			Document doc = db.parse(is);
			
			NodeList studentList = doc.getElementsByTagName("student");
			for (int i = 0; i < studentList.getLength(); i++) {
				Element student = (Element) studentList.item(i);
				String id = student.getAttribute("id");
				String name = student.getAttribute("name");
				String sex = student.getAttribute("sex");
				System.out.println(id + ", " + name + ", " + sex);
				
				NodeList hobbies = student.getElementsByTagName("hobby");
				for (int j = 0; j < hobbies.getLength(); j++) {
					Element hobby = (Element) hobbies.item(j);
					String hobbyName = hobby.getTextContent();
					System.out.println("\t" + hobbyName);
				}
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}


