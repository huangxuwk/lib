package com.dl.mmfct.send;

import com.dl.mmfct.node.INetNode;
import com.dl.mmfct.resource.ResourceBaseInfo;
import com.dl.rmi.core.RMIIterfaceAnno;

@RMIIterfaceAnno(interfaces=IResourceSender.class)
public class ResourceSender implements IResourceSender {

	public ResourceSender() {
	}
	
	@Override
	public void sendSectionInfo(INetNode receiver, ResourceBaseInfo rbi) {
		SectionSender sectionSender = new SectionSender(receiver, rbi);
		
		new Thread(sectionSender).start();
	}

}
