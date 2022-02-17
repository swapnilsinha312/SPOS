import  java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

class compAT implements Comparator<Process>
{
        public int compare(Process a , Process b)
        {
            if(a.arrTime>b.arrTime)
                return 1;
            else  
                return -1;
        }
}

class sortByAsc implements Comparator<Process>
{
        public int compare (Process a , Process b)
        {
            if(a.priority>b.priority) return 1;
            else if(a.priority==b.priority) 
            return (a.addIndex-b.addIndex);
            else 
            return -1;
        }
        
        public String toString()
        {
            return "Ascending priority i.e 1>2";
        }
}

class sortByDec implements Comparator<Process>
{
        public int compare (Process b , Process a)
        {
            if(a.priority<b.priority) return -1;
            else if(a.priority==b.priority) return (a.priority-b.priority);
            else 
            return 1;
        }
        
        public String toString()
        {
            return "Descending priority i.e 2>1";
        }
}
public class Priority 
{
    ArrayList<Process> listOfProcess;
    ArrayList<Gantt> listOfGantt;
    PriorityQueue <Process> pqProcess;
    Process extra;
    Double avgWT;
    Double avgTAT; 
    
    
    public Priority()
    {
        listOfProcess= new ArrayList<Process>();
        listOfGantt= new ArrayList<Gantt>();
        pqProcess= new PriorityQueue<Process>(new sortByAsc());
        extra = null;
        avgTAT=0.0;
        avgWT=0.0;
    }
    
    public void addGanttToList(String name,int start, int end)
    {
        Gantt a = new Gantt(name, start, end);
        listOfGantt.add(a);
    }
    
    
        
    public void initProcess(String pId[], int at[], int bt[], int pri[])
    {
        for (int i=0;i<pId.length;i++)
        {
            Process a = new Process(pId[i], at[i], bt[i],pri[i]);
            addProcessList(a);
        }
    }
    
    public void addProcessList(Process a)
    {
        a.addIndex=listOfProcess.size();
        listOfProcess.add(a);
    }
    
    public void addProcessQueue(Process a)
    {
        pqProcess.add(a);
    }
    
    public int totBT()
    {
        int time=0;
        for(Process a:listOfProcess)
        time+=a.burTime;
        return time;
    }
    
    public int processOne(int presTime)
    {
        int time=0;
        Process a = pqProcess.poll();
        //System.out.print(a.addIndex+" "+a.burTime+" "+presTime);
        if(a.burRem>1)
        { //In essese the quantum time here is 1
            time=1;
            a.burRem-=1; 
            addProcessQueue(a);
        }
        else
        {
            time= a.burRem;
            a.burRem=0;
        }
        a.waitTime+= presTime-a.compTime;
        a.compTime=presTime+time;
        addGanttToList(a.pId,presTime,presTime+time);
        return time;
    }
    
    
    public void Processing()
    {
        Collections.sort(listOfProcess, new compAT());
        //System.out.println(listOfProcess.get(0).pId+"<<");
        int endTime= totBT();
        int timePassed=0;
        int addNo=0;
        for(int i=0;i<endTime;i+=timePassed) // if i=0 then < end time check
        {
            
            while(addNo<listOfProcess.size())
            {   Process add= listOfProcess.get(addNo);
                if(add.arrTime<=i)
                {
                    addProcessQueue(add);
                    addNo++;
                }
                else break;
            }
            //System.out.println(pqProcess+" "+i+" "+listOfProcess.get(0).burTime);
            //if(extra!=null) {pqProcess.add(extra); extra=null;}
            timePassed= processOne(i);
        }
    }    
    
    public void processGantt()
    {
        Gantt a= listOfGantt.get(0);
        ArrayList<Gantt> neww= new ArrayList<Gantt>();
        for(int i=1;i<listOfGantt.size();i++)
        {
            if(listOfGantt.get(i).name.equals(a.name))
            {
                a.end=listOfGantt.get(i).end;
            }
            
            else 
            {
                neww.add(a);
                a= listOfGantt.get(i);
            }
        }
        neww.add(a);
        listOfGantt=neww;
    }
    
    public void printGantt()
    {
        System.out.println();
        processGantt();
        for (Gantt a : listOfGantt)
        System.out.print("|"+a.start+" "+a.name+" "+a.end+"|");
    }
    
    public void printTable()
    {
        System.out.println("\nProcess\tArr T\tBurst T\tWait T\tTAT\tPriority");
        for(Process i:listOfProcess)
        {
            i.waitTime-=i.arrTime;
            i.updateTAT();
            avgTAT+=i.TATime;
            avgWT+=i.waitTime;
            i.printPriorityProcess();
        }
        avgTAT/=listOfProcess.size();
        avgWT/=listOfProcess.size();
        System.out.println("\nAverage Wait Time = "+avgWT);
        System.out.println("Average Turn Around Time = "+avgTAT);
    }
    
    public static void main(String args[])
    {
        
       String pId[]= {"P1","P2","P3","P4","P5"};
       int at[]= {0,0,6,11,12};
       int bt[]= {4,3,7,4,2};
       int pri[]= {1,2,1,3,2};
        
        
       System.out.println("Priority Process");
       Priority  abc= new Priority();
       abc.initProcess(pId,at,bt,pri);
       abc.Processing();
       abc.printGantt();
       abc.printTable(); 
    }

    
}
