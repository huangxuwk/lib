package com.dl.multi_file.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.dl.multi_file.client.IClientServerAction;
import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.netWork.Transmission;
import com.dl.multi_file.resource.LocalResources;
import com.dl.multi_file.resource.SectionInfo;
import com.dl.rpc.server.RPCProxyAnntotation;

@RPCProxyAnntotation(interfaces = {IClientServerAction.class})
public class ClientServerAction implements IClientServerAction {
	private Transmission transmission;
	private LocalResources localResources;

	public ClientServerAction() {
		transmission = new Transmission();
		localResources = new LocalResources();
		localResources.scanLocalResource();
	}
	
	@Override
	public void requestResource(NetNode recipient, List<SectionInfo> resourceList) throws Throwable {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Socket receiver = null;
				try {
					receiver = new Socket(recipient.getIp(), recipient.getPort());
					DataOutputStream dos = new DataOutputStream(receiver.getOutputStream());
					SectionInfo i = localResources.getSection("\\2.txt:0:15");
					transmission.sendObject(dos, i);
					
					transmission.sendData(dos, localResources.readFromLocal(i));
					transmission.sendObject(dos, null);
					
					
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						receiver.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

}
