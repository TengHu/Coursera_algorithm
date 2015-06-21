public class PercolationsStats
{
	private Percolation pc;
	private double[] count;
	private static int T;
	private static int N;
	
		

	public PercolationsStats(int N,int T)
	{
		this.T = T;
		this.N = N;
		count = new  double[T];
		int row;
		int column;		
		double MC_times;		

	
		for(int i = 0; i<T; i++)
		{	
			pc = new Percolation(N);
			MC_times = 0;			
			while( !pc.percolates() )
			{
				     row = StdRandom.uniform(1,N+1);
				     column = StdRandom.uniform(1,N+1);
				if( pc.isOpen(row,column) ) continue;
				else
				{
					//StdOut.print( "row :" + row +  "column :" + column + " ");	
					//StdOut.println();		
					pc.open(row,column);
					MC_times++;
				}		
				
				
				count[i] = MC_times/(N*N);
			}
		}
	}

	public double mean()
	{
		double sum = 0;	
		for(int i = T-1; i>=0 ; i--)
		{
			sum += count[i];
		}
		return sum/T;
	}



	//public double stddev();
	//public double confidenceLo();
	//public double confidenceHi();
		
	public static void main(String[] args)
	{
		//int N = StdIn.readInt();
		//int T = StdIn.readInt();
				
		PercolationsStats pc = new PercolationsStats(200,100);	
		
						
		
			StdOut.print(pc.mean() + " ");
				
		
		
	} 
}
