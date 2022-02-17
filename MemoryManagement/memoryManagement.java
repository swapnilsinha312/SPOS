
import java.util.ArrayList; 

public class memoryManagement
{
    int blockSize;
    int processSize;
    ArrayList<Integer> blocks;
    ArrayList<Integer> process;
    ArrayList<Integer> allocation;
    
    memoryManagement(int a,int b,int aa[],int bb[])
    {
        blockSize=a;
        processSize=b;
        blocks= new ArrayList<>();
        process=new ArrayList<>();
        allocation= new ArrayList<>();
        for(int i:aa) {blocks.add(i); allocation.add(0);}
        for(int i:bb) process.add(i);
    } 
    
    public void bestFit()
        { 
            for (int i=0; i<processSize; i++)
            {
                int index = -1;
                for (int j=0; j<blockSize; j++)
                {
                    if (blocks.get(j) >= process.get(i))
                    {
                        if (index == -1)
                            index = j;
                        else if (blocks.get(index)>blocks.get(j))
                            index = j;
                    }
                }
                if (index != -1)
                {
                    allocation.set(i,index);
                    blocks.set(index,blocks.get(index)-process.get(i));
                }
            } 
        }


	public void firstFit()
    { 
        for (int i=0;i<processSize;i++)
        {
            for (int j=0;j<blockSize;j++)
            {
                if (blocks.get(j)>=process.get(i))
                {
                    allocation.set(i,j);
                    blocks.set(j,blocks.get(j)-process.get(i)); 
                    break;
                }
            }
        } 
    }
    
    
    public void worstFit()
    { 
        for (int i=0; i<processSize; i++)
        {
            int index = -1;
            for (int j=0; j<blockSize; j++)
            {
                if (blocks.get(j)>=process.get(i))
                {
                    if (index == -1)
                        index = j;
                    else if (blocks.get(index)<blocks.get(j))
                        index = j;
                }
            }
            if (index != -1)
            {
                allocation.set(i,index);
                blocks.set(index,blocks.get(index)-process.get(i));
            }
        } 
    }


    public void nextFit() 
    { 
        int j=0;
        for (int i=0; i<processSize;i++) 
        {
            int count=0;
            while (j<blockSize) 
            {
                count++;
                if (blocks.get(j)>=process.get(i)) 
                {
                    allocation.set(i,j);
                    blocks.set(j,blocks.get(j)-process.get(i));
                    break;
                }
                if(count==blockSize+1) break;
                j = (j + 1) % blockSize;
            }
        } 
    }
    
    
    void display()
    {
        
        System.out.println("Process No\tProcess Size\tBlock no");
        for (int i = 0; i < processSize; i++)
        {
            System.out.print("\t" + (i+1) + "\t" + process.get(i) + "\t");
            if (allocation.get(i) != -1)
                System.out.print(allocation.get(i) + 1);
            else
                System.out.print("-NA-");
            System.out.println();
        }
        System.out.println();
    }
	
    
    public static void main(String[] args)
	{
        int m=5; 
        int n=4;
        int blocks[] = {100, 500, 200, 300, 600};
        int process[] = {212, 417, 112, 426};

        memoryManagement abc= new memoryManagement(m,n,blocks,process);
        abc.bestFit();
        abc.display();

        abc= new memoryManagement(m,n,blocks,process);
        abc.firstFit();
        abc.display();

        abc= new memoryManagement(m,n,blocks,process);
        abc.worstFit();
        abc.display(); 
	}
}