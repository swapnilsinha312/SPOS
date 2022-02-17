import java.util.ArrayList;
import java.util.LinkedList; 

public class Optimal
{
    ArrayList<Integer> pages;
    LinkedList<Integer> frames;
    int fSize;
    int fIndex;
    int hit;
    int fault;

    Optimal(int f, int p[])
    {
        fSize=f;
        hit=0;
        fault=0;
        pages= new ArrayList<>();
        frames=new LinkedList<>();
        for(int i:p) pages.add(i);
    }

    public int linearSearch(int el,int start)
    {
        // int ret=-1;
        for(start=start+1;start<pages.size();start++)
        {
            if(pages.get(start)==el) return start;
        }
        return -1;
    }

    public void processing()
    {
        for(int i=0;i<pages.size();i++)
        {
            int curr=pages.get(i);
            if(frames.contains(curr))
            {
                hit++;
            }
            else
            {
                fault++;
                if(frames.size()<fSize) 
                {
                    frames.add(curr);
                    continue;
                }

                int elem=-1; 
                for(int ii=0;ii<frames.size();ii++)
                { 
                    int temp= linearSearch(frames.get(ii), i);
                    int max= -1;
                    if(temp>max) 
                    {
                        max=temp;
                        elem=frames.get(ii);
                    }
                }
                // System.out.println(frames+" "+elem);
                if(elem!=-1)
                {
                    int temp=frames.indexOf(elem);
                    frames.set(temp,curr); 
                } 
            }
        }
    }

    public void disp()
    {
        String ret=hit+"\t"+fault+"\t";
        for(int i:frames) ret+=i+"\t"; 
        System.out.println(ret);
        // System.out.println(" _\n|_|");
    }

    public void finalDisp()
    {
        System.out.println("Total Hits= "+hit+"\nTotal faults= " +fault);
        System.out.println();
    }

    public static void run()
    {
        int f[]= {1,3,0,3,5,6,3};
        int pg=3;
        Optimal abc= new Optimal(pg,f);
        System.out.println("Optimal");
        abc.processing();
        abc.finalDisp();
    }

    public static void main(String args[])
    {
        int f[]= {1,3,0,3,5,6,3};
        int pg=3;
        Optimal abc= new Optimal(pg,f);
        System.out.println("Optimal");
        abc.processing();
        abc.finalDisp();
    }


}