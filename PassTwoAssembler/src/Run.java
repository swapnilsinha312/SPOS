package PassTwoAssembler.src;

import java.io.IOException;

public class Run 
{
    public static void main(String args[]) throws IOException
    {
        
        IO io= new IO("src/File/inputFile.txt", "src/File/outputfile.txt","src/File/SymbolTable.txt",  "src/File/IntCode.txt" ,"src/File/MachineCode.txt" ,"src/File/LiteralTable.txt", "src/File/PoolTable.txt");
        OpcodeTable.makeList();
        statementInfo st= new statementInfo();
        io.readFile(st);

        //System.out.print(" check");
        // for(statements a:st.statementList)
        // {a.display();}

        processing abcd = new processing();
        for(statements a:st.statementList)
        { 
            abcd.process(a);
        }
        io.output(abcd);
        
        // System.out.println(abcd.litTable.noAdress);
        // System.out.println(abcd.symTable.noAdress);
    }
}
