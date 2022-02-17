
//Assignment 1 FCFS
import java.util.*;

class FCFS1Class 
{
    ArrayList<Process> list;
    ArrayList<Gantt> listOfGantt;
    float TATAverage;  
    float WTAverage;
    FCFS1Class()
    {
        listOfGantt= new ArrayList<Gantt>();    
        list= new ArrayList<Process>();
        TATAverage=0;  
        WTAverage=0;
    }
    
    public void addGanttToList(String name,int start, int end)
    {
        Gantt a = new Gantt(name, start, end);
        listOfGantt.add(a);
    }
    
    class FSFCCompare implements Comparator<Process>
    {
        public int compare(Process a, Process b )
    {
        return a.arrTime - b.arrTime;
    }  
    }

    
    public void addProcesses(String a[], int at[], int bt[])
    {
        for (int i=0;i<a.length;i++)
        list.add(new Process(a[i],at[i],bt[i]));
        Collections.sort(list,new FSFCCompare());
    }
    
        public void setUp(String pId[], int at[], int bt[])
    {
        
        for(int i=0;i<pId.length;i++)
        {
            list.get(i).pId= pId[i];
            list.get(i).arrTime= at[i];
            list.get(i).burTime= bt[i];
        }
    }
    
    
    public void getCT(String pId[])
    {
        int timeStart=1; //change if 0 start time
        
         
        for(int i=0;i<pId.length;i++)
        {
            if(i==0)
            list.get(0).compTime=list.get(0).arrTime+list.get(0).burTime;
            else 
            list.get(i).compTime= list.get(i-1).compTime+list.get(i).burTime;
            addGanttToList(list.get(i).pId, timeStart, list.get(i).compTime);
            timeStart+=list.get(i).compTime;
        }
    }
    
        public void gettat(String pId[])
    {
        float avg=0;
        for(int i=0;i<pId.length;i++)
        {
            list.get(i).TATime= list.get(i).compTime-list.get(i).arrTime;
            avg+=list.get(i).TATime;
        }
        avg=avg/pId.length;
        TATAverage= avg;
        
    }
    
        public void getwt(String pId[])
    {
        float av=0;
        for(int i=1;i<pId.length;i++)
        {
            list.get(i).waitTime= list.get(i).TATime-list.get(i).burTime;
            av+=list.get(i).waitTime;
        }
        WTAverage= av/pId.length;
    }
        public void printGantt()
    {
        System.out.println();
        for (Gantt a : listOfGantt)
        System.out.print("|"+a.start+" "+a.name+" "+a.end+"|");
        System.out.println("\n");
    }
    
    public void print ()
    {
        System.out.println("Process   \tAT\tBT\tCT\tTAT\tWT");
        for( int i=0;i< list.size();i++)
        {
            System.out.println(list.get(i).pId+"\t\t"+list.get(i).arrTime+"\t"
            +list.get(i).burTime+"\t"+list.get(i).compTime+"\t"+list.get(i).TATime
            +"\t"+list.get(i).waitTime);
        }
        
        System.out.println();
        System.out.println("Average Turnaround Time= "+ TATAverage);
        System.out.println("Average Waiting Time= "+ WTAverage);
    }
    
  
    public static void main(String args[])
    {
        String []pId = {"P2","P1","P3","P4"};
        int at[]= {2,1,3,4};
        int bt[]= {4,2,3,1};
        System.out.println("First Come First Serve");
        
        FCFS1Class abc= new FCFS1Class();
        abc.addProcesses(pId,at,bt);
        //abc.setUp(pId,at,bt);
        abc.getCT(pId);
        abc.gettat(pId);
        abc.getwt(pId);
        
        abc.printGantt();
        abc.print();
        
        
    }
}

