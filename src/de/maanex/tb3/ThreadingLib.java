package de.maanex.tb3;


public class ThreadingLib {

	private ThreadingLib() {
	}

	public static void newThread(Runnable r) {
		new Thread(r).start();
	}

}
