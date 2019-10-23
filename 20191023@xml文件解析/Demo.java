package com.xml_parser;

import java.io.IOException;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.util.XMLParser;


public class Demo {

	public static void main(String[] args) {
		try {
			new XMLParser() {
				@Override
				public void dealElement(Element element, int index) {
					String id = element.getAttribute("id");
					String name = element.getAttribute("name");
					String sex = element.getAttribute("sex");
					System.out.println(id + ", " + name + ", " + sex);
					
					new XMLParser() {
						@Override
						public void dealElement(Element element, int index) {
							String hobby = element.getTextContent();
							System.out.println("\t" + hobby);
						}
					}.parse(element, "hobby");;
				}
			}.parse(XMLParser.loadXml("/student.xml"), "student");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}

}
