package com.hx.cs_framework.core;

/**
 * 观察者模式
 * 无论将要实现服务器的界面是什么类型的
 * 都可以通过这个接口来实现
 * 比如给服务器界面反馈信息，可以反馈在任何
 * 工具编写者不知道的界面上
 * 想要使用这个模式，就需要调用另一个接口
 * INetListener, 在这个接口中包括一个方法
 * 这个方法的目的单一，就是由用户决定在哪个界面上
 * 反馈信息，由于这个方法是抽象方法，所以用户使用
 * 就必须先实现它，那么就达到了观察者模式的目的。
 * @author chaojidalao
 *
 */
/*
 * 说话者，即反馈信息的，将需要反馈的信息反馈给侦听者
 */
public interface INetSpeaker {
	void addListener(INetListener listener);
	void removeListener(INetListener listener);
}
