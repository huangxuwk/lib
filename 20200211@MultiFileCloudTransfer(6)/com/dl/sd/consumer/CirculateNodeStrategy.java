package com.dl.sd.consumer;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.sd.registry.INetNode;

/**
 * ��ͬ����ѭ��ʹ�÷�����<br>
 * �ŵ㣺���ѡ���Ϊ��<br>
 * ȱ�㣺�������Ŀͻ��˽��ѡ��ͬ��ʱ����Ȼ����ɷ������Ĺ���
 * 
 * @author dl
 *
 */
public class CirculateNodeStrategy implements INodeStrategy {
	private static int SerialNumber;
	
	public CirculateNodeStrategy() {
		SerialNumber = 0;
	}
	
	@Override
	public INetNode ServerBalance(Consumer consumer, String serviceTag, List<NetNode> nodeList) {
		if (SerialNumber >= nodeList.size()) {
			SerialNumber = 0;
		}
		
		return nodeList.get(SerialNumber++);
	}
	
}
