package com.dl.consumer.core;

import java.util.List;

import com.dl.registry.core.NetNode;

/**
 * ���ѡ����Խӿ�<br>
 * 1������ע�����Ļ�ȡ�������б����������Ҫ����ѡ���㣻<br>
 * 2��Ϊ���ⵥ������������ѹ����������⣬��Ҫ���и��ؾ��⣻<br>
 * 3�����ؾ���ʵ�ֵ��ַ��϶࣬��˸��û����˽ӿڣ�
 * 
 * @author dl
 *
 */
public interface INodeStrategy {
	NetNode ServerBalance(Consumer consumer, String serviceTag, List<NetNode> nodeList);
}
