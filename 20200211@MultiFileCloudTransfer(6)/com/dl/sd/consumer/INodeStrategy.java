package com.dl.sd.consumer;

import java.util.List;

import com.dl.multi_file.netWork.NetNode;
import com.dl.sd.registry.INetNode;

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
	INetNode ServerBalance(Consumer consumer, String serviceTag, List<NetNode> nodeList);
}
