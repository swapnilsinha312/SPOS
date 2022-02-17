 

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap; 

public class MDT 
    {
    
    //processing one statement at a time from line MACRO ie 1st line to Start
    ArrayList<String> varIndex;
    HashMap<String,String> MDTEntries;
    HashMap<Integer,String> MNTEntries;
    statementInfo st;
    ArrayList<statement> stats;

    MDT(statementInfo st)
    {
        //System.out.println(stats);
        this.stats=new ArrayList<>(st.statementList);
        this.varIndex=new ArrayList<>();
        this.st=st;
        MDTEntries= new HashMap<>();
        MNTEntries= new HashMap<>();
        //Collections.copy(this.stats,stats);
        // startIndex=s;
        // endIndex=i;
        //s++;
    }
 
    void processStart(int start,int mntInd)
    {
        statement aa=stats.get(start);
        MNTEntries.put(start-mntInd-1,aa.part1);
        //System.out.println(aa.part1+" "+aa.part2+" "+aa.vars);
        for(String a:aa.vars)
        {
            varIndex.add(a);
        }
    }

    void processRest(int i)
    {
        statement st=stats.get(i);
        ArrayList<String> temp = st.vars;
        for(int a=0;a<st.vars.size();a++)
        {
            //System.out.println(stats.get(i));
            int ind=varIndex.indexOf(temp.get(a));
            if(ind!=-1)
            temp.set(a,"#"+ind);
            st.processPart2RevMDT();
        }
    }

    void processOneBatch(int start, int end, int mntInd)
    {
        processStart(start,mntInd);
        for(int i=start+1;i<end;i++)
        {
            processRest(i);
        }
    }
 
    
    void process()
    {
        //System.out.println(st.indexBarriers);
        for(int i=0;i<st.indexBarriers.size()-2;i++)
        {
            //System.out.println(st.indexBarriers.get(i)+"L");
            processOneBatch(st.indexBarriers.get(i),st.indexBarriers.get(i+1),i);
            varIndex.clear();
        }
        display();
        processMNT();
    }
    
    void display()
    {
        String ret="";
        String str="";
        for(int i=1;i<st.indexBarriers.get(st.indexBarriers.size()-1);i++)
        {
        if(stats.get(i).toString().indexOf("MACRO")==0 ||  stats.get(i).toString().indexOf("START")==0)
        {
            str+=ret;
            MDTEntries.put(ret.substring(0,ret.indexOf(" ")),ret);
            ret="";

        } 
        else 
            {
                ret+=(stats.get(i).part1+" "+stats.get(i).part2MDT)+"\n";
            }
        }
 
        IO.write("File/MDT.txt",str);
    }

    void processMNT()
    {
        //System.out.println(MNTEntries);
        String str="";
        ArrayList<Integer> key= new ArrayList<>(MNTEntries.keySet());
        Collections.sort(key);
        
        for(int i:key)
        {
             str+=i+"\t"+MNTEntries.get(i)+"\n";
        }
        IO.write("File/MNT.txt",str);
        
    }


}

