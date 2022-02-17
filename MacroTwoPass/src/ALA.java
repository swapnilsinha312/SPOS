

import java.util.ArrayList;

class ALAStruct
{
    int lineNo;
    statement ALAStat;

    ALAStruct(String a[], int line)
    {
        this.ALAStat= new statement(a[0],a[1]);
        lineNo=line;
    }

    ALAStruct(String a,String b, int line)
    {
        this.ALAStat= new statement(a,b);
        lineNo=line;
    }

    public String toString()
    {
        return lineNo+"\t"+ALAStat.part1+"\t"+ALAStat.part2;
    }
}

public class ALA  
{
    int indexOfStart;
    ArrayList<ALAStruct> alaTable;
    statementInfo st;

    ALA(int indexOfStart, statementInfo st)
    {   
        alaTable= new ArrayList<>();
        this.st=st;
        this.indexOfStart=indexOfStart;
    }

    void addALA(int index, String a,String b)
    {
        ALAStruct neww= new ALAStruct(a,b,index);
        alaTable.add(neww);
    }

    void process()
    {
        for(int i=indexOfStart;i<st.statementList.size();i++)
        {
            if(st.macroNames.containsKey(st.statementList.get(i).part1))
            {
                addALA(i, st.statementList.get(i).part1, st.statementList.get(i).part2);
                //System.out.println(st.statementList.get(i).part2);
                // int end=st.indexBarriers.get(st.indexBarriers.indexOf(st.macroNames.get(st.statementList.get(i).part1)+1));
                // processOne(i,end);
                // i=end;
            }
        }
        display();
    }

    void display()
    {
        String ret="";
        for(ALAStruct a:alaTable)
        ret+=a+"\n";
        IO.write("File/ALA.txt",ret);
    }

   
}
