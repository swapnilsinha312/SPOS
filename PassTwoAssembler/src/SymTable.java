package PassTwoAssembler.src;

import java.util.ArrayList;
import java.util.HashMap; 

class Symbol 
{
    int location;
    String name; 

    Symbol(int number, String name) 
    {
		this.location = number;
		this.name = name;
	}
    public String toString()
    {
        String ret=name+" "+location;
        return ret;
    }

}
//S ,01 problem
public class SymTable 
{
    HashMap<String , Symbol> symMap;
    HashMap<String , Integer> indexMap; 
    //ArrayList<Symbol> table; 
    ArrayList<String> noAdress; 
    
    SymTable()
    {
       symMap= new HashMap<>();
       //table = new ArrayList<Symbol>();
       noAdress= new ArrayList<>();
       indexMap= new HashMap<>();
    }

    void add(Symbol neww)
    {
        //table.add(neww);
        symMap.put(neww.name,neww);
        if(neww.location==-1)
        noAdress.add(neww.name);
        indexMap.put(neww.name,indexMap.size());
    }

    void add(String label, int loc)
    {
        Symbol neww= new Symbol(loc,label);
        symMap.put(neww.name,neww);
        if(neww.location==-1)
        noAdress.add(neww.name);
        indexMap.put(neww.name,indexMap.size());
        //table.add(neww);
    }

    void add(String label)
    {
        Symbol neww= new Symbol(-1,label);
        //table.add(neww);
        symMap.put(neww.name,neww);
        noAdress.add(neww.name);
        indexMap.put(neww.name,indexMap.size());
    }

    String display()
    {
        String ret="";
        for(String i: symMap.keySet())
        {
            ret+=(symMap.get(i))+"\n";
        }
        return ret;
    }
    // ArrayList<Symbol> getSymTable()
    // {
    //     return table;
    // }
}


