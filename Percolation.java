public class Percolation
{
	
	private static boolean[] grid;	
	private static int N; 
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
			if( grid[ N * (i) + j ] == true ) uf.union(N * (i - 1) + j , N * (i) + j);//down 
			if( grid[ N * (i - 1) + j + 1] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if( i == 1 && j == N)
		{
			if( grid[ N * (i - 1) + j - 1] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			if( grid[ N * (i) + j ] == true ) uf.union(N * (i - 1) + j , N * (i) + j);//down 
		}else if( i == N && j == 1)
		{
			if( grid[ N * (i - 2) + j ] == true ) uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			if( grid[ N * (i - 1) + j + 1 ] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if( i == N && j == N)
		{
			if( grid[ N * (i - 2) + j ] == true ) uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			if( grid[ N * (i - 1) + j - 1] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
		}else if( i == 1  )
		{
			if( grid[ N * (i) + j ] == true ) uf.union(N * (i - 1) + j , N * (i) + j);//down 
			if( grid[ N * (i - 1) + j - 1] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			if( grid[ N * (i - 1) + j + 1 ] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}
		else  if ( i == N )
		{
			if( grid[ N * (i - 2) + j ] == true ) uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			if( grid[ N * (i - 1) + j - 1] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			if( grid[ N * (i - 1) + j + 1 ] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if ( j == 1)
		{
			if( grid[ N * (i) + j ] == true )  uf.union(N * (i - 1) + j , N * (i) + j);//down 
			 if( grid[ N * (i - 2) + j ] == true ) uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			if( grid[ N * (i - 1) + j + 1 ] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else if( j == N)
		{
			if( grid[ N * (i - 2) + j ] == true ) uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			if( grid[ N * (i - 1) + j - 1] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			if( grid[ N * (i - 1) + j + 1 ] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
		}else 
		{ 
			if( grid[ N * (i - 2) + j ] == true ) uf.union(N * (i - 1) + j , N * (i - 2) + j);//up
			if( grid[ N * (i - 1) + j - 1] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j - 1);//left
			if( grid[ N * (i - 1) + j + 1 ] == true ) uf.union(N * (i - 1) + j , N * (i - 1) + j + 1);//right
			if( grid[ N * (i) + j ] == true )  uf.union(N * (i - 1) + j , N * (i) + j);//down 
		}
	}
	
	public static boolean isOpen(int i, int j)
	{
		return grid[N * (i - 1) + j];
		
	}
	
	public void grid_display()
	{
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				StdOut.print( grid[N * (i - 1) + j] + " " );
			}
			StdOut.println();
		}
	}
			

	public boolean percolates()//does the system percolate?
	{
		//StdOut.print(uf.connected(0,N*N + 1) + " ");
		//StdOut.println();
		return 	uf.connected(0,N*N + 1);
	}
	
	


	public static void main(String[] args)
	{
		/*int count = 0;		
		Percolation pc = new Percolation(200);
		while( !pc.percolates())
		{
			int row = StdRandom.uniform(1,N+1);
			int column = StdRandom.uniform(1,N+1);
			if( isOpen(row,column) ) continue;
			else
			{
				StdOut.print( "row :" + row +  "column :" + column + " ");	
				StdOut.println();		
				pc.open(row,column);
				count++;
			}		
		}		
		pc.grid_display();
		StdOut.println();
		StdOut.print(count + " ");*/
	}		
}	
