package PassTwoAssembler.src;

import java.util.ArrayList;

class processing
{
    int lineCounter;
    IntCodeTable intCode;
    int locCounter;
    LiteralTable litTable;
    SymTable symTable;
    statementInfo allStats;
    int statementIndex;
    ArrayList<Integer> pooltable;
    
    processing()
    {
        lineCounter=0;
        OpcodeTable.makeList();
        intCode= new IntCodeTable();
        pooltable= new ArrayList<>();
        this.locCounter =0;
        this.litTable= new LiteralTable();
        this.symTable= new SymTable();
        this.allStats= new statementInfo();
        this.statementIndex=0;
    } 

    void process(statements stat)
    {
        Opcode op= OpcodeTable.opList.get(stat.operation);
        intCode.indexTable.add(locCounter);
        // A DC '5'
        // add dc's int code here and if areg the add it in areg

        //check equ first

        intCode.table.add(new ArrayList<Opcode>());
        //intCode.table.get(intCode.table.size()-1).add(op);
        //System.out.println(stat.operation+" a "+op+" a "+stat+" a ");

        if(op.abbr.equals("AD"))
            processAD(stat,op);
        
        else if(op.abbr.equals("IS"))
        {
            if(!stat.label.equals("") )
            {
                Symbol temp=symTable.symMap.get(stat.label);
                if(temp!=null)
                    temp.location=locCounter;
                    
                else 
                symTable.add(stat.label, locCounter);

                //intCode.table.get(intCode.table.size()-1).add(new Opcode("Symbol","S",""+symTable.indexMap.get(stat.label),0));

            }

            
            processIS(stat,op);
            //System.out.println(stat.label+" ii"+symTable.symMap.get(stat.label));
        }
             
        
        else if(op.abbr.equals("DL"))
             processDL(stat,op);

        else if(op.abbr.equals("CC"))
            processCC(stat,op);
            
        if(stat.part3.contains("REG"))
           processRG(stat,op);

        lineCounter++;

        // System.out.println("REG");//
        //System.out.println(op.abbr);
        // if(!stat.label.equals(""))
        // symTable.add(stat.label, locCounter);

        // Opcode op = OpcodeTable.opList.get(stat.operation);
        
        // if(stat.operation.equals("LTORG"))
        //     processLTORG(stat);
        // if(statementIndex== allStats.statementList.size()-1)
        //     processEND();       
    }

    

    void processIS(statements stat,Opcode op)
    {
        intCode.table.get(intCode.table.size()-1).add(op);
        locCounter+=op.length;
    }
    
    void processAD(statements stat,Opcode op)
    { 
        
        if(op.operation.equals("START"))
            processStart(stat,op);
        
        else if(op.operation.equals("END"))
            processEND(stat,op);
        
        else if(op.operation.equals("ORIGIN"))
            processORIGIN(stat,op);                              //A ORIGIN C+1  //A ORIGIN C
        
        else if(op.operation.equals("EQU"))
            processEQU(stat,op);
            
        else if(op.operation.equals("LTORG"))
            {
                pooltable.add(litTable.litMap.size()-litTable.noAdress.size());
                processLTORG(stat,op);
            }
        
        intCode.table.get(intCode.table.size()-1).add(op);
        locCounter+=op.length;
                   
    }

    void processStart(statements stat, Opcode op)
    {
        locCounter+= Integer.parseInt(stat.part3);
        intCode.table.get(intCode.table.size()-1).add(new Opcode("Constant","C",""+locCounter,0));
    }

