package com.swing.util;

public interface ISwing{
	default void initSwing() {
		init();
		dealAction();
		reinit();
	}
	
	void init();
	void reinit();
	void dealAction();
	void showView();
	void closeView();
}
