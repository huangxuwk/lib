package com.dl.multi_file.center;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.multi_file.server.IServerCenterAction;
import com.dl.rpc.server.RPCProxyAnntotation;
import com.dl.sd.registry.ServiceCache;

@RPCProxyAnntotation(interfaces = {IServerCenterAction.class})
public class ServerCenterAction implements IServerCenterAction {
	private ServiceCache cashe;

	public ServerCenterAction() {
		cashe = new ServiceCache();
	}
	
	@Override
	public void startNewTask(List<String> resourceHandles, NetNode netNode) throws Throwable {
		for (String handle : resourceHandles) {
			List<NetNode> nodeList = cashe.getNodeList(handle);
			if (nodeList != null) {
				int index = nodeList.indexOf(netNode);
				if (index >= 0) {
					NetNode target = nodeList.get(index);
					target.startNewTask();
				}
			}
		}
	}

	@Override
	public void accomplishTask(List<String> resourceHandles, NetNode netNode) throws Throwable {
		for (String handle : resourceHandles) {
			List<NetNode> nodeList = cashe.getNodeList(handle);
			if (nodeList != null) {
				int index = nodeList.indexOf(netNode);
				if (index >= 0) {
					NetNode target = nodeList.get(index);
					target.accomplishTask();
				}
			}
		}		
	}

}
