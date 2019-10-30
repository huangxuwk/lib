package com.hx.database.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.util.XMLParser;

public class ClassTableFactory {
	private static final Map<String, ClassTable> classTableMap;
	
	static {
		classTableMap = new HashMap<>();
	}
	
	public ClassTableFactory() {
	}

	/*
	 * ����XML�ļ��������ݷŽ�Map��
	 */
	public void loadMapping(String path) {
		try {
			new XMLParser() {
				@Override
				public void dealElement(Element element, int index) {
					String className = element.getAttribute("class");
					String tableName = element.getAttribute("table");
					
					/*
					 * ClassTable�Ķ����Ӧ�ļ����һ�������� mapping 
					 */
					ClassTable ct = new ClassTable();
					try {
						// �Խ���
						Class<?> klass = Class.forName(className);
						ct.setklass(klass);
						ct.setTable(tableName);
						// ���� column 
						new XMLParser() {
							@Override
							public void dealElement(Element element, int index) {
								// �˴��� id ���жϸ��ֶ��Ƿ�Ϊ�ؼ���
								String id = element.getAttribute("id");
								String property = element.getAttribute("property");
								String column = element.getAttribute("name");
								
								// һ��PropertyColumn�����Ӧһ����Ա�����ֶ���
								PropertyColumn pc = new PropertyColumn();
								try {
									// Field Ϊ������������У���get�������Եõ���Ա��������Ϣ������Ȩ�ޡ�����
									pc.setField(klass.getDeclaredField(property));
									pc.setColumn(column);
									
									// ����õ���id���ַ������ȴ���0�����ʾ��ֵ�����ֶ�Ϊ�ؼ���
									if (id.length() > 0) {
										ct.setId(pc);
									}
									ct.addProperty(pc);
								} catch (NoSuchFieldException | SecurityException e) {
									e.printStackTrace();
								}
							}
						}.parse(element, "column");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					classTableMap.put(className, ct);
				}
			}.parse(XMLParser.loadXml(path), "mapping");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * �������������������Եõ�������Ӧ�ı����Ϣ
	 */
	public static ClassTable getClassTable(Class<?> klass) {
		return classTableMap.get(klass.getName());
	}
	
	public static ClassTable getClassTable(String className) {
		return classTableMap.get(className);
	}
	
}
