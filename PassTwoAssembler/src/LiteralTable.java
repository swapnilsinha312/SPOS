package PassTwoAssembler.src;

import java.util.ArrayList;
import java.util.HashMap; 

class Literal
{
    int location;
    String name; 

    Literal(int number, String name) 
    {
		this.location = number;
		this.name = name;
	}

    public String toString()
    {
        String ret=name.substring(0,name.lastIndexOf("'")+1)+" "+location;
        return ret;
    }
}

public class LiteralTable 
{
    HashMap<String,Literal> litMap;
    HashMap<String,Integer> indexMap;
    //ArrayList<Literal> table; 
    ArrayList<String> noAdress; 
    
    LiteralTable()
    {
        litMap= new HashMap<>();
       //table = new ArrayList<Literal>();
       noAdress= new ArrayList<>();
       indexMap= new HashMap<>();
    }

    void add(Literal neww)
    {
       // table.add(neww);
        litMap.put(neww.name,neww);
        if(neww.location==-1)
        noAdress.add(neww.name);
        indexMap.put(neww.name,indexMap.size());
    }

    void add(String label, int loc)
    {
        Literal neww= new Literal(loc,label);
        litMap.put(neww.name,neww);
        if(neww.location==-1)
        noAdress.add(neww.name); 
        indexMap.put(neww.name,indexMap.size());
        //table.add(neww);
    }

    void add(String label)
    {
        Literal neww= new Literal(-1,label);
        //table.add(neww);
        litMap.put(neww.name,neww);
        noAdress.add(neww.name);
        indexMap.put(neww.name,indexMap.size());
    }


    String display()
    {
        String ret=""; 
        for(String i: litMap.keySet())
        {
            ret+=(litMap.get(i))+"\n";
        }
        return ret;
    }

    // ArrayList<Literal> getSymTable()
    // {
    //     return table;
    // }
}

