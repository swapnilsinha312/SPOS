import  java.io.*;
import java.util.*;

class compRR implements Comparator<Process>
{
        public int compare(Process a , Process b)
        {
            if(a.burRem==b.burRem)
            {
                if(a.addTime>b.addTime)
                return 1;
                else if(a.addTime<b.addTime) 
                return -1;
                else return a.addIndex-b.addIndex; 
            }
            else if(a.burRem<b.burRem)
            {
                return -1;
            }
            else if (a.arrTime== b.arrTime)
            return a.addIndex-b.addIndex;
            else return -1;
            
        }
}

class sortByArr implements Comparator<Process>
{
        public int compare (Process a , Process b)
        {
            if(a.arrTime<b.arrTime) return -1;
            if(a.arrTime>b.arrTime) return 1;
            else 
            return a.addIndex-b.addIndex;
        }
}
    
    
public class RoundRobin 
{
    ArrayList<Process> listOfProcess;
    ArrayList<Gantt> listOfGantt;
    Queue <Process> pqProcess;
    Process extra;
    Double avgWT;
    Double avgTAT;
    int quantumTime;
    
    public RoundRobin(int qt)
    {
        listOfProcess= new ArrayList<Process>();
        listOfGantt= new ArrayList<Gantt>();
        pqProcess= new LinkedList<Process>();
        quantumTime=qt;
        extra = null;
        avgTAT=0.0;
        avgWT=0.0;
    }
    
    public void addGanttToList(String name,int start, int end)
    {
        Gantt a = new Gantt(name, start, end);
        listOfGantt.add(a);
    }
    
    
        
    public void initProcess(String pId[], int at[], int bt[])
    {
        for (int i=0;i<pId.length;i++)
        {
            Process a = new Process(pId[i], at[i], bt[i]);
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
        if(a.burRem>quantumTime)
        {
            time=quantumTime;
            a.burRem-=quantumTime; 
            extra = a;
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
        Collections.sort(listOfProcess, new sortByArr());
        //System.out.println(listOfProcess.get(0).pId+"<<");
        int endTime= totBT();
        int timePassed=0;
        int addNo=0;
        for(int i=1;i<=endTime;i+=timePassed) // if i=0 then < end time check
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
            if(extra!=null) {pqProcess.add(extra); extra=null;}
            timePassed= processOne(i);
        }
    }    
    
    public void printGantt()
    {
        System.out.println();
        for (Gantt a : listOfGantt)
        System.out.print("|"+a.start+" "+a.name+" "+a.end+"|");
    }
    
    public void printTable()
    {
        System.out.println("\nProcess\tArr T\tBurst T\tWait T\tTAT");
        for(Process i:listOfProcess)
        {
            i.updateTAT();
            avgTAT+=i.TATime;
            avgWT+=i.waitTime;
            i.printProcess();
        }
        avgTAT/=listOfProcess.size();
        avgWT/=listOfProcess.size();
        System.out.println("\nAverage Wait Time = "+avgWT);
        System.out.println("Average Turn Around Time = "+avgTAT);
    }
    
    public static void main(String args[])
    {
        
        // String pId[]= {"P4","P5","P3","P2","P1","P6"};
        //int at[]= {1,2,3,4,5,6};
        //int bt[]= {9,2,7,6,5,3};
        System.out.println("Round Robin");
        String []pId = {"P2","P1","P3","P4"};
        int at[]= {2,1,3,4};
        int bt[]= {4,2,3,1};
        int quantTime=2;
       
        //String pId[]= {"P1","P2","P3"};
       // int at[]= {0,0,0};
       // int bt[]= {10,5,8};
       RoundRobin abc= new RoundRobin(quantTime);
        abc.initProcess(pId,at,bt);
        abc.Processing();
        abc.printGantt();
        abc.printTable();
    }

    
}
