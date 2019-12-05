package com.mec.uddi.registry;

public interface INetSpeaker {
	void addListener(INetListener listener);
	void removeListener(INetListener listener);
}
