package de.maanex.tb3;


public class ThreadingLib {

	private ThreadingLib() {
	}

	public static void newThread(Runnable r) {
		new Thread(r).start();
	}

	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void threadDelayed(int ms, Runnable r) {
		newThread(() -> {
			sleep(ms);
			r.run();
		});
	}

}
