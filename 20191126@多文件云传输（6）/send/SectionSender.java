package com.dl.mmfct.send;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.ResourceBaseInfo;
import com.dl.mmfct.resource.ResourcePool;
import com.dl.mmfct.resource.ResourceStructInfo;
import com.dl.mmfct.section.FileSection;
import com.dl.mmfct.section.SectionInfo;

public class SectionSender implements Runnable {
	private Map<String, RandomAccessFile> rafBuffer;
	private INetNode receiver;
	private ResourceBaseInfo serverRbi;
	
	private Socket socket;
	private DataOutputStream dos;
	
	SectionSender() {
		this(null, null);
	}
	
	SectionSender(INetNode receiver, ResourceBaseInfo serverRbi) {
		this.receiver = receiver;
		this.serverRbi = serverRbi;
		rafBuffer = new HashMap<>();
	}
	
	SectionSender setReceiver(INetNode receiver) {
		this.receiver = receiver;
		
		return this;
	}
	
	SectionSender setServerRbi(ResourceBaseInfo serverRbi) {
		this.serverRbi = serverRbi;
		
		return this;
	}
	
	void connectToReceiver() throws UnknownHostException, IOException {
		this.socket = new Socket(receiver.getIp(), receiver.getPort());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}
	
	private ResourceStructInfo getRsiByFileHandle(int fileHandle, List<ResourceStructInfo> rsiList) {
		for (ResourceStructInfo rsi : rsiList) {
			if (rsi.getFileHandle() == fileHandle) {
				return rsi;
			}
		}
		
		return null;
	}
	
	private RandomAccessFile getRaf(String filePath) throws FileNotFoundException {
		RandomAccessFile raf = rafBuffer.get(filePath);
		if (raf == null) {
			raf = new RandomAccessFile(filePath, "r");
			rafBuffer.put(filePath, raf);
		}
		
		return raf;
	}
	
	private void closeFile() {
		for (RandomAccessFile raf : rafBuffer.values()) {
			try {
				raf.close();
			} catch (IOException e) {
			}
		}
	}
	
	private void close() {
		// 如果在socket和dos关闭后this还要继续存活才有做finally的意义
		try {
			if (!socket.isClosed() && socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket = null;
		}
		try {
			if (dos != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dos = null;
		}
	}
	
	void sendSection() {
		String appName = serverRbi.getAppName();
		ResourceBaseInfo rbi = ResourcePool.getResourceBaseInfo(appName);
		String absoluteRoot = rbi.getAbsoluteRoot();
		
		List<ResourceStructInfo> rsiList = rbi.getRsiList();
		List<SectionInfo> sectionList = serverRbi.getSiList();
		
		RandomAccessFile raf = null;
		for (SectionInfo section : sectionList) {
			int fileHandle = section.getFileHandle();
			ResourceStructInfo rsi = getRsiByFileHandle(fileHandle, rsiList);
			
			String filePath = absoluteRoot + rsi.getFilePath();
			long offset = section.getOffset();
			int size = section.getSize();
			
			try {
				raf = getRaf(filePath);
				raf.seek(offset);
				byte[] value = new byte[size];
				raf.read(value);
				
				FileSection fSection = new FileSection();
				fSection.setSection(section);
				fSection.setValue(value);
				fSection.sendSection(dos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		closeFile();
		
		// TODO rmiClient调用资源分发器发送次数加一
	}
	
	@Override
	public void run() {
		try {
			connectToReceiver();
			sendSection();
			close();
		} catch (UnknownHostException e) {
			// 日志信息，虽然发送失败，但总要记载失败记录
		} catch (IOException e) {
		}

	}

}
