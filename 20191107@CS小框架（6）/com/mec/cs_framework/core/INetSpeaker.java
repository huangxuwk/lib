package com.mec.cs_framework.core;

public interface INetSpeaker {
	void addListener(INetListener listener);
	void removeListener(INetListener listener);
}
