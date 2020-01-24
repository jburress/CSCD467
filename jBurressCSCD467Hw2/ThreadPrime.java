
public class ThreadPrime extends Thread {
	private int low;
	private int high;
	private int numFound = 0;
	private Counter c;
	
	// each thread only  takes care of one subrange (low, high)
	public ThreadPrime (int lowLocal, int highLocal, Counter ct) {
		this.low = lowLocal;
		this.high = highLocal;
		c = ct;
	}
	
	public boolean checkIfPrime(int num) // this method was copied from the isPrime(int) method in SerialPrime
	{
		if(num%2 == 0)
			return false;
		for(int i = 3; i*i <= num; i+=2)
		{
			if(num%i == 0)
				return false;
		}
		return true;
	}

	public void run()
	{
		for(int i = low; i <= high; i++)
		{
			if(checkIfPrime(i))
				numFound++;
			
		}
		c.increment(numFound);
		
	}
		
}
