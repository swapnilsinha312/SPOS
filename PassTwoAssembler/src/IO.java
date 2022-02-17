package PassTwoAssembler.src; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Collections; 

public class IO
{
      String intCodeFileName;
      String inputFileName;
      String outFileName;
      String machCodeFileName;
      String litCodeFileName;
      String poolFileName;
      String symTableFileName;
    
      IO(String inp, String out, String sym, String inter , String mach, String lit, String pool)
    {
      intCodeFileName=inter;
      inputFileName= inp;
      outFileName= out;
      machCodeFileName=mach;
      litCodeFileName=lit;
      poolFileName=pool;
      symTableFileName= sym;
    }

    void readFile(statementInfo stats) throws IOException
    {
        File inputFile = new File(inputFileName);
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String st;
        while ((st = br.readLine()) != null)
        {
            // System.out.println(st+" L"); // st=st.replaceAll("\t", "5");  // st=st.replaceAll("\\s", " "); //st=st.replace(",", "$"); //st = st.replaceAll("\t", " ");//System.out.println(parts.length);    
            String parts[] = st.split(" ");
            stats.addStatement(parts);
            //for(String str:parts) // System.out.print(str+" "); // System.out.println();
        }
            
        br.close();
    }


    public void output(processing abcd)
    { 
      write(intCodeFileName,abcd.intCode.display());
      write(symTableFileName,abcd.symTable.display());
      write(litCodeFileName,abcd.litTable.display());
      write(poolFileName,abcd.pooltable+" ");
      write(machCodeFileName,ProcessingPassTwo.machineCode(abcd));
    }

 
    public void write(String fname, String content) 
    {
        try 
        {
          FileWriter myWriter = new FileWriter(fname);
          myWriter.write(content);
          myWriter.close();
          System.out.println("Data Written in "+fname);
        } 
        catch (IOException e) 
        {
          System.out.println("An error occurred in "+fname);
          e.printStackTrace();
        }
    }
}





// package TwoPass;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.Collections; 

// class IO
// {
//     void readFile(statementInfo stats) throws IOException
//     {
//         File inputFile = new File("inputFile.txt");
//         BufferedReader br = new BufferedReader(new FileReader(inputFile));
//         String st;
//         while ((st = br.readLine()) != null)
//         {
//             // System.out.println(st+" L"); // st=st.replaceAll("\t", "5");  // st=st.replaceAll("\\s", " "); //st=st.replace(",", "$"); //st = st.replaceAll("\t", " ");//System.out.println(parts.length);    
//             String parts[] = st.split(" ");
//             stats.addStatement(parts);
//             //for(String str:parts) // System.out.print(str+" "); // System.out.println();
//         }
            
//         br.close();
//     }

//     public static void main(String args[]) throws IOException
//     {
//         IO abc= new IO();
//         OpcodeTable.makeList();
//         statementInfo st= new statementInfo();
//         abc.readFile(st);

//         //System.out.print(" foaisunpao");
//         // for(statements a:st.statementList)
//         // {a.display();}

//         processing abcd = new processing();
//         for(statements a:st.statementList)
//         { 
//             abcd.process(a);
//         }
//         output(abcd);
//         System.out.println(abcd.litTable.noAdress);
//         System.out.println(abcd.symTable.noAdress);
        
//     }

//     public static void output(processing abcd)
//     {
//       write("IntCode.txt",abcd.intCode.display());
//       write("SymbolTable.txt",abcd.symTable.display());
//       write("LiteralTable.txt",abcd.litTable.display());
//       write("PoolTable.txt",abcd.pooltable+" ");
//       write("MachineCode.txt",machineCode(abcd));
//     }

//     public static String machineCode(processing abcd)
//     {
//       IntCodeTable code= abcd.intCode;
//       String ret="";
//       String [] mca= {"IS","RG","S","L"};
      
//       for(int i=0;i<code.table.size();i++)                  //one line of intermediate code
//         {
//           String retSubPart=""; String space="";
//           int zeroCounter=0; int found=0;
//           Collections.sort(code.table.get(i),new IntCodeCompMachCode());
//           retSubPart+=code.indexTable.get(i)+"\t";  
                              
//           oneEl:for(int j=0;j<code.table.get(i).size();j++)       // one element of one line of intermediate code
//             { 
//             int zc=0;
//             for(int k=0;k<mca.length;k++)                   //LC IS RG S/L 
//             {  
//               if(code.table.get(i).get(j).abbr.equals(mca[k]))//ret+=0+"\t";
//               {
//                 if(mca[k].charAt(0)=='L')
//                 {
//                   String name= space+code.table.get(i).get(j).nameForSymAndLit; 
//                   name=name.substring(name.indexOf("'"));
//                   //retSubPart+=abcd.litTable.litMap.get(name).location+code.table.get(i).get(j).abbr+"\t";// System.out.println(name);  // System.out.println(abcd.litTable.litMap); // System.out.println(abcd.litTable.indexMap);
//                   retSubPart+=abcd.litTable.litMap.get(name).location+"\t";
//                 }

//                 else if(mca[k].charAt(0)=='S')
//                 {
//                   String name= code.table.get(i).get(j).nameForSymAndLit;
//                   //name=name.substring(name.lastIndexOf(" ")); // name=name.strip();// System.out.println(name);// System.out.println(abcd.symTable.symMap);// System.out.println(abcd.symTable.indexMap);              // System.out.println("A"+name+"A");// System.out.println(abcd.symTable.symMap);
//                   retSubPart+=space+abcd.symTable.symMap.get(name).location+"\t";
//                   //retSubPart+=space+abcd.symTable.symMap.get(name).location+code.table.get(i).get(j).abbr+"\t";//retSubPart+=space+name;
//                 }

//                 else 
//                   retSubPart+=space+code.table.get(i).get(j).code+"\t";
//                   //retSubPart+=space+code.table.get(i).get(j).code+code.table.get(i).get(j).abbr+"\t";
                
//                 found++;
//                 continue oneEl;
//               }
//               else //IF NONE IS FOUND IN THE REQUIRED LIST  
//               {
//                 zc++;
//                 space="";
//                 for(int ii=0;ii<zc-found;ii++)
//                   space+="00\t";
//                 //retSubPart+="0\t";
//               }
//             } 
//             if(zc==4) 
//               zeroCounter++;
//           }
//           if (zeroCounter==1) retSubPart="";
//           ret+=retSubPart+"\n";
//         }
//         return ret;
//     }

//     public static void write(String fname, String content) 
//     {
//         try {
//           FileWriter myWriter = new FileWriter(fname);
//           myWriter.write(content);
//           myWriter.close();
//           System.out.println("Data Written in "+fname);
//         } 
//         catch (IOException e) 
//         {
//           System.out.println("An error occurred in "+fname);
//           e.printStackTrace();
//         }
//       }

// }

