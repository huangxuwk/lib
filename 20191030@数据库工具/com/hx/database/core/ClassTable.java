package com.hx.database.core;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassTable {
	private Class<?> klass;
	private String table;
	private List<PropertyColumn> fieldList;
	private PropertyColumn id;
	
	public ClassTable() {
		fieldList = new ArrayList<>();
	}
	
	public void setFieldFromResultSet(ResultSet resultSet, Object object) {
		// 遍历整个列表，将每个成员都赋值为表中字段所对应的值
		for (PropertyColumn field : fieldList) {
			field.setProperty(resultSet, object);
			System.out.println("++++++++");
		}
	}
	
	public void addProperty(PropertyColumn property) {
		fieldList.add(property);
	}
	 
	public String getIdString() {
		return table + "." + id.getColumn();
	}
	
	public String getColumnString() {
		StringBuffer res = new StringBuffer();
		
		boolean first = true;
		for (PropertyColumn field : fieldList) {
			res.append(first ? "" : ", ")
					.append(table).append(".")
					.append(field.getColumn());
			
			first = false;
		}
		
		return res.toString();
	}

	public Class<?> getKlass() {
		return klass;
	}

	public void setklass(Class<?> klass) {
		this.klass = klass;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public PropertyColumn getId() {
		return id;
	}

	public void setId(PropertyColumn id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		
		res.append(klass.getName()).append("\n")
				.append("表:").append(table).append("\n");
		for (PropertyColumn pc : fieldList) {
			res.append("\t").append(pc.getField().getName())
					.append("<==>")
					.append(pc.getColumn())
					.append("\n");
		}
		
		return res.toString();
	}
	
}
