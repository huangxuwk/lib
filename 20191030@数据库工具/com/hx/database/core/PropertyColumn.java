package com.hx.database.core;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyColumn {
	// ����֪����Ա�����ͣ������÷�����Ƶ�field����
	private Field field;
	private String column;
	
	public PropertyColumn() {
	}

	public void setProperty(ResultSet resultSet, Object object) {
		try {
			// �ӱ�ĵ�ǰһ����¼�У���ȡ��column��ֵΪ�ֶ������ֶε�ֵ
			Object value = resultSet.getObject(column);
			System.out.println(value + "value");
			// ����field(��Ա)�Ŀɷ�����Ϊ�棬����private��Ա�����Ա�����
			field.setAccessible(true);
			// ���Ķ���object��field��Ա��ֵΪvalue
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
