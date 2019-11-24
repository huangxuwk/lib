package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class XMLEditor {
	public static final Gson gson = new GsonBuilder().create();
	public static final String ROOT_TAG = "root";
	
	private static volatile DocumentBuilder db;
	private static volatile Transformer tf;
	
	public XMLEditor() {
		init();
	}
	
	private void init() {
		if (db == null) {
			synchronized (XMLEditor.class) {
				if (db == null) {
					try {
						db = DocumentBuilderFactory
								.newInstance()
								.newDocumentBuilder();
					} catch (ParserConfigurationException e) {
						e.printStackTrace();
					}
					try {
						tf = TransformerFactory
								.newInstance()
								.newTransformer();
					} catch (TransformerConfigurationException e) {
						e.printStackTrace();
					} catch (TransformerFactoryConfigurationError e) {
						e.printStackTrace();
					}
				}
			}
		}		
	}
	
	private void createNewXML(File xmlFile) {
		Document document = db.newDocument();
		Element element = document.createElement(ROOT_TAG);
		element.setTextContent("\n");
		document.appendChild(element);
		
		saveXml(xmlFile, document);
	}
	
	public String getAbsolutePathByProjectRoot() {
		File currentPath = new File(".");
		String absolutePath = currentPath.getAbsolutePath();
		int lastDotIndex = absolutePath.lastIndexOf("\\.");
		return absolutePath.substring(0, lastDotIndex + 1);
	}
	
	public File getAbsolutePathByProjectBin(String path) {
		String absulutePath = getAbsolutePathByProjectRoot();
		String projectBinPath = absulutePath + "bin\\";
		File file = new File(projectBinPath + path);
		
		return file;
	}
	
	private void saveXml(File xml, Document doc) {
		try {
			tf.setOutputProperty("indent", "yes");
			DOMSource source = new DOMSource();
			source.setNode(doc);
			StreamResult result = new StreamResult();
			result.setOutputStream(new FileOutputStream(xml));
			
			tf.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insert(String xmlFilePath, Object object) {
		File xmlFile = new File(xmlFilePath);
		if (!xmlFile.exists()) {
			int lastIndex = xmlFilePath.lastIndexOf("\\");
			String xmlFileDirs = xmlFilePath.substring(0, lastIndex);
			File xmlFileDirPath = new File(xmlFileDirs);
			xmlFileDirPath.mkdirs();
			createNewXML(xmlFile);
		}
		if (object == null) {
			return false;
		}
		
		Document doc;
		try {
			doc = db.parse(xmlFile);
			Element root = (Element) doc.getElementsByTagName(ROOT_TAG);
			if (root == null) {
				return false;
			}
			
			makeElementByObject(doc, root, object);
			saveXml(xmlFile, doc);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	private void makeElementByObject(Document document, Element parent, Object object) {
		Class<?> klass = object.getClass();
		Element curElement = document.createElement(klass.getSimpleName());
		
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			Class<?> paraType = field.getType();
			if (!isRightType(paraType)) {
				continue;
			}
			String tagName = field.getName();
			Element subEle = document.createElement(tagName);
			
			Object objValue;
			try {
				objValue = getFieldValue(klass, object, field);
				if (objValue == null) {
					continue;
				}
				if (paraType.isPrimitive() || paraType.equals(String.class)) {
					String value = objValue.toString();
					subEle.setTextContent(value);
				} else {
					subEle.setAttribute("class", objValue.getClass().getName());
					@SuppressWarnings("unchecked")
					List<Object> list = (List<Object>) objValue;
					for (Object item : list) {
						makeElementByObject(document, subEle, item);
					}
				}
				curElement.appendChild(subEle);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		parent.appendChild(curElement);
	}
	
	private Object getFieldValue(Class<?> klass, Object object, Field field) throws Exception {
		String fieldName = field.getName();
		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method method = klass.getDeclaredMethod(getMethodName, new Class<?>[] {});
		
		return method.invoke(object);
	}
	
	private boolean isRightType(Class<?> paraType) {
		return paraType.isPrimitive() 
				|| paraType.equals(String.class)
				|| paraType.isAssignableFrom(List.class);
	}
	
	public <T> T get(String xmlFilePath, Class<?> klass, String propertyName, String value) {
		if (propertyName == null || value == null) {
			return null;
		}
		File xmlFile = new File(xmlFilePath);
		if (!xmlFile.exists()) {
			return null;
		}
		
		Document document;
		try {
			document = db.parse(xmlFile);
			Element clazz = search(document, xmlFile, klass, propertyName, value);
			if (clazz == null) {
				return null;
			}
			
			return get(clazz, klass);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Element search(Document document, File xmlFile, Class<?> klass, String propertyName, String value) {
		String tagName = klass.getSimpleName();
		NodeList tagList = document.getElementsByTagName(tagName);
		for (int index = 0; index < tagList.getLength(); index++) {
			Element parentElement = (Element) tagList.item(index);
			Element property = (Element) parentElement.getElementsByTagName(propertyName).item(0);
			String textContent = property.getTextContent();
			if (value.equals(textContent)) {
				return parentElement;
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T get(Element element, Class<?> klass) {
		StringBuffer result = new StringBuffer();
		result.append("{");
		Field[] fields = klass.getDeclaredFields();
		boolean first = true;
		for (Field field : fields) {
			String tagName = field.getName();
			
			NodeList eleList = element.getElementsByTagName(tagName);
			Element eleProperty = (Element) eleList.item(0);
			if (eleProperty == null) {
				continue;
			}
			
			String propertyValue = eleProperty.getTextContent();
			propertyValue = propertyValue.replace("\\", "\\\\");
			
			result.append(first ? "" : ",");
			result.append('"').append(tagName).append("\":");
			result.append('"').append(propertyValue).append('"');
			first = false;
		}
		result.append("}");

		return (T) gson.fromJson(result.toString(), klass);
	}
	
	public void modify(String xmlFilePath, String propertyName, String value, Object newObject) {
		if (xmlFilePath == null || propertyName == null || value == null || newObject == null) {
			return;
		}
		File xmlFile = new File(xmlFilePath);
		if (!xmlFile.exists()) {
			return;
		}
		
		try {
			Document document = db.parse(xmlFile);
			Element root = (Element) document.getElementsByTagName(ROOT_TAG).item(0);
			if (root == null) {
				return;
			}
			Class<?> klass = newObject.getClass();
			
			Element element = search(document, xmlFile, klass, propertyName, value);
			root.removeChild(element);
			makeElementByObject(document, root, newObject);
			
			saveXml(xmlFile, document);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
