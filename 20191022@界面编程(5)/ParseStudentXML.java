package com.mec.parse_xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseStudentXML {

	public static void main(String[] args) {
		try {
			InputStream is = ParseStudentXML.class
					.getResourceAsStream("/student.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
		
			NodeList studentList = document.getElementsByTagName("student");
			for (int index = 0; index < studentList.getLength(); index++) {
				Element student = (Element) studentList.item(index);
				
				Element eleName = (Element) student.getElementsByTagName("name").item(0);
				String name = eleName.getTextContent();
				System.out.println(name);
				
				Element eleId = (Element) student.getElementsByTagName("id").item(0);
				String id = eleId.getTextContent();
				System.out.println(id);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
