package PassTwoAssembler.src;

import java.util.HashMap;

class Opcode
{
    String operation;
    String abbr;
    String code;
    int length;
    String nameForSymAndLit;

    Opcode(String operation, String abbr, String code, int length)
    {
        this.operation=operation;
        this.abbr=abbr;
        this.code=code;
        this.length=length;
        this.nameForSymAndLit="";
    }

    Opcode(String operation, String abbr, String code, int length,String name)
    {
        this.operation=operation;
        this.abbr=abbr;
        this.code=code;
        this.length=length;
        this.nameForSymAndLit=name;
    }

    public String toString()
    {
        String ret="";
        ret= abbr+","+code;
        return ret;
    }
}

public class OpcodeTable 
{
    
    public static HashMap<String , Opcode> opList= new HashMap<>();
    
    public static void addOp(String operation, String abbr, String code, int length)
    {
        Opcode op= new Opcode(operation,abbr,code,length);
        opList.put(operation, op);
    }
    
    //run this function in the main class before anything else to add all codes to list

    public static void makeList()
    {
        addOp("STOP","IS","00",1);
        addOp("ADD","IS","01",1);
        addOp("SUB","IS","02",1);
        addOp("MULT","IS","03",1);
        addOp("MOVER","IS","04",1);
        addOp("MOVEM","IS","05",1);
        addOp("COMP","IS","06",1);
        addOp("BC","IS","07",1);
        addOp("DIV","IS","08",1);
        addOp("READ","IS","09",1);
        addOp("PRINT","IS","10",1);
        addOp("START","AD","01",0);
        addOp("END","AD","02",0);
        addOp("ORIGIN","AD","03",0);
        addOp("EQU","AD","04",0);
        addOp("LTORG","AD","05",0);
        addOp("DS","DL","01",0);
        addOp("DC","DL","02",1);
        addOp("AREG","RG","01",0);
        addOp("BREG","RG","02",0);
        addOp("CREG","RG","03",0);
        addOp("EQ","CC","01",0);
    }
    
}
