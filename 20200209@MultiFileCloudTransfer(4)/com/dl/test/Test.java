package com.dl.test;

import java.util.ArrayList;
import java.util.List;

import com.dl.multi_file.resource.SectionInfo;
import com.dl.multi_file.strategy.ResourceStrategy;

public class Test {
	
	public static void main(String[] args) {
//		long t1 = System.currentTimeMillis();
//		long t2 = System.currentTimeMillis();
//		System.out.println(t2 - t1);
		
//		List<NetNode> nodeList = new ArrayList<>();
//		NetNode n1 = new NetNode("1", 1);
//		n1.setSendCount(1);
//		n1.setSendingCount(4);
//		NetNode n2 = new NetNode("2", 2);
//		n2.setSendCount(1);
//		n2.setSendingCount(3);
//		NetNode n3 = new NetNode("3", 3);
//		n3.setSendCount(0);
//		NetNode n4 = new NetNode("4", 4);
//		n4.setSendingCount(5);
//		NetNode n5 = new NetNode("5", 5);
//		n5.setSendCount(3);
//		nodeList.add(n1);
//		nodeList.add(n2);
//		nodeList.add(n3);
//		nodeList.add(n4);
//		nodeList.add(n5);
//		
//		NodeStrategy s = new NodeStrategy(3, -1);
//		List<NetNode> result = s.doNodeStrategy(nodeList);
//		for (NetNode netNode : result) {
//			System.out.println(netNode);
//		}
		
		ResourceStrategy s = new ResourceStrategy();
		List<SectionInfo> sectionList = new ArrayList<>();
		sectionList.add(new SectionInfo("1", 0, 50));
		sectionList.add(new SectionInfo("2", 10, 60));
		sectionList.add(new SectionInfo("3", 0, 100));
		sectionList.add(new SectionInfo("4", 0, 100));
		sectionList.add(new SectionInfo("5", 0, 100));
		sectionList.add(new SectionInfo("6", 0, 100));
		
		List<List<SectionInfo>> list = s.doResourceStrategy(sectionList, 10);
		for (List<SectionInfo> list2 : list) {
			for (SectionInfo list3 : list2) {
				System.out.println(list3);
			}
			System.out.println("----------");
		}
	}
}
