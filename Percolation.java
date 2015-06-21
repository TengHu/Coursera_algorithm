public class Percolation
{
	
	private boolean[] grid;	
	private int N; 
	private UF uf; 	

	public Percolation(int N)// create N-by-N grid, with all sites blocked
	{
		this.N = N;
				
		this.grid = new boolean[N*N + 2];
		
		this.grid[0] = true;
		this.grid[N*N + 1] = true;
		for(int i = 1; i<= N*N; i++)
		{
			grid[i] = false;
		}

		this.uf = new UF(N*N+2);		
		
		for(int i = 1; i <= N; i++)
		{
   			this.uf.union(0,i);
		}
		
		for(int i = 1; i <= N; i++)
		{
   			this.uf.union(N*N+1,N*(N-1)+i);
		}
	}	
	
	public void open(int i,int j)
	{
		grid[N * (i - 1) + j] = true;
		
		if( i == 1 && j == 1)
		{
			uf.union(N * (i - 1) + j , N * (i) + j);//down 
			uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if( i == 1 && j == N)
		{
			uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			uf.union(N * (i - 1) + j , N * (i) + j);//down 
		}else if( i == N && j == 1)
		{
			uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if( i == N && j == N)
		{
			uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
		}else if( i == 1  )
		{
			uf.union(N * (i - 1) + j , N * (i) + j);//down 
			uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}
		else  if ( i == N )
		{
			uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if ( j == 1)
		{
			uf.union(N * (i - 1) + j , N * (i) + j);//down 
			uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if( j == N)
		{
			uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else 
		{ StdOut.print(" connecting error"); StdOut.println();}
	}
	
	public boolean isOpen(int i, int j)
	{
		return grid[N * (i - 1) + j];
		
	}
	
	

	public void percolates()//does the system percolate?
	{
		StdOut.print(uf.connected(0,N*N + 1) + " ");
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		Percolation pc = new Percolation(4);
		pc.open(1,1);
		pc.open(2,1);
		pc.open(3,1);
		pc.percolates();
		StdOut.print(pc.isOpen(3,1));

	}		
	

}	
