public class ReadersWritersSimulation {
	public static void main (String args[]) {
		int numReaders = 3;
		int numWriters = 2;
		int policy = 1;

		// usage: java ReadersWritersSimulation [readers] [writers] [policy]
		try{
			if(args.length > 0) numReaders = Integer.parseInt(args[0]);
			if(args.length > 1) numWriters = Integer.parseInt(args[1]);
			if(args.length > 2) policy = Integer.parseInt(args[2]);
		}catch(NumberFormatException e){
			System.out.println("Usage: java ReadersWritersSimulation [readers] [writers] [policy 1 or 2]");
			return;
		}
		if(numReaders < 0 || numWriters < 0 || (policy != 1 && policy != 2)){
			System.out.println("Usage: java ReadersWritersSimulation [readers] [writers] [policy 1 or 2]");
			return;
		}

		// every thread gets the same manager object, that is what they synchronise on
		DataAccessPolicyManager lockManager;
		if(policy == 1){
			lockManager = new DataAccessPolicyManager();
			System.out.println("Policy 1 (readers first): " + numReaders + " readers, " + numWriters + " writers");
		}else{
			lockManager = new DataAccessPolicyManager2();
			System.out.println("Policy 2 (writers first): " + numReaders + " readers, " + numWriters + " writers");
		}

		for(int i = 1; i <= numReaders; i++){
			Reader r = new Reader(lockManager);
			r.setName("Reader " + i);   // the name is what shows up in the output
			r.start();
		}
		for(int i = 1; i <= numWriters; i++){
			Writer w = new Writer(lockManager);
			w.setName("Writer " + i);
			w.start();
		}
	}
}
