package com.dl.multi_file.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.resource.LocalResources;
import com.dl.rpc.server.RPCServer;
import com.dl.sd.provider.Provider;
import com.dl.sd.registry.INetNode;
import com.util.ThreadPoolFactory;

/**
 * ��Դ����������Դ�ṩ�ߣ�<br>
 * 1��ͨ�������ֵ��ṩ�߶�����ע��ע����Դ��<br>
 * 2����Ϊrpc�����������տͻ��˵�����<br>
 * 3�����������ṩ�˽�������Դȫ��ע�ᵽ�������ĵķ�����
 * @author dl
 *
 */
public class ResourceServer {
	private Provider provider;
	private RPCServer rpcServer;
	
	private LocalResources localResources;
	private INetNode netNode;
	
	public ResourceServer() {
		provider = new Provider();
		rpcServer = new RPCServer();
	}
	
	public void setLocalResources(LocalResources localResources) {
		this.localResources = localResources;
	}
	
	public LocalResources getLocalResources() {
		return localResources;
	}
	
	/**
	 * ��ȡָ��·���µ�rpc�����ļ�
	 * @param path
	 */
	public void readRPCConfig(String path) {
		rpcServer.readConfig(path);
	}
	
	public void shutdown() {
		rpcServer.shutdown();
		new ThreadPoolFactory().close(false);
	}
	
	/**
	 * ������������ע�᱾����Դ<br>
	 * 1�����������߷�������ǰ���ǿͻ���ɨ�赽��������Դ��������ĳЩ��Դ��<br>
	 * 2��������Ҫ����һ���߾ͽ�������Դȫ��ע�ᵽ�������ģ�
	 * @throws Throwable ע��ʧ�ܣ�rpc����ʧ��
	 */
	public void registryLocalResource() throws Throwable {
		List<String> resourceList = localResources.resourceList();
		registryResource(resourceList);
	}
	
	private void getLocalNet() {
		String ip = getLocalIp();
		int port = rpcServer.getPort();
		NetNode netNode = new NetNode(ip, port);
		this.netNode = netNode;
		provider.setNetNode(netNode);
	}
	
	public void registryResource(List<String> resourceList) throws Throwable {
		if (netNode == null) {
			getLocalNet();
		}
		provider.registryService(resourceList);		
	}
	
	public void registryResource(String resource) throws Throwable {
		List<String> resourceList = new ArrayList<>();
		resourceList.add(resource);
		registryResource(resourceList);
	}
	
	/**
	 * ע����Դ����rpc���𣬿��ܻᷢ��ʧ�ܣ�<br>
	 * ʧ�ܺ��֪��һ�㣻
	 * @param resourceList ע����Դ�б�
	 * @throws Throwable ע��ʧ�ܣ�rpc����ʧ��
	 */
	public void cancellationService(List<String> resourceList) throws Throwable {
		if (netNode == null) {
			getLocalNet();
		}
		provider.cancellationService(resourceList);
	}
	
	public void cancellationService(String resource) throws Throwable {
		List<String> resourceList = new ArrayList<>();
		resourceList.add(resource);
		cancellationService(resourceList );
	}

	public static String getLocalIp() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			String ip = address.getHostAddress();
			return ip;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
}
