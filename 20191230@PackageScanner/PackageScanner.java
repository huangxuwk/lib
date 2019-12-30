package com.parser_reflect.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class PackageScanner {
	
	// ���󷽷����ṩ������ʹ���ߴ����ļ����ֶ�
	public abstract void dealClass(Class<?> klass);
	
	private void dealClassFile(String rootPackage, File file) {
		String fileName = file.getName();
		if (fileName.endsWith(".class")) {
			fileName = fileName.replace(".class", "");
			try {
				Class<?> klass = Class.forName(rootPackage + "." + fileName);
				// ɨ�赽�ಢ�õ�Class��ʹ�ó��󷽷���������ʹ���ߴ���
				dealClass(klass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ɨ����ͨ��
	private void dealDirectory(String rootPackage, File curFile) {
		File[] fileList = curFile.listFiles();
		for (File file : fileList) {
			// �ж��Ƿ�ΪĿ¼������Ŀ¼�����γɵݹ飬ɨ�����е�Ŀ¼���ļ�
			if (file.isDirectory()) {
				dealDirectory(rootPackage + "." + file.getName(), file);
			} else if (file.isFile()) {
				dealClassFile(rootPackage, file);
			}
		}
	}
	
	// ɨ��jar��
	private void dealJarPackage(URL url) {
		try {
			JarURLConnection connection = (JarURLConnection) url.openConnection();
			JarFile jarFile = connection.getJarFile();
			Enumeration<JarEntry> jarEntries = jarFile.entries();
			// ����ɨ��ð��ڵ�����Ŀ¼���ļ�
			while (jarEntries.hasMoreElements()) {
				JarEntry jar = jarEntries.nextElement();
				if(jar.isDirectory() || !jar.getName().endsWith(".class")) {
					continue;
				}
				String jarName = jar.getName();
				jarName = jarName.replace(".class", "");
				jarName = jarName.replace("/", ".");
				
				try {
					Class<?> klass = Class.forName(jarName);
					// ɨ�赽�ಢ�õ�Class��ʹ�ó��󷽷���������ʹ���ߴ���
					dealClass(klass);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ʹ�ð�������ɨ��
	public void packageScanner(String packageName) {
		String rootPackage = packageName;
		packageName = packageName.replace(".", "/");
		URL url = Thread.currentThread().getContextClassLoader().getResource(packageName);
		try {
			// �жϸð�Ϊ��ͨ������jar����ѡ���õķ���������
			if (url.getProtocol().equals("file")) {
				URI uri = url.toURI();
				File root = new File(uri);
				dealDirectory(rootPackage, root);
			} else {
				dealJarPackage(url);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	// ʹ����������ɨ��
	public void packageScanner(Class<?> klass) {
		packageScanner(klass.getPackage().getName());
	}
 
	public void packageScanner(Class<?>[] classes) {
		for(Class<?> klass : classes) {
			packageScanner(klass);
		}
	}
 
	public void packageScanner(String[] packages) {
		for(String packageName : packages) {
			packageScanner(packageName);
		}
	}

}
