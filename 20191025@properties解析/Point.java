package com.hx.point;

public class Point {
	private int row;
	private int col;
	
	public static final int MIN_ROW = 1; 
	public static final int MAX_ROW = 25; 
	public static final int DEFAULT_ROW = 12;
	public static final int MIN_COL = 1; 
	public static final int MAX_COL = 80; 
	public static final int DEFAULT_COL = 40;
	
	public Point() {
		this(0, 0);
	}
	
	public Point(int row, int col) {
		setRow(row);
		setCol(col);
	}
	
	public void setRow(int x) {
		if (x < MIN_ROW || x > MAX_ROW) {
			x = DEFAULT_ROW;	// ����Ĭ�ϵ�һ��
		}
		this.row = x;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public void setCol(int y) {
		if (y < MIN_COL || y > MAX_COL) {
			y = DEFAULT_COL;	// ����Ĭ�ϵ�һ��
		}
		this.col = y;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void moveTo(int newRow, int newCol) {
		setRow(newRow);
		setCol(newCol);
	}

	@Override
	public String toString() {
		System.out.println("ִ����toString()������");
		return "(" + row + ", " + col + ")";
	}

}
