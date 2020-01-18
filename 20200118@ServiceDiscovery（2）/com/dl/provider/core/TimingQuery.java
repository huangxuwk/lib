package com.dl.provider.core;

public class TimingQuery implements ITimingQuery {

	@Override
	public void dealTimingQuery(Provider provider) {
		System.out.println("¶¨Ê±");
		provider.startup();
	}
	
}
