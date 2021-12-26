package in.home.app.utils;

import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {

	private static int count = 0;
	private static String nameString = "PoolWorker-";

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, nameString + ++count);
		return thread;
	}
}
