package com.dl.mmfct.view;

import java.net.Socket;

public interface IReceiveViewAction {
	void getSenderCount(int senderCount);
	void linkedOneSender(Socket sender);
}
