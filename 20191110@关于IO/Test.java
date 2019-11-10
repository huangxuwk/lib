package com.dl.about_io.test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

public class Test {
	
		public static void main(String[] args) {
			// 得到根目录
			File[] driver = File.listRoots();
			for (File file : driver) {
				long totalSpace = file.getTotalSpace();
				long freeSpace = file.getFreeSpace();
				System.out.println(file.getAbsolutePath() + "  tatal : " + Bytes2String.bytesToString(totalSpace * 100)
				+ "  free: " + Bytes2String.bytesToString(freeSpace));
			}
			
			// 得到当前目录
			String currentPath = null;
			File cDriver = new File("N:");
			currentPath = cDriver.getAbsolutePath();
			System.out.println(currentPath);
			
			System.out.println();		
			String curPath = "N:\\Java";
			File curDir = new File(curPath);
			File[] curFiles = curDir.listFiles();
			System.out.println(Arrays.toString(curFiles));
			
			System.out.println();
			for (File curFile : curFiles) {
				// 可以判断是否为文件，如果文件夹的后缀为.txt
				// 就会对后续对文件的处理产生影响
				if (curFile.isFile()) {
					String fileName =curFile.getName();
					if (fileName.endsWith(".txt")) {
						// TODO 可以在这进行对指定类型文件的处理
						System.out.println(fileName);
					}
				}
			}
			
			File[] selectFiles = curDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					String fileName = pathname.getName();
					return fileName.endsWith(".txt");
				}
			});
			System.out.println();
			System.out.println(Arrays.toString(selectFiles));
			// TODO 可以在这进行对指定类型文件的处理
			// for-each
			
			curDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (!pathname.isFile()) {
						return false; 
					}
					String fileName = pathname.getName();
					if (fileName.endsWith(".txt")) {
						// TODO 可以在这进行对指定类型文件的处理
					}
					return false;
				}
			});
			
		}
}
