package PassTwoAssembler.src;

import java.util.Collections;

public class ProcessingPassTwo 
{
    public static String machineCode(processing abcd)
    {
      IntCodeTable code= abcd.intCode;
      String ret="";
      String [] mca= {"IS","RG","S","L"};
      
      for(int i=0;i<code.table.size();i++)                  //one line of intermediate code
        {
          String retSubPart=""; String space="";
          int zeroCounter=0; int found=0;
          Collections.sort(code.table.get(i),new IntCodeCompMachCode());
          retSubPart+=code.indexTable.get(i)+"\t";  
                              
          oneEl:for(int j=0;j<code.table.get(i).size();j++)       // one element of one line of intermediate code
            { 
            int zc=0;
            for(int k=0;k<mca.length;k++)                   //LC IS RG S/L 
            {  
              if(code.table.get(i).get(j).abbr.equals(mca[k]))//ret+=0+"\t";
              {
                if(mca[k].charAt(0)=='L')
                {
                  String name= space+code.table.get(i).get(j).nameForSymAndLit; 
                  name=name.substring(name.indexOf("'"));
                  //retSubPart+=abcd.litTable.litMap.get(name).location+code.table.get(i).get(j).abbr+"\t";// System.out.println(name);  // System.out.println(abcd.litTable.litMap); // System.out.println(abcd.litTable.indexMap);
                  retSubPart+=abcd.litTable.litMap.get(name).location+"\t";
                }

                else if(mca[k].charAt(0)=='S')
                {
                  String name= code.table.get(i).get(j).nameForSymAndLit;
                  //name=name.substring(name.lastIndexOf(" ")); // name=name.strip();// System.out.println(name);// System.out.println(abcd.symTable.symMap);// System.out.println(abcd.symTable.indexMap);              // System.out.println("A"+name+"A");// System.out.println(abcd.symTable.symMap);
                  retSubPart+=space+abcd.symTable.symMap.get(name).location+"\t";
                  //retSubPart+=space+abcd.symTable.symMap.get(name).location+code.table.get(i).get(j).abbr+"\t";//retSubPart+=space+name;
                }

                else 
                  retSubPart+=space+code.table.get(i).get(j).code+"\t";
                  //retSubPart+=space+code.table.get(i).get(j).code+code.table.get(i).get(j).abbr+"\t";
                
                found++;
                continue oneEl;
              }
              else //IF NONE IS FOUND IN THE REQUIRED LIST  
              {
                zc++;
                space="";
                for(int ii=0;ii<zc-found;ii++)
                  space+="00\t";
                //retSubPart+="0\t";
              }
            } 
            if(zc==4) 
              zeroCounter++;
            //System.out.println(code.table.get(i).size()+" "+zc+" "+zeroCounter+" "+i);
          }
          if (zeroCounter>=1 ) retSubPart="";
          ret+=retSubPart+"\n";
        }
        return ret;
    }    
}
