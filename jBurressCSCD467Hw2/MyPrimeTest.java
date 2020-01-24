
public class MyPrimeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		if (args.length < 3) {
			System.out.println("Usage: MyPrimeTest numThread low high \n");
			return;
		}
		int nthreads = Integer.parseInt(args[0]);
		int low = Integer.parseInt(args[1]);
		int high = Integer.parseInt(args[2]);
		Counter c = new Counter();
		
		//test cost of serial code
		long start = System.currentTimeMillis();
		int numPrimeSerial = SerialPrime.numSerailPrimes(low, high);
		long end = System.currentTimeMillis();
		long timeCostSer = end - start;
		System.out.println("Time cost of serial code: " + timeCostSer + " ms.");
		
		//test of concurrent code
		// **************************************
        // Logic provided in Lecture on 7/2
		Thread[] threads = new Thread[nthreads];
		int newHigh = high/nthreads - 1;
		start = System.currentTimeMillis();
		for(int i = 0; i < nthreads; i++)
		{
			threads[i] = new Thread(new ThreadPrime(low, newHigh,c));
			threads[i].start();
			low = newHigh + 1;
			newHigh += high/nthreads + 1;
		}
		for(int x = 0; x < nthreads; x++)
		{
			threads[x].join();
		}
		end = System.currentTimeMillis();
		long timeCostCon = end - start;
		
		// **************************************
		System.out.println("Time cost of parallel code: " + timeCostCon + " ms.");
		System.out.format("The speedup ration is by using concurrent programming: %5.2f. %n", (double)timeCostSer / timeCostCon);
		
		System.out.println("Number prime found by serial code is: " + numPrimeSerial);
		System.out.println("Number prime found by parallel code is " + c.total());
	}
		

}