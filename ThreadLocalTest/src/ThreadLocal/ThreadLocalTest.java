package ThreadLocal;

public class ThreadLocalTest {
	static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
	static ThreadLocal<User> threadlocaluser = new ThreadLocal<>();
	
	public static void main(String[] args) {
		
		threadLocal.set(100);
		System.out.println(threadLocal.get());
		
		User user = new User();
		user.setAge(23);
		user.setName("jack");
		threadlocaluser.set(user);
		System.out.println(threadlocaluser.get());
	}
}
