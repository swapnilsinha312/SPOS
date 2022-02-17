 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO 
{
    static void readFile(statementInfo stats, String filename) throws IOException
    {
        File inputFile = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String st;
        int lineNo=0;

        //lines are 1 indexed

        while ((st = br.readLine()) != null )
        {
            lineNo++;
            // if(st.indexOf("START")==0)
            // stats.LineNoStart=lineNo;
            //start at ind=1, end at index-1
            stats.input.add(st);
            if(st.indexOf("MACRO")==0 || st.indexOf("MEND")==0 || st.indexOf("START")==0)
            {
                if(st.indexOf("MACRO")==0 && stats.indexBarriers.size()>0 && stats.indexBarriers.get(stats.indexBarriers.size()-1)==lineNo-1  )
                stats.indexBarriers.remove(stats.indexBarriers.size()-1);
                stats.indexBarriers.add(lineNo);
                //stats.macroNames.put(st.substring(0,st.indexOf(" ")),lineNo);
            }
            String parts[] = st.split(" ");
            stats.addStatement(parts);
        }
            
        br.close();
    }

    static String readFileEXP(String filename) throws IOException
    {
        String ret="";
        File inputFile = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String st;
         
        while ((st = br.readLine()) != null )
            ret+=st+"\n";
            
        br.close();
        return ret;
    }

    public static void write(String fname, String content) 
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
