/*
third fix: asymmetric pick up. even numbered philosophers take their right
chopstick first and odd numbered ones take their left first. deadlock in the
original needs a full circle of five, each holding their right chopstick and
waiting for the neighbour's. here neighbours 1 and 2 both go for chopstick 2
first, and 3 and 4 both go for chopstick 4 first, so whoever loses is waiting
while holding nothing and the circle can't close. no extra semaphore is needed
eg. first picks: 0 -> stick 0, 1 -> stick 2, 2 -> stick 2, 3 -> stick 4, 4 -> stick 4
*/
class DiningPhilosophers3 {
	public static void main(String args[]) {
		Semaphore chopSticks[];
		Philosopher3 workerThread[];

		// Create an array of five Semaphore Object Reference Handles
		chopSticks = new Semaphore[5];

		// Create five Binary Semaphore Objects and assign to the array
		for (int i=0; i<5; i++)
			chopSticks[i] = new Semaphore(1); // Semaphore initial value=1

		// Create an array of five Philosopher Thread Object Reference Handles
		workerThread = new Philosopher3[5];

		// Create and initiate five Philosopher Thread Objects
		for (int i=0; i<5; i++) {
			workerThread[i] = new Philosopher3(i, chopSticks);
			workerThread[i].start();
		}
	}
}

// The Philosopher class implements a run() method defining the behaviour of a Philosopher thread

class Philosopher3 extends Thread {
	private int myName;
	private Semaphore chopSticks[];
	//
	// This is the constructor function which is executed when a Philosopher
	// thread is first created
	//
	public Philosopher3(int myName, Semaphore chopSticks[]) {
		this.myName = myName; // 'this' distinguishes the local private variable from the parameter
		this.chopSticks = chopSticks;
	}

	//
	// This is what each philosopher thread executes
	//
	public void run() {
		int right = myName;
		int left = (myName+1)%5;

		while (true) {
			System.out.println("Philosopher "+myName+" thinking.");
			try {
				sleep ((int)(Math.random()*20000));
			} catch (InterruptedException e) {}

			System.out.println("Philosopher "+myName+" hungry.");
			if (myName % 2 == 0) {
				chopSticks[right].acquire(); // even: right then left
				chopSticks[left].acquire();
			} else {
				chopSticks[left].acquire(); // odd: left then right
				chopSticks[right].acquire();
			}

			System.out.println("Philosopher "+myName+" eating.");
			try { // Simulate eating activity for a random time
				sleep ((int)(Math.random()*10000));
			} catch (InterruptedException e) {}

			chopSticks[right].release(); // Release right
			chopSticks[left].release(); // Release left
		}
	}
}
