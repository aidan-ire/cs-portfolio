/*
second fix: picking up the two chopsticks is made a critical section. a shared
binary semaphore called pickUp has to be held while a philosopher takes both
chopsticks, so nobody can be caught holding one chopstick while a neighbour
takes the other. the philosopher holding pickUp can still be made to wait for a
chopstick, but only by someone who is already eating, and eating philosophers
put their chopsticks back without needing pickUp, so the wait always ends.
the cost is that one philosopher waiting inside the critical section holds up
everybody else who is hungry, even ones whose chopsticks are free
*/
class DiningPhilosophers2 {
	public static void main(String args[]) {
		Semaphore chopSticks[];
		Semaphore pickUp;
		Philosopher2 workerThread[];

		// Create an array of five Semaphore Object Reference Handles
		chopSticks = new Semaphore[5];

		// Create five Binary Semaphore Objects and assign to the array
		for (int i=0; i<5; i++)
			chopSticks[i] = new Semaphore(1); // Semaphore initial value=1

		// binary semaphore guarding the pick up of both chopsticks
		pickUp = new Semaphore(1);

		// Create an array of five Philosopher Thread Object Reference Handles
		workerThread = new Philosopher2[5];

		// Create and initiate five Philosopher Thread Objects
		for (int i=0; i<5; i++) {
			workerThread[i] = new Philosopher2(i, chopSticks, pickUp);
			workerThread[i].start();
		}
	}
}

// The Philosopher class implements a run() method defining the behaviour of a Philosopher thread

class Philosopher2 extends Thread {
	private int myName;
	private Semaphore chopSticks[];
	private Semaphore pickUp;
	//
	// This is the constructor function which is executed when a Philosopher
	// thread is first created
	//
	public Philosopher2(int myName, Semaphore chopSticks[], Semaphore pickUp) {
		this.myName = myName; // 'this' distinguishes the local private variable from the parameter
		this.chopSticks = chopSticks;
		this.pickUp = pickUp;
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
			pickUp.acquire(); // only one philosopher picks up at a time
			chopSticks[myName].acquire(); // Acquire right
			chopSticks[(myName+1)%5].acquire(); // Acquire left
			pickUp.release(); // has both, let the next one try

			System.out.println("Philosopher "+myName+" eating.");
			try { // Simulate eating activity for a random time
				sleep ((int)(Math.random()*10000));
			} catch (InterruptedException e) {}

			chopSticks[myName].release(); // Release right
			chopSticks[(myName+1)%5].release(); // Release left
		}
	}
}
