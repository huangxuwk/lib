package com.dl.multi_file.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.dl.multi_file.client.IClientServerAction;
import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.netWork.Transmission;
import com.dl.multi_file.resource.LocalResources;
import com.dl.multi_file.resource.SectionInfo;
import com.dl.rpc.client.RPCClient;
import com.dl.rpc.client.RPCClientProxy;
import com.dl.rpc.server.RPCProxyAnntotation;
import com.util.ThreadPoolFactory;

@RPCProxyAnntotation(interfaces = {IClientServerAction.class})
public class ClientServerAction implements IClientServerAction {
	private Transmission transmission;
	private LocalResources localResources;
	
	private static RPCClient rpcClient;
	private IServerCenterAction centerAction;
	
	private static NetNode netNode;

	public ClientServerAction() {
		transmission = new Transmission();
		localResources = new LocalResources();
		localResources.scanLocalResource();
		
		rpcClient = new RPCClient();
		RPCClientProxy proxy = new RPCClientProxy();
		proxy.setClient(rpcClient);
		centerAction = proxy.jdkProxy(IServerCenterAction.class);
	}
	
	/**
	 * 读取指定路径下的rpc配置文件
	 * @param path
	 */
	public static void readRPCConfig(String path) {
		rpcClient.readConfig(path);
	}
	
	@Override
	public void requestResource(NetNode recipient, List<SectionInfo> sectionList) throws Throwable {
		new ThreadPoolFactory().execute(new InnerSender(recipient, sectionList));
	}
	
	private List<String> sectionList2StringList(List<SectionInfo> sectionList) {
		List<String> resourceHandles = new ArrayList<>();
		for (SectionInfo sectionInfo : sectionList) {
			String handle = localResources.getIntactHandle(sectionInfo.toString());
			resourceHandles.add(handle);
		}
		return resourceHandles;	
	}
	
	void startNewTask(List<SectionInfo> sectionList)  {
		try {
			if (netNode == null) {
				netNode = ResourceServer.getNetNode();
			}
			centerAction.startNewTask(sectionList2StringList(sectionList), netNode);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	void accomplishTask(List<SectionInfo> sectionList) {
		try {
			if (netNode == null) {
				netNode = ResourceServer.getNetNode();
			}
			centerAction.accomplishTask(sectionList2StringList(sectionList), netNode);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	class InnerSender implements Runnable {
		private NetNode recipient;
		private List<SectionInfo> sectionList;
		private DataOutputStream dos;
		private Socket sender;
		
		public InnerSender(NetNode recipient, List<SectionInfo> sectionList) {
			this.recipient = recipient;
			this.sectionList = sectionList;
		}

		@Override
		public void run() {
			try {
				startNewTask(sectionList);
				
				sender = new Socket(recipient.getIp(), recipient.getPort());
				dos = new DataOutputStream(sender.getOutputStream());
				
				int bufferSize = Transmission.DEFAULT_BUFFFER_LENGTH;
				byte[] datas = new byte[bufferSize];
				SectionInfo header;
				for (SectionInfo section : sectionList) {
					RandomAccessFile random = localResources.getRandomAccessFileOnlyRead(section.getFileName());
					int offset = section.getOffset();
					int size = section.getSize();
					random.seek(offset);
					int readLen = 0;
					int resLen = size;
					int len;
					while (resLen > 0) {
						len = resLen > bufferSize ? bufferSize : resLen;
						if (len < bufferSize) {
							datas = new byte[len];
						}
						readLen = random.read(datas, 0, len);
						
						header = new SectionInfo(section.getFileName(), offset, readLen);
						transmission.sendObject(dos, header);
						transmission.sendData(dos, datas);						
						
						offset += readLen;
						resLen -= readLen;
					}
					random.close();
				}
				transmission.sendObject(dos, null);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			accomplishTask(sectionList);
		}	
		
		private void close() {
			try {
				if (!sender.isClosed() && sender != null) {
					sender.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				sender = null;
			}
			try {
				if (dos != null) {
					dos.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				dos = null;
			}
		}
	}
}
