public class AlternateThread implements Runnable
{
	//private int currentThread;
	private int threadID;
	private Monitor mon;
	private int nThreads;
	
	public AlternateThread(int threadID, Monitor mon, int nThreads)
	{
		this.mon = mon;
		this.threadID = threadID;
		this.nThreads = nThreads;
		//currentThread = 0;
	}
	public void run() 
	{
		for(int i = 0; i < 10; i++)
		{
			mon.waitForTurn(threadID);
			System.out.println("Message from: " + Thread.currentThread().getName());
			mon.increment(nThreads);
		}
	}//end of run
	
	public static void main(String args[])
	{
		int result = Integer.parseInt(args[0]);
		Monitor mon = new Monitor();
		for(int i = 0; i < result; i++)
		{
			Thread t = new Thread(new AlternateThread(i, mon, result));
			t.setName("" + i);
			t.start();
		}
		
	}//end of main
	
}//end of AlternateThread

class Monitor
{
	int selectTurn = 0;
	
	synchronized  int selectTurn(int selectTurn)
	{
		return selectTurn;
	}
	
	synchronized void waitForTurn(int ThreadID)
	{
		while(selectTurn != ThreadID)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	synchronized void increment(int nThreads)
	{
		selectTurn++;
		if(selectTurn > nThreads - 1)
			selectTurn = 0;
		//System.out.println(whosTurn);
		notifyAll();
	}
}
