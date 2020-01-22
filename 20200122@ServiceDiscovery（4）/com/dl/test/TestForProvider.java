package com.dl.test;

import java.util.ArrayList;

import com.dl.provider.core.Provider;
import com.dl.provider.core.TimingQuery;

public class TestForProvider {

	public static void main(String[] args) {
		Provider provider = new Provider();
		TimingQuery timingQuery = new TimingQuery();
		provider.setTimingQuery(timingQuery);
		provider.startup();
		ArrayList<String> services = new ArrayList<>();
		System.out.println("1");
		provider.registryService(services);
//		provider.cancellationService(services);
		
		
//		provider.shutdown();
		
	}

}
