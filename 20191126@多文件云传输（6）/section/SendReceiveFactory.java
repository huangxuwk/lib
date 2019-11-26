package com.dl.mmfct.section;

public class SendReceiveFactory {
	// volatile 很关键，多线程问题
	private static volatile BytesSendReceive bytesSendReceive;
	
	private SendReceiveFactory() {
	}
	
	// 方法重载，调用有参数的newInstance()方法，参数设置为临界值，0就是临界值
	public static BytesSendReceive newInstance() {
		return newInstance(0);
	}
	// 大黄：关键就在这，因为buffersize有默认值，也可以后期设置，所以我给了重载
	/*
	 * 下面的锁其实就比较简单了，咱们以前用过很多回了
	 * 第一次调用这个方法的时候，先判断成员bytesSendReceive有没有初始化
	 * 没有就初始化，从此以后就返回这个成员就行，这就是单例工厂模式
	 * 有没有觉得其实很傻逼，但不得不说很好用
	 * 小常：想问为啥要用buffersize，只是因为它有默认值？
	 * 大黄：因为产生BytesSendReceive对象有两种方式，所以可以给一个buffersize
	 * 小常：没懂，想发哭笑的表情  哈哈 可爱
	 * 大黄：咱们当时写的那个接收和发送文件的类里面，每次接收一定长度的字节数组
	 * 			 那个长度是可以设置的呀，因为每台电脑可能适合的长度不一样，所以构造方法有两种
	 * 小常：那为啥两个里面都有buffersize   我明白了
	 * 大黄：我在下面判断是否大于0，大于就说明用户设置了bufferSize，小于就用默认构造方法
	 * 你帮我拖到最下面   所以这个类的作用只是接收和发送然后捎带确定一下每个部分字节数组的长度？
	 *你说的哪个类？（SendReceiveFactory？？不我洗是这个，但是我不知道说的意思对不对，我只是看着现在有
	 *的代码来说的但是从这个类的名字来看有种列表或者数组的意思）
	 *不是！（我没凶你哦嘿嘿）
	 *这个类作用单一：产生一个BytesSendReceive对象！剩下的我都不管  
	 *是不是只管接收和发送，还是说只管产生这个对象里面的工作也不管
	 *你可能理解的不太对
	 *单例工厂就是产生一个对象，它不管你有啥功能，你只要是个类，我就负责给你产生一个对象就行，只不过
	 *所有来工厂取对象的，得到的都是一个相同的对象
	 *打个比方：富士康产手机
	 *大黄去找他 让它造一堆 大黄牌手机，你知道告诉它怎么造手机就行（就是 new 对象）
	 *小常也找他 让它造小常牌 ，同样他知道这手机怎么new就行，别的它不管，他不管你这手机能不能打电话，发消息，接电话等等
	 *这么来说好像个傻子。。。。对啊 懂了，但是我还是有点不理解他的存在
	 *
	 *你居然删掉了，你一会儿系不系也会把这个删掉 暂时不会 哈哈哈  哼
	 *我在想给你讲啥   那你想我看下你的代码
	 */
	
	public static BytesSendReceive newInstance(int bufferSize) {
		if (bytesSendReceive == null) {
			synchronized(SendReceiveFactory.class) {
				if (bytesSendReceive == null) {
					bytesSendReceive = bufferSize > 0
								? new BytesSendReceive(bufferSize)
								:  new BytesSendReceive();
				}
			}
		}
		
		return bytesSendReceive;
	}
}