    void processORIGIN(statements stat, Opcode op)
    {
        if(stat.part3.toUpperCase().equals(stat.part3.toLowerCase()))
            locCounter=Integer.parseInt(stat.part3);
            else 
            {
                int plusIndex= stat.part3.indexOf("+");
                int minusIndex= stat.part3.indexOf("-");
                if(plusIndex!=-1 && minusIndex!=-1)
                locCounter=(symTable.symMap.get(stat.part3).location);
                String toGet=""; int add=0;
                if(minusIndex==-1)
                {
                    toGet=stat.part3.substring(0,plusIndex);
                    add= Integer.parseInt(stat.part3.substring(plusIndex+1,stat.part3.length()));
                }
                    
                else 
                    {
                        toGet=stat.part3.substring(0,minusIndex);
                        add= -1*Integer.parseInt(stat.part3.substring(minusIndex+1,stat.part3.length()));
                    
                    }
                locCounter=(symTable.symMap.get(toGet).location)+add;
                intCode.table.get(intCode.table.size()-1).add(new Opcode("Constant","C",""+locCounter,0));
            

                // if(minusIndex==-1)
                // locCounter=(symTable.symMap.get(stat.part3).location);
                // else 
                // {
                //     String sym= stat.part3.substring(0,plusIndex);
                //     int add= Integer.parseInt(stat.part3.substring(plusIndex+1,stat.part3.length()));
                //     locCounter=(symTable.symMap.get(sym).location)+add;
                // }
            }
    }

    void processEQU(statements stat,Opcode op)
    {
        //A EQU 100  || A EQU B+1
        //remove things here ? idk
        //label has to be gven pre-existing symbol value in if

        int loc;

        if(stat.part3.toUpperCase().equals(stat.part3.toLowerCase()))
            {
                loc=Integer.parseInt(stat.part3);
                symTable.symMap.get(stat.label).location=(loc);
            }
        else 
            {
                int plusIndex= stat.part3.indexOf("+");
                int minusIndex= stat.part3.indexOf("-");
                if(plusIndex!=-1 && minusIndex!=-1)
                locCounter=(symTable.symMap.get(stat.part3).location);
                String toGet=""; int add=0;
                if(minusIndex==-1)
                {
                    toGet=stat.part3.substring(0,plusIndex);
                    add= Integer.parseInt(stat.part3.substring(plusIndex+1,stat.part3.length()));
                }
                    
                else 
                    {
                        toGet=stat.part3.substring(0,minusIndex);
                        add= -1*Integer.parseInt(stat.part3.substring(minusIndex+1,stat.part3.length()));
                    
                    }
                symTable.symMap.get(stat.label).location=(symTable.symMap.get(toGet).location)+add;
                    
                    //System.out.println("L "+stat.label+" "+ symTable.symMap.get(stat.label).location+" "+symTable.symMap.get(toGet).location+" L "+add);
            }
            //intCode.table.get(intCode.table.size()-1).add(new Opcode("Constant","C",""+symTable.symMap.get(stat.label).location,0));
            //maybe need the above line not sure

        // Symbol lb = symTable.symMap.get(stat.label);
        // if(lb==null) symTable.add(stat.label,loc); 
        // else  lb.location=loc; 
        //A EQU A  ||  A EQU 100  || A EQU A+1
    }

    void processDL(statements stat,Opcode op)
    {
        if(op.operation.equals("DS"))
        {
            Symbol temp= symTable.symMap.get(stat.label);
            if(temp!=null)
            temp.location=locCounter;
            symTable.add(stat.label,locCounter);
            
            intCode.table.get(intCode.table.size()-1).add(op);
            intCode.table.get(intCode.table.size()-1).add(new Opcode("Symbol","S",""+symTable.indexMap.get(stat.label),0,stat.label));
            intCode.table.get(intCode.table.size()-1).add(new Opcode("Constant","C",""+stat.part3,0));
            //System.out.println("M"+stat.label);
            //intCode.indexTable.add(locCounter);
            locCounter+=Integer.parseInt(stat.part3) ;
            //check if symbol in table if in then add location to it
            //else add new symbol to table
            //store int code using opcode and loc counter
            //locCounter++;
        }

        if(op.operation.equals("DC"))
        {
            Symbol temp= symTable.symMap.get(stat.label);
            if(temp!=null)
            temp.location=locCounter;
            symTable.add(stat.label,locCounter);

            intCode.table.get(intCode.table.size()-1).add(op);
            //intCode.table.get(intCode.table.size()-1).add(new Opcode("Symbol","S",""+symTable.indexMap.get(stat.label),0));
            
            if(!litTable.noAdress.contains(stat.part3.charAt(1)+""))
            {
                int s= stat.part3.indexOf("'");
                int e= stat.part3.lastIndexOf("'");
                litTable.add(stat.part3.substring(s,e+1)+" "+lineCounter,-1);
                //System.out.println(litTable.indexMap+" "+stat.part3.substring(s,e+1));
                intCode.table.get(intCode.table.size()-1).add(new Opcode("Literal","L",""+litTable.indexMap.get(stat.part3.substring(s,e+1)+" "+lineCounter),0,stat.part3.substring(s,e+1)+" "+lineCounter));
            }
               
            
            locCounter+=op.length;
            
            //intCode.indexTable.add(locCounter);
            //if literal has no address give adreess else move give label adress 
            //add to intermediate table
            //locCounter++;
        }
        //C DC '5'
        // sym table pdate
        // intermediate code


    }

