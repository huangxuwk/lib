package com.mec.util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TempViewTool {
	
	public TempViewTool() {
	}

	public static void setLabelImage(JLabel jlbl, byte[] image) {
		int width = jlbl.getWidth();
		int height = jlbl.getHeight();
		ImageIcon icon = new ImageIcon(new ImageIcon(image)
				.getImage().getScaledInstance(width, height,
				Image.SCALE_FAST));
		jlbl.setIcon(icon);
	}
	
}
