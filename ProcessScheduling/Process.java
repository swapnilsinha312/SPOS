

public class Process 
{
      String pId;
      int arrTime;
      int burTime;
      int compTime;
      int TATime;
      int waitTime;
      int addTime;
      int burRem;
      int addIndex;
      int priority;
    
    public Process(String id, int at, int bt)
    {
        // initialise instance variables
        pId=id;
        arrTime=at;
        burTime=bt;
        compTime=0;
        TATime=0;
        waitTime=0;
        burRem=burTime;
        addTime=0;
        addIndex=1;
        priority=0;
    }
     
    public Process(String id, int at, int bt, int p)
    {
        // initialise instance variables
        pId=id;
        arrTime=at;
        burTime=bt;
        compTime=0;
        TATime=0;
        waitTime=0;
        burRem=burTime;
        addTime=0;
        addIndex=1;
        priority=p;
    }
    
    
    public void updateTAT()
    {
        this.TATime= this.burTime+this.waitTime;
    }
    
    public void printProcess()
    {
        System.out.println(this.pId+"\t"+this.arrTime+"\t"+this.burTime+"\t"+
        this.waitTime+"\t"+this.TATime);
    }
    
    public void printPriorityProcess()
    {
        System.out.println(this.pId+"\t"+this.arrTime+"\t"+this.burTime+"\t"+
        this.waitTime+"\t"+this.TATime+"\t"+this.priority);
    }

    public String toString()
    {
        String ret= pId+" "+arrTime;
        return ret;
    }
}
