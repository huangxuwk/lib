package com.mec.cs_framework.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.mec.util.XMLParser;

public class ActionBeanFactory {
	private static final Map<String, ActionDefinition> actionMap;
	static {
		actionMap = new HashMap<>();
	}
	
	public ActionBeanFactory() {
	}
	
	public static void scanActionMapping(String actionMappingPath) {
		try {
			new XMLParser() {
				@Override
				public void dealElement(Element element, int index) {
					ActionDefinition actionDefinition = new ActionDefinition();
					String action = element.getAttribute("name");
					
					String className = element.getAttribute("class");
					String methodName = element.getAttribute("method");
					try {
						actionDefinition.setKlass(className);
						new XMLParser() {
							@Override
							public void dealElement(Element element, int index) {
								String paraName = element.getAttribute("name");
								String paraType = element.getAttribute("type");
								
								actionDefinition.addParameter(paraName, paraType);
							}
						}.parse(element, "para");
						actionDefinition.setMethod(methodName);
						
						actionMap.put(action, actionDefinition);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.parse(XMLParser.loadXml(actionMappingPath), "action");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static ActionDefinition getAction(String action) {
		return actionMap.get(action);
	}
	
}
