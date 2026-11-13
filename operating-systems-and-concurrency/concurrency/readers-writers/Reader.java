public class Reader extends Thread {

	DataAccessPolicyManager lockManager;

	public Reader (DataAccessPolicyManager lockManager) {
		this.lockManager = lockManager;
	}

	public void run() {
		while(true){
			System.out.println(getName() + " is sleeping");
			pause(5000);

			System.out.println(getName() + " wants to read");
			lockManager.acquireReadLock();

			// several readers can be in here at once, but never with a writer
			System.out.println(getName() + " is reading");
			pause(3000);
			System.out.println(getName() + " has finished reading");

			lockManager.releaseReadLock();
		}
	}

	// sleeps for a random time up to max milliseconds
	private void pause(int max){
		try{
			sleep((int)(Math.random()*max));
		}catch(InterruptedException e){}
	}
}
