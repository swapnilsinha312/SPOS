package PassTwoAssembler.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IntCodeTable 
{
    ArrayList<ArrayList<Opcode>> table;
    ArrayList<Integer> indexTable;

    IntCodeTable()
    {
        table= new ArrayList<>();
        indexTable = new ArrayList<>();
    }

    String display()
    {
        String ret="";
        for(int i=0;i<table.size();i++)
        {
            Collections.sort(table.get(i),new IntCodeComp());
            //Collections.sort(table.get(i),new IntCodeComp());
            ret+=indexTable.get(i)+"\t\t\t";
            ArrayList<Opcode> temp= table.get(i);
            for(Opcode tt:temp) ret+="("+tt+") ";
            ret+="\n";
        }
        return ret;
    }
    
    class IntCodeComp implements Comparator<Opcode>
{
    
    @Override
    public int compare(Opcode o1, Opcode o2) 
    {

        if(o1.abbr.equals ("L") || o1.abbr.equals("C"))
        return 1;

        if(o2.abbr.equals("L") || o2.abbr.equals("C"))
        return -1;

        return (o1.abbr.compareTo(o2.abbr));

        
    }
}
}

     class IntCodeCompMachCode implements Comparator<Opcode>
    {
    
    @Override
    public int compare(Opcode o1, Opcode o2) 
    {
        //"IS","RG","S","L"

        if(o1.abbr.equals ("IS"))
        return -1;

        if(o2.abbr.equals ("IS"))
        return 1;

        if(o1.abbr.equals ("RG"))
        return -1;

        if(o2.abbr.equals ("RG"))
        return 1;

        if(o1.abbr.equals ("S"))
        return -1;

        if(o2.abbr.equals ("S"))
        return 1;

        if(o1.abbr.equals ("L"))
        return -1;

        if(o2.abbr.equals ("L"))
        return 1;

        return (o1.abbr.compareTo(o2.abbr));

        
    }


}