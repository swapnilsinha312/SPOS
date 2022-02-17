import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

class pair
{
    int a;
    int b;
    pair(int A,int B)
    {
        a=A;
        b=B;
    }

    public String toString()
    {
        return a+" "+b;
    }

    public int compare(pair a)
    {
        if(this.a==a.a) return 0;
        else return 1;
    }

    public boolean equals(Object ob) 
    {
        if (this == ob) return true;
        if (ob == null || getClass() != ob.getClass()) return false;
        pair temp = (pair)ob;
        return this.a==temp.a;
    }
}

public class LRU 
{
    PriorityQueue<pair> framesMap;
    ArrayDeque<Integer> frames;
    ArrayList<Integer> pages;
    int hit;
    int fault; 
    int fInd;
    int fSize;

    LRU(int f,int p[])
    {
        hit=0;
        fault=0; 
        fInd=0;
        fSize=f; 
        frames= new ArrayDeque<>();
        pages= new ArrayList<>();
        for(int i:p) pages.add(i);
        // for(int i=0;i<fSize;i++) frames.add(-1);
        // System.out.println("Hits\tFaults\tFrames(1-"+fSize+")");
    }
    
    public void processing()
    {
        for(int i=0;i<pages.size();i++)
        {
            int currPage=pages.get(i);
            if(frames.contains(currPage))
            {
                frames.remove(currPage);
                frames.addLast(currPage);
                hit++;
            }
            else
            {
                if(frames.size()==fSize) frames.removeFirst();
                frames.addLast(currPage);
                fault++;
            }            
            // disp();            
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
        int f[]= { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
        int pg=4;
        System.out.println("LRU");
        LRU abc= new LRU(pg,f);
        abc.processing();
        abc.finalDisp();
    }

    public static void main(String args[])
    {
        int f[]= {1,3,0,3,5,6,3};
        int pg=3;
        // int f[]= { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
        // int pg=4;
        LRU abc= new LRU(pg,f);
        abc.processing();
        abc.finalDisp();
    }


}