    void processRG(statements stat,Opcode op)
    {
        String regName=stat.processedThirdpart.get(0);
        Opcode reg= OpcodeTable.opList.get(regName);
        
        intCode.table.get(intCode.table.size()-1).add(reg);
        //intCode.indexTable.add(locCounter);
        //locCounter+=op.length;
        
        if(!stat.part3.equals("") && stat.part3.contains("'"))
            {
                String lit=stat.processedThirdpart.get(1);
                Literal temp=litTable.litMap.get(lit);
                if(temp==null)
                // {
                //     temp.location=locCounter;
                //     if(litTable.noAdress.contains(temp.name))
                //     litTable.noAdress.remove(temp.name);
                // }
                
                // else 
                litTable.add(lit+" "+lineCounter, -1);

                intCode.table.get(intCode.table.size()-1).add(new Opcode("Literal","L",""+litTable.indexMap.get(lit+" "+lineCounter),0,lit+" "+lineCounter));

            }

            if(!stat.part3.equals("") && !stat.part3.contains("'"))
            {
                String lit=stat.processedThirdpart.get(1);
                int ind=symTable.noAdress.indexOf(lit);
                Symbol temp;
                if(ind!=-1)
                    temp=symTable.symMap.get(symTable.noAdress.get(ind));
                else temp=null;
                if(temp!=null)
                {
                    temp.location=locCounter;
                    if(symTable.noAdress.contains(temp.name))
                    symTable.noAdress.remove(temp.name);
                }
                
                else 
                symTable.add(lit, locCounter);
                 
                intCode.table.get(intCode.table.size()-1).add(new Opcode("Symbol","S",""+symTable.indexMap.get(lit),0,lit));

            }
        // if(stat.processedThirdpart.size()>1)
        // {
        //     String a = stat.processedThirdpart.get(1);
        //     if(a.contains("'"))
        //     intCode.table.get(intCode.table.size()-1).add(new Opcode("Constant","C",""+litTable.indexMap.get(a),0));
        //     // else 
        //     // intCode.table.get(intCode.table.size()-1).add(new Opcode("Symbol","S",""+symTable.indexMap.get(a),0));
        // }
        
            
        //This is the commom three line in each function
        // intCode.table.get(intCode.table.size()-1).add(reg);
        // intCode.indexTable.add(locCounter);
        // locCounter+=op.length;

        //suppose A MOVEM AREG,B
        //this func will add int code for areg only
        //ie AREG= rg,01 This part only
        
    }

    void processCC(statements stat,Opcode op)
    {

    }

 
    void processLTORG(statements stat, Opcode op)
    {
        for(String i:litTable.noAdress)
        {
            Literal lt= litTable.litMap.get(i);
            lt.location=locCounter;
            locCounter++;
        } 
        //locCounter+=litTable.noAdress.size();
        litTable.noAdress.clear();
        
        // intCode.table.get(intCode.table.size()-1).add(op);
        // //intCode.indexTable.add(locCounter);
        // locCounter+=op.length;
    }

    void giveLitAdd()
    {

    }

    void processEND(statements stat, Opcode op)
    {
        for(String i:litTable.noAdress)
        {
            Literal lt= litTable.litMap.get(i);
            lt.location=locCounter;
            locCounter++;
        } 
        litTable.noAdress.clear();

        // intCode.table.get(intCode.table.size()-1).add(op);
        // //intCode.indexTable.add(locCounter);
        // locCounter+=op.length;
    }

    // public static void main(String args[])
    // {

    // }

}