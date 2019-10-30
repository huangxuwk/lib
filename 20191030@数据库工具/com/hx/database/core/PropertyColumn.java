package com.hx.database.core;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyColumn {
	// 并不知道成员的类型，所以用反射机制的field定义
	private Field field;
	private String column;
	
	public PropertyColumn() {
	}

	public void setProperty(ResultSet resultSet, Object object) {
		try {
			// 从表的当前一条记录中，获取以column的值为字段名的字段的值
			Object value = resultSet.getObject(column);
			System.out.println(value + "value");
			// 设置field(成员)的可访问性为真，即，private成员都可以被访问
			field.setAccessible(true);
			// 更改对象object的field成员的值为value
			field.set(object, value);
			System.out.println(field);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
	}
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
	
	@Override
	public String toString() {
		return field + " : " + column;
	}

}
