public class Writer extends Thread {

	DataAccessPolicyManager lockManager;

	public Writer (DataAccessPolicyManager lockManager) {
		this.lockManager = lockManager;
	}

	public void run() {
		while(true){
			System.out.println(getName() + " is sleeping");
			pause(5000);

			System.out.println(getName() + " wants to write");
			lockManager.acquireWriteLock();

			// a writer is always in here on its own
			System.out.println(getName() + " is writing");
			pause(3000);
			System.out.println(getName() + " has finished writing");

			lockManager.releaseWriteLock();
		}
	}

	// sleeps for a random time up to max milliseconds
	private void pause(int max){
		try{
			sleep((int)(Math.random()*max));
		}catch(InterruptedException e){}
	}
}
