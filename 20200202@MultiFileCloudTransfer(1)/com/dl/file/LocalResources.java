package com.dl.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import com.parser_reflect.util.PropertiesParser;

/**
 * ɨ���ļ�����д�ļ��Ļ�����<br>
 * 1������Ĭ�ϸ�·����Ҳ��ͨ��properties�ļ����ã�<br>
 * 2�����ݸ�·����ȡ·���µ��ļ�����ɨ�赽����Դ������map�У�<br>
 * 3���ṩRandomAccessFile������ļ���д��
 * @author dl
 *
 */
public class LocalResources {
	public static final String DEFAULT_PATH = "N:\\LocalResources";

	// ����Ƶ�ļ���Ϊ�������ļ���������Ϊֵ
	private static final Map<String, SectionInfo> systemMap = new HashMap<>();
	private String rootPath;
	
	public LocalResources() {
		rootPath = DEFAULT_PATH;
		readConfig("/cloudTransfer.config.properties");
	}
	

	
	public void readConfig(String path) {
		PropertiesParser.load(path);
		
		String rootPath = PropertiesParser.findElement("rootPath");
		if (rootPath != null && !rootPath.equals("")) {
			this.rootPath = rootPath;
		}
	}
	
	/**
	 * ���ݸ�Ŀ¼��ȡ������Դ��
	 */
	public Map<String, SectionInfo> scanLocalResource() {
		File rootFile = new File(rootPath);
		scanLocalResource(rootFile);
		
		return new HashMap<>(systemMap);
	}
	
	/**
	 * ���еݹ���ļ�ɨ�裻���ļ����;���·��������map�У�
	 * @param rootFile
	 */
	private void scanLocalResource(File rootFile) {
		File[] files = rootFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String filePath = file.getAbsolutePath();
				String path = filePath.replaceAll(rootPath, "");
				int length = (int) file.length();
				SectionInfo info = new SectionInfo(path, 0, length);
				systemMap.put(path, info);
			} else {
				scanLocalResource(file);
			}
		}		
	}
	
	/**
	 * �����ļ�����ȡָ��ƫ�����ͳ��ȵ��ֽ����ݣ�
	 * @param name
	 * @param offset
	 * @param size
	 */
	public void readFromLocal(SectionInfo sectionInfo) {
		String path = sectionInfo.getFileName();
		int offset = sectionInfo.getOffset();
		int size = sectionInfo.getSize();
		String fileName = rootPath + "\\" + path;
		File file = new File(fileName);
		int length = (int) file.length();
		
		byte[] datas = new byte[length];
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "r");
			randomAccessFile.read(datas, offset, size);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomAccessFile != null) {
				try {
					randomAccessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ���ֽ�����д���ļ����ɶ�д����ƫ�Ƶ�ַ�ͳ��Ⱦ�����
	 * @param randomAccessFile
	 * @param datas
	 * @param offset
	 * @param size
	 */
	public void writeInLocal(RandomAccessFile randomAccessFile
			, byte[] datas, int offset, int size) {
		try {
			randomAccessFile.write(datas, offset, size);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ṩ���и�·�����ļ���д����
	 * @param name
	 * @return
	 */
	public RandomAccessFile getRandomAccessFile(String path) {
		String fileName = rootPath + "\\" + path;
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
			return randomAccessFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addResource(SectionInfo sectionInfo) {
		systemMap.put(sectionInfo.getFileName(), sectionInfo);
	}

}
