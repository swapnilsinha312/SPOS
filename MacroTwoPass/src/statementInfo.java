 

import java.util.ArrayList;
import java.util.HashMap;

class statement 
{
    String part1;
    String part2;
    String part2MDT;
    String part2EXP;
    ArrayList<String> vars;
    ArrayList<String> varsExp;
   // HashMap<String, hashedVars;
    
    statement(String part1, String part2)
    {
        this.part1=part1;
        this.part2=part2;
        part2MDT=part2;
        part2EXP=part2;
        vars= new ArrayList<String>();
        processPart2();
        varsExp=new ArrayList<String>(vars);
        
    }

    void processPart2()
    {
        if(part2.equals("")) return;        //part2+=","; //System.out.println(part2);
        String [] temp= part2.split(",");
        
        for(int i=0;i<temp.length;i++)
            vars.add(temp[i]);
         
    }

    void processPart2RevMDT()
    {
        // &a,&b 
        // #0 #1
        // MOVER A,B
        // MOVER #0,#1

        if(part2=="") return;
        String ret=vars.get(0);
        
        for(int i=1;i<vars.size();i++)
            ret+=","+vars.get(i);
        part2MDT=ret;
    }

    void processPart2RevEXP()
    {
        if(part2=="") return;
        String ret=varsExp.get(0);
        
        for(int i=1;i<varsExp.size();i++)
            ret+=","+varsExp.get(i);
        part2EXP=ret;
    }

    void display()
    {
        System.out.println(part1+" "+part2);
    }

    public String toString()
    {
        return part1+" "+part2+" ";
    }
}

//first process all above start 
// then below
public class statementInfo
{

    ArrayList<statement> statementList;
    ArrayList<String> input;
    int LineNoStart;
    ArrayList<Integer> indexBarriers;
    HashMap<String,Integer> macroNames;
    

    statementInfo()
    {
        input= new ArrayList<String>();
        macroNames= new HashMap<>();
        indexBarriers= new ArrayList<Integer>();
        LineNoStart=-1;
        statementList= new ArrayList<>();
    }

    void addStatement(String a[])
    {
        statement toAdd;
        if(a.length==1)
        toAdd = new statement(a[0],"");
        else 
        toAdd = new statement(a[0],a[1]);   // statements like mend and macro or luke add &A,&B
        statementList.add(toAdd);
    }

    void addStatement(String part1,String part2)
    {
        statement neww = new statement(part1,part2);
        statementList.add(neww);
    }

    void addMacroNames()
    {
        //System.out.println(indexBarriers);
        for(int i=0;i<indexBarriers.size()-2;i++)
        {
            macroNames.put(statementList.get(indexBarriers.get(i)).part1,indexBarriers.get(i));
            //System.out.println(i+" "+statementList.get(indexBarriers.get(i)).part1);
        }
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
}