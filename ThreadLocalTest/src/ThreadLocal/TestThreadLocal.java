package ThreadLocal;

import java.util.concurrent.TimeUnit;

public class TestThreadLocal {

	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
	public static void main(String[] args) throws InterruptedException {
		thread1 thread1 = new thread1();
		thread2 thread2 = new thread2();
		thread2.start();
		TimeUnit.MILLISECONDS.sleep(1000);//睡眠，等待线程2执行完毕
		thread1.start();
		TimeUnit.MILLISECONDS.sleep(1000);//睡眠，等待线程2执行完毕
		System.out.println("main:"+threadLocal.get());
	}

	public static class thread1 extends Thread{
		public void run() {
			threadLocal.set(123);
			System.out.println(threadLocal.get());
		}
	}
	public static class thread2 extends Thread{
		public void run() {
			
			System.out.println(threadLocal.get());
			threadLocal.set(456);
			System.out.println(threadLocal.get());
		}
	}
	
	
	
}
