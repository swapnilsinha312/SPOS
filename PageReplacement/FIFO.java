import java.util.ArrayList;

public class FIFO
{
    ArrayList<Integer> frames;
    ArrayList<Integer> pages;
    int hit;
    int fault; 
    int fInd;
    int fSize;

    FIFO(int f, int []p)
    {
        hit=0;
        fault=0; 
        fInd=0;
        fSize=f;
        frames= new ArrayList<>();
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
            hit++;
            else
            {
                fault++;
                if(frames.size()==fSize) frames.set(fInd++,currPage);
                else frames.add(currPage);
                fInd%=fSize;
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
        int f[]= {1,3,0,3,5,6,3};
        int pg=3;
        System.out.println("FIFO");
        FIFO abc= new FIFO(pg,f);
        abc.processing();
        abc.finalDisp();
    }

    public static void main(String args[])
    {
        int f[]= {1,3,0,3,5,6,3};
        int pg=3;
        FIFO abc= new FIFO(pg,f);
        abc.processing();
        abc.finalDisp();
    }



}