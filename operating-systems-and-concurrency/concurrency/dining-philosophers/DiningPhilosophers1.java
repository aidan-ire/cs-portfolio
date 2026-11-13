/*
first fix: only four of the five philosophers are allowed to be at the table
(hungry or eating) at the same time. deadlock needs all five to be holding one
chopstick each, and with at most four at the table there are five chopsticks
between four people, so at least one of them can always get a second one, eat
and put both back. the limit is a counting semaphore called table that starts at 4
*/
class DiningPhilosophers1 {
	public static void main(String args[]) {
		Semaphore chopSticks[];
		Semaphore table;
		Philosopher1 workerThread[];

		// Create an array of five Semaphore Object Reference Handles
		chopSticks = new Semaphore[5];

		// Create five Binary Semaphore Objects and assign to the array
		for (int i=0; i<5; i++)
			chopSticks[i] = new Semaphore(1); // Semaphore initial value=1

		// counting semaphore, one seat fewer than there are philosophers
		table = new Semaphore(4);

		// Create an array of five Philosopher Thread Object Reference Handles
		workerThread = new Philosopher1[5];

		// Create and initiate five Philosopher Thread Objects
		for (int i=0; i<5; i++) {
			workerThread[i] = new Philosopher1(i, chopSticks, table);
			workerThread[i].start();
		}
	}
}

// The Philosopher class implements a run() method defining the behaviour of a Philosopher thread

class Philosopher1 extends Thread {
	private int myName;
	private Semaphore chopSticks[];
	private Semaphore table;
	//
	// This is the constructor function which is executed when a Philosopher
	// thread is first created
	//
	public Philosopher1(int myName, Semaphore chopSticks[], Semaphore table) {
		this.myName = myName; // 'this' distinguishes the local private variable from the parameter
		this.chopSticks = chopSticks;
		this.table = table;
	}

	//
	// This is what each philosopher thread executes
	//
	public void run() {
		while (true) {
			System.out.println("Philosopher "+myName+" thinking.");
			try {
				sleep ((int)(Math.random()*20000));
			} catch (InterruptedException e) {}

			System.out.println("Philosopher "+myName+" hungry.");
			table.acquire(); // sit down, blocks if four are already seated
			chopSticks[myName].acquire(); // Acquire right
			chopSticks[(myName+1)%5].acquire(); // Acquire left

			System.out.println("Philosopher "+myName+" eating.");
			try { // Simulate eating activity for a random time
				sleep ((int)(Math.random()*10000));
			} catch (InterruptedException e) {}

			chopSticks[myName].release(); // Release right
			chopSticks[(myName+1)%5].release(); // Release left
			table.release(); // leave the table so someone else can sit
		}
	}
}
