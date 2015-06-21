public class PercolationStats
{
	private Percolation pc;
	private double[] count;
	private static int T;
	private static int N;
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	
	public PercolationStats(int N,int T)
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
		this.mean = sum/T;
		return mean;
	}

	

	public double stddev()
	{
		double sum = 0;	
		for(int i = T-1; i>=0 ; i--)
		{
			sum = sum + (count[i] - mean) * (count[i] - mean);
		}
		this.stddev = Math.sqrt(sum/(T-1));
		return stddev;
	}	
	
	public double confidenceLo()
	{
		this.confidenceLo = mean - (1.96 * stddev)/Math.sqrt(T);
		return confidenceLo;
	}
		
	public double confidenceHi()
	{
		this.confidenceHi = mean + (1.96 * stddev)/Math.sqrt(T);
		return confidenceHi;
	}
		
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		
		PercolationStats pc = new PercolationStats(n,t);	
		
		StdOut.print(" mean = " + pc.mean());
		StdOut.println();
		StdOut.print(" stddev = " + pc.stddev());
		StdOut.println();
		StdOut.print(" 95% confidence interval = " + pc.confidenceLo() + " " + pc.confidenceHi());
		StdOut.println();
	} 
}
