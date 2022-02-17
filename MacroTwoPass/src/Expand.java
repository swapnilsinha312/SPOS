 

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Expand  
{ 
    int indexOfStart;
    String output;
    statementInfo st;   //ArrayList<String> varIndex;
    ArrayList<String> actualPara;
    HashMap<String,String> varMap;
    HashMap<String,String> mdtEntry;
    ArrayList<String> expList;

    Expand(int indexOfStart, statementInfo st,HashMap<String,String> mdtEntry)
    {
        output="";
        this.st=st;
        this.indexOfStart=indexOfStart;
        actualPara= new ArrayList<>();
        expList= new ArrayList<>();
        varMap=new HashMap<>();
        this.mdtEntry= mdtEntry;
        
        //varIndex=new ArrayList<String>(); //System.out.println(st.statementList); //System.out.println(st.statementList.get(22).vars);
    }

    void addParaMap(statement stat)
    {
        statement macroStat= st.statementList.get(st.macroNames.get(stat.part1));
        varMap.clear();
        String aa[]= stat.part2.split(",");
        for(int i=0;i<aa.length;i++)
        {
            varMap.put(macroStat.vars.get(i),aa[i]); 
        }
    }

    void addParaMap(String str)
    {
        statement macroStat= st.statementList.get(st.macroNames.get(str.substring(0,str.indexOf(" "))));
        varMap.clear();
        String aa[]= str.split(",");
        for(int i=0;i<aa.length;i++)
        {
            varMap.put(macroStat.vars.get(i),aa[i]); 
        }
    }

    // void processStart(int start)
    // {
    //     statement aa=st.statementList.get(start);
    //     //System.out.println(aa.part1+" "+aa.part2+" "+aa.vars);
    //     for(String a:aa.vars)
    //     {
    //         varIndex.add(a);
    //     }
    // }

    void processOneTime() 
    {
        String ret="";
        for(int i=indexOfStart-1;i<st.statementList.size();i++)
        {
            if(st.macroNames.containsKey(st.statementList.get(i).part1))
            {
                addParaMap(st.statementList.get(i));
                int begin=st.macroNames.get(st.statementList.get(i).part1);
                int end= st.indexBarriers.get(st.indexBarriers.indexOf(begin)+1);
                String tempStr=getExpanded(begin,end);
                //ret+=tempStr;
                
                for(String a:tempStr.split("\n"))
                { 
                    if(!(a.equals("MEND") || a.equals("MEND\n")))
                    {
                        expList.add(a+"\n");
                        ret+=a+"\n";
                    }
                    //System.out.println("LLL "+a);
                }
                // //   System.out.println(tempStr+" "+expList);
                // if(expList.get(expList.size()-1).equals("MEND"))
                //    expList.remove(expList.size()-1);
                // //System.out.println(st.statementList.get(i)+" ABCD");
            }

            else 
            { 
                if(!(st.input.get(i).equals("MEND") ||st.input.get(i).equals("MEND\n")))
                {
                    expList.add(st.input.get(i)+"\n");
                    ret+=st.input.get(i)+"\n";
                }
            }
        }
        output+=ret;
        display();
        
    }

    void processRepeat(int ind) 
    {
        String ret="";
        for(int i=0;i<expList.size();i++)
        {
            if(i!=ind)
            {   
                ret+=expList.get(i); 
            }
            else
            { 
                addParaMap(expList.get(i));
                int begin=st.macroNames.get(expList.get(i).substring(0,expList.get(i).indexOf(" ")));
                int end= st.indexBarriers.get(st.indexBarriers.indexOf(begin)+1);
                String tempStr=getExpanded(begin,end);
                ret+=tempStr;
                int addptr=0;
                expList.remove(ind);
                for(String a:tempStr.split("\n"))
                { 
                    if(!(a.equals("MEND") || a.equals("MEND\n")))
                    {
                        expList.add(ind+addptr,a+"\n");
                        addptr++;
                    }
                }
                 

                // String temp= mdtEntry.get(expList.get(ind).substring(0,expList.get(ind).indexOf(" ")));
                
                // int begin=st.macroNames.get(st.statementList.get(ind).part1);
                // int end= st.indexBarriers.get(st.indexBarriers.indexOf(begin)+1);
                // ret+=getExpanded(begin,end);
            }
        }

        output=ret;
        display();

        //     if(st.macroNames.containsKey(st.statementList.get(i).part1))
        //     {
        //         addParaMap(st.statementList.get(i));
        //         int begin=st.macroNames.get(st.statementList.get(i).part1);
        //         int end= st.indexBarriers.get(st.indexBarriers.indexOf(begin)+1);
        //         ret+=getExpanded(begin,end);
        //         System.out.println(st.statementList.get(i)+" ABCD");
        //     }

        //     else 
        //         ret+=st.statementList.get(i)+"\n";
        // }
       // output+=ret;
        
    }

    void process() throws IOException
    {
        processOneTime();
        int a[]= retNoMacroLeft();
        int p=(a[0]);
        //int p=1;
        while(p>0)
        {
            //System.out.println(a[1]);
            processRepeat(a[1]+1);
            display();
            a = retNoMacroLeft();
            p=(a[0]);
        } 
        
        // while(p>0);
        //     System.out.println(retNoMacroLeft()[0]+" "+retNoMacroLeft()[1]+" V "+indexOfStart);
        //     //+st.statementList.get(retNoMacroLeft()[1]+indexOfStart)
        // processRepeat(retNoMacroLeft()[1]+indexOfStart);
    }
    
    int[] retNoMacroLeft() throws IOException //for nested
    {
        String str= IO.readFileEXP("File/Expanded.txt");
        int no[]={0,0};
         
        String []arr= str.split("\n");
        for(int i=0;i<arr.length;i++)
        { 
            String a = arr[i];
            if((a.indexOf(" ")!=-1 && st.macroNames.containsKey(a.substring(0,a.indexOf(" ")))))
            {
                //System.out.println(a.substring(0,a.indexOf(" ")));
                no[0]++;
                no[1]=i;
                break;
            }
            
        }
        return no;

    }
    
        String getExpanded(int start, int end)
        {
            //System.out.println(varMap+" "+start+" "+end);
            String ret="";
            
            
            
            for(int i=start+1;i<end-1;i++)
            {
                statement stat=st.statementList.get(i);
                //System.out.println(stat);
                ArrayList<String> temp = stat.varsExp;

                for(int a=0;a<stat.varsExp.size();a++)
                {
                    //System.out.println(stat);
                    if(varMap.get(temp.get(a))!=null)
                    temp.set(a,varMap.get(temp.get(a)));
                    // //System.out.println(stats.get(i));
                    // int ind=varIndex.indexOf(temp.get(a));
                    // if(ind!=-1)
                    // temp.set(a,actualPara.get(a)); //ind/a
                   
                }
                stat.processPart2RevEXP();
                ret+=stat.part1+" "+stat.part2EXP+"\n";
            }
            return ret;
        }

    
     
    void display()
    {
        //processInline();
        String str="";
        for(int i=0;i<expList.size();i++)
        {
            if(!(expList.get(i).length()>3 && expList.get(i).substring(0,4).equals("MEND")))
                str+=expList.get(i);
        }
        IO.write("File/Expanded.txt",str);

    }

    // void displayCustom()
    // {
    //     //processInline();
    //     String str="";
    //     for(int i=0;i<expList.size();i++)
    //     {
    //         if(!(expList.get(i).length()>3 && expList.get(i).substring(0,4).equals("MEND")))
    //             str+=expList.get(i);
    //     }
    //     IO.write("Expanded.txt",str);
    //     //IO.write("Expanded1.txt",a);

    // }
    
}









// void processRest()
    // {
    //     statement stat=st.statementList.get(i);
    //     ArrayList<String> temp = stat.varsExp;
    //     for(int a=0;a<stat.varsExp.size();a++)
    //     {
    //         //System.out.println(stats.get(i));
    //         int ind=varIndex.indexOf(temp.get(a));
    //         if(ind!=-1)
    //         temp.set(a,varMap.get(temp.get(a)));
    //         stat.processPart2RevEXP();
    //     }
    // }

    //     //System.out.println(aa.part1+" "+aa.part2+" "+aa.vars);
    //     for(String a:aa.vars)
    //     {
    //         varIndex.add(a);
    //     }
        
    // }

     
    // void processExpand(int i) 
    // {
    //     statement stat=st.statementList.get(i);
    //     ArrayList<String> temp = stat.vars;
    //     for(int a=0;a<stat.vars.size();a++)
    //     {
    //         //System.out.println(stats.get(i));
    //         int ind=varIndex.indexOf(temp.get(a));
    //         if(ind!=-1)
    //         temp.set(a,"#"+ind);
    //         st.processPart2Rev();
    //     }
        
    // }   
    