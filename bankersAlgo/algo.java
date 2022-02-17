import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BankersAlgo
{
    List<List<Integer>> max;
    List<List<Integer>> need;
    List<List<Integer>> allocation;
    List<Integer> availibleResources;
    List<String> processNames;
    List<String> resourceNames;
    List<String> orderProcess; 

    public BankersAlgo()
    {
        this.max= new ArrayList<>();
        this.need= new ArrayList<>();
        this.allocation= new ArrayList<>();
        this.processNames= new ArrayList<>(); 
        this.availibleResources= new ArrayList<>();
        this.orderProcess= new ArrayList<>();
    }

    public void inputNumbers() throws IOException
    {
        InputStreamReader oo= new InputStreamReader(System.in);
        BufferedReader sc= new BufferedReader(oo);
        System.out.println("Enter the names of Processes (Space seperated)");
        this.processNames= Arrays.asList(sc.readLine().split(" "));
        System.out.println("Enter the names of shared resources (Space Seperated)");
        this.resourceNames= Arrays.asList(sc.readLine().split(" "));
        System.out.println("Enter originally availible resources (Space Seperated)");
        List<String> temp = Arrays.asList(sc.readLine().split(" ")); 
        for(int j=0;j<resourceNames.size();j++)
                availibleResources.add(Integer.parseInt(temp.get(j)));
        // sc.close();
        // oo.close();
    }

    public void inputMax() throws IOException
    {
        InputStreamReader oo= new InputStreamReader(System.in);
        BufferedReader sc= new BufferedReader(oo);
        System.out.println("Enter the value of needed amount of resources in given order for each process next to it's name "+resourceNames);
        for(int i=0;i<processNames.size();i++)
        {
            System.out.println(processNames.get(i)+" ");
            List<String> temp = Arrays.asList(sc.readLine().split(" ")); 
            List<Integer> temp1= new ArrayList<>();
            for(int j=0;j<resourceNames.size();j++)
                temp1.add(Integer.parseInt(temp.get(j)));
            max.add(temp1);            
        } 
        // sc.close(); 
        // oo.close(); 
    }

    public void inputAllocated() throws IOException
    {
        InputStreamReader oo= new InputStreamReader(System.in);
        BufferedReader sc= new BufferedReader(oo);
        System.out.println("Enter the value of already alolocated resouces in given order for each process next to it's name "+resourceNames);
        for(int i=0;i<processNames.size();i++)
        {
            System.out.print(processNames.get(i)+" ");
            List<String> temp = Arrays.asList(sc.readLine().split(" ")); 
            List<Integer> temp1= new ArrayList<>();
            for(int j=0;j<resourceNames.size();j++)
                temp1.add(Integer.parseInt(temp.get(j)));
            allocation.add(temp1);            
        } 
        sc.close(); 
        oo.close();
    }

    public void needCalc()
    {
        for(int i=0;i<processNames.size();i++)
        {
            need.add(new ArrayList<Integer>());
            for(int j=0;j<resourceNames.size();j++)
            {
                int a = allocation.get(i).get(j);
                int b= max.get(i).get(j);
                int c= b-a;
                if(c<0) c=0;
                need.get(i).add(c);
            }
        }
    }

    public boolean checkOneEligible(int index)
    {
        int cntr=0;
        for(int i=0;i<resourceNames.size();i++)
        {
            if(need.get(index).get(i)<=availibleResources.get(i))
            cntr++;
        }
        
        if(cntr==resourceNames.size())
        {
            for(int i=0;i<resourceNames.size();i++)
            {
                availibleResources.set(i,(allocation.get(index).get(i)+availibleResources.get(i))); 
            }
            return true;
        }
        return false; 
    }


    public void checkEligible()
    {
        ArrayList<Integer> finishedProcesses = new ArrayList<>();
        int c=0;
        int i=0;
        while(processNames.size()!=orderProcess.size() && c++<10*processNames.size())
        {
            if(finishedProcesses.contains(i)) {i++;continue;}

            boolean temp=checkOneEligible(i);
            if(temp) 
            {
                orderProcess.add(processNames.get(i));
                finishedProcesses.add(i);
            }
            // System.out.println(i+" "+processNames.get(i)+" "+temp+"\n");
            // System.out.println(processNames.get(i)+" "+availibleResources);
            i++;
            if(i==processNames.size()) i=0;
        } 
        if(c++>3*processNames.size())
        System.out.println("Lockout");
        // System.out.println(c+" "+need.get(2)+" "+allocation.get(2)+" "+availibleResources.get(2));
    }

    public void run() throws IOException
    {
        // this.inputNumbers();
        // this.inputMax();
        // this.inputAllocated();
        this.needCalc();
        // System.out.println(need);
        this.checkEligible();
        System.out.println("The order of process completion is\t"+orderProcess); 
    }

    public void func(List<List<Integer>> a, Integer aa[][])
    {
        for(int i=0;i<aa.length;i++)
        {
            List<Integer> temp1= new ArrayList<>();
            for(int j:aa[i])
            temp1.add(j);
            a.add(temp1);
        }
    }

    public static void main(String args[]) throws IOException
    {
        BankersAlgo ob= new BankersAlgo();
        // ob.run(); 

        Integer[][] a= {{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
        Integer[][] b= {{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};
        String n1[] = {"p0","p1","p2","p3","p4"};
        String n2[] = {"A","B","C"};
        Integer [] c= {3,3,2}; 
        ob.func(ob.max,b);
        ob.func(ob.allocation,a);
        ob.resourceNames=Arrays.asList(n2);
        ob.processNames=Arrays.asList(n1);
        ob.availibleResources=Arrays.asList(c); 
        ob.run();         
        //for dynamic input cooment lines 169 to 179 uncomment 167 and 144-147


    }

}

