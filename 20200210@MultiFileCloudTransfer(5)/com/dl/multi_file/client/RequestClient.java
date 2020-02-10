package com.dl.multi_file.client;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.resource.SectionInfo;
import com.dl.rpc.client.RPCClient;
import com.dl.rpc.client.RPCClientProxy;
import com.dl.sd.consumer.Consumer;

public class RequestClient {
	private Consumer consumer;
	
	private IClientCenterAction centerAction;
	private IClientServerAction serverAction;
	
	private RPCClient rpcClient;
	
	public RequestClient() {
		consumer = new Consumer();
		
		rpcClient = new RPCClient();
		RPCClientProxy proxy = new RPCClientProxy();
		proxy.setClient(rpcClient);
		centerAction = proxy.jdkProxy(IClientCenterAction.class);
		serverAction = proxy.jdkProxy(IClientServerAction.class);
	}
	
	/**
	 * �ṩ������Դ��������ȫ����Դ����б�ķ���
	 * @return
	 */
	public List<String> updataResourceHandles() {
		return consumer.updataServiceTags();
	}

	/**
	 * �ṩ������Դ������ض�Ӧ����б�ķ���
	 * @param resourceName ��Դ���
	 * @return
	 */
	public List<NetNode> updataNews(String resourceHandle) {
		return consumer.updataNews(resourceHandle);
	}
	
	public void setResourceServer(NetNode netNode) {
		rpcClient.setIp(netNode.getIp());
		rpcClient.setPort(netNode.getPort());
	}
	
	/**
	 * �ṩͨ��rpc�������������Դ�ķ���
	 * @param recipient ���շ�������Ϣ
	 * @param sectionList ��Դ��Ϣ�б�
	 * @return
	 * @throws Throwable 
	 */
	public void requestResource(NetNode recipient, List<SectionInfo> sectionList) throws Throwable {
		serverAction.requestResource(recipient, sectionList);
	}
	
	/**
	 * �����ַ��������ߣ��ͻ���ͨ��rpc�������������
	 */
	public void reportDropped() {
		try {
			centerAction.reportDropped();
		} catch (Throwable e) {
			// ��������崻�����������
		}
	}
}
