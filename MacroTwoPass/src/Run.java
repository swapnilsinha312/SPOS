
import java.io.IOException;

public class Run 
{
    public static void main(String args[]) throws IOException
    {
        statementInfo st= new statementInfo();
        String inpFileName= "File/input.txt";
        IO.readFile(st,inpFileName);
        st.addMacroNames();
        //System.out.println(st.macroNames);
       
        MDT mdt= new MDT(st);
        mdt.process();
        
        
        ALA ala= new ALA(mdt.st.indexBarriers.get(st.indexBarriers.size()-1),st);
        ala.process();
       

        Expand exp= new Expand(st.indexBarriers.get(st.indexBarriers.size()-1), st,mdt.MDTEntries);
        exp.process();
         


    }    
}
