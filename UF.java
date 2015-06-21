public class UF
{
	private int[] id;	
	private int[] sz;

	public UF(int N)
	{
		id =  new int[N];
		sz = new int [N];
	
		for(int i = 0; i<N; i++)
		{
			id[i] = i;
		}

		
		for(int i = 0; i<N; i++)
		{
			sz[i] = 1;
		}

		
	}
		
	
	public void union(int i,int j)
	{
		i = find(i);
		j = find(j);
		
		if( i == j) return;
		else if( sz[i] >= sz[j] ) { id[j] = i; sz[i] += sz[j];}
		else {id[i] = j;sz[j] += sz[i];}
	}
		

	public int find(int i)//find the root
	{
		if( i != id[i] ) 
		{
			i = id[i];
			return find(i);
		}
		else return i;
	}

	public void display()
	{
		int a = id.length - 1;
		for(int i = 0; i<=a ; i++)
		{
			StdOut.print(id[i] + " ");
		}

		StdOut.println();
	}



	public static void main(String[] args)
	{
		UF uf = new UF(10);
		uf.union(1,0);
		uf.union(9,6);
		uf.union(7,2);
		uf.union(4,6);
		uf.union(5,4);
		uf.union(1,2);
		uf.union(6,8);
		uf.union(9,2);
		uf.union(4,3);
								
		uf.display();
	}		 
}
		
