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
	 * 解析XML文件，将内容放进Map里
	 */
	public void loadMapping(String path) {
		try {
			new XMLParser() {
				@Override
				public void dealElement(Element element, int index) {
					String className = element.getAttribute("class");
					String tableName = element.getAttribute("table");
					
					/*
					 * ClassTable的对象对应文件里的一个完整的 mapping 
					 */
					ClassTable ct = new ClassTable();
					try {
						// 对接类
						Class<?> klass = Class.forName(className);
						ct.setklass(klass);
						ct.setTable(tableName);
						// 处理 column 
						new XMLParser() {
							@Override
							public void dealElement(Element element, int index) {
								// 此处的 id 是判断该字段是否为关键字
								String id = element.getAttribute("id");
								String property = element.getAttribute("property");
								String column = element.getAttribute("name");
								
								// 一个PropertyColumn对象对应一个成员名和字段名
								PropertyColumn pc = new PropertyColumn();
								try {
									// Field 为反射机制所独有，用get方法可以得到成员的所有信息，包括权限、类型
									pc.setField(klass.getDeclaredField(property));
									pc.setColumn(column);
									
									// 如果得到的id的字符串长度大于0，则表示有值，此字段为关键字
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
	 * 根据所给的类名，可以得到其所对应的表的信息
	 */
	public static ClassTable getClassTable(Class<?> klass) {
		return classTableMap.get(klass.getName());
	}
	
	public static ClassTable getClassTable(String className) {
		return classTableMap.get(className);
	}
	
}
