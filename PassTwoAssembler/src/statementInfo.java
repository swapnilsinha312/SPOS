package PassTwoAssembler.src;

import java.util.ArrayList;

class statements 
{
    String statement;
    String label;
    String operation;
    String part3;
    ArrayList<String> processedThirdpart;
    int number;
    int abbr;

    statements(String part1,String part2,String part3)
    {
        this.label=part1;
        this.operation=part2;
        this.part3=part3;
        this.statement= part1+" "+part2+" "+part3;
        this.processedThirdpart= process3rd();
    }

    void display()
    {
        System.out.println(statement+processedThirdpart);
    }

    public String toString()
    {
        return (statement+processedThirdpart);
    }

    ArrayList<String> process3rd()
    {
        //System.out.println(part3);
        if(part3.equals("")) return new ArrayList<>(); 
        ArrayList<String> ret = new ArrayList<>();
        int commaIn= part3.indexOf(",");
        int equIn= part3.indexOf("="); 
        if(commaIn==-1)
        {
            ret.add(part3);
            return ret;
        }
        else 
        ret.add(part3.substring(0,commaIn));
        if(equIn!=-1) 
        {
            ret.add(part3.substring(equIn+1,part3.length()));
        }
        else ret.add(part3.substring(commaIn+1,part3.length()));
             
        return ret;
    }
}


public class statementInfo
{

    ArrayList<statements> statementList;

    statementInfo()
    {
        statementList= new ArrayList<>();
    }

    void addStatement(String a[])
    {
        statements toAdd;
        if(a.length==1)
        toAdd = new statements("",a[0],"");         // Three types of statements there can be
        else if(a.length==2)                        // START 100
        {                                           //A MOVER AREG,='1'
            String check= a[0];                     //C STOP
            if(OpcodeTable.opList.keySet().contains(check)) 
            toAdd = new statements("",a[0],a[1]);
            else
            toAdd = new statements(a[0],a[1],"");
        }
        else 
        toAdd = new statements(a[0],a[1],a[2]);
        statementList.add(toAdd);
    }

    void addStatement(String part1,String part2,String part3)
    {
        statements neww = new statements(part1,part2,part3);
        statementList.add(neww);
    }
 
}
