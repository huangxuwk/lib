package com.dl.multi_file.strategy;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;

/**
 * ���ѡ����Խӿ�
 * @author dl
 *
 */
public interface INodeStrategy {
	/**
	 * �����ѡ��ķ���<br>
	 * ͨ�������Ľ���б���ѡ�����ʵĽ�㣻
	 * @param nodeList ����б�
	 * @return ѡ���Ľ���б�
	 */
	List<NetNode> doNodeStrategy(List<NetNode> nodeList);
}
