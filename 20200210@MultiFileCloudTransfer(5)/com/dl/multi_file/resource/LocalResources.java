package com.dl.multi_file.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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

	// ���ļ�������ʼ��ַ���ļ����ַ���Ϊ�������ļ���������Ϊֵ
	private static final Map<String, SectionInfo> systemMap = new HashMap<>();
	private static String rootPath;
	
	public LocalResources() {
		rootPath = DEFAULT_PATH;
	}
	
	public static void setRootPath(String rootPath) {
		LocalResources.rootPath = rootPath;
	}
	
	public void readConfig(String path) {
		PropertiesParser.load(path);
		
		String rootPath = PropertiesParser.findElement("rootPath");
		if (rootPath != null && !rootPath.equals("")) {
			LocalResources.rootPath = rootPath;
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
	 * ���еݹ���ļ�ɨ�裻
	 * @param rootFile
	 */
	private void scanLocalResource(File rootFile) {
		File[] files = rootFile.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String filePath = file.getAbsolutePath();
				int len = rootPath.length();
				String path = filePath.substring(len);
				int length = (int) file.length();
				SectionInfo info = new SectionInfo(path, 0, length);
				systemMap.put(info.toString(), info);
			} else {
				scanLocalResource(file);
			}
		}		
	}
	
	/**
	 * ����map�ļ������Ա��ϲ�ʹ��
	 * @return
	 */
	public List<String> resourceList() {
		return new ArrayList<>(systemMap.keySet());
	}
	
	public SectionInfo getSection(String resourceHandle) {
		return systemMap.get(resourceHandle);
	}
	
	/**
	 * �����ļ�������ȡ�ļ�������Ϣ���ַ�����
	 * @param path
	 * @return
	 */
	public String getIntactHandle(String path) {
		String[] str = path.split(":");
		Collection<String> handles = systemMap.keySet();
		for (String string : handles) {
			if (string.split(":")[0].equals(str[0])) {
				return string;
			}
		}
		return null;
	}
	
	/**
	 * �����ļ�����ȡָ��ƫ�����ͳ��ȵ��ֽ����ݣ�
	 * @param name
	 * @param offset
	 * @param size
	 */
	public byte[] readFromLocal(SectionInfo sectionInfo) {
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
		return datas;
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
	
	public RandomAccessFile getRandomAccessFileOnlyRead(String path) {
		String fileName = rootPath + "\\" + path;
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r");
			return randomAccessFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public void addResource(SectionInfo sectionInfo) {
		systemMap.put(sectionInfo.toString(), sectionInfo);
	}

}
