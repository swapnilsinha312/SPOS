import java.io.IOException;

public class Run implements Runnable
{
   int sign;
     Run(int sign)
     {
         this.sign=sign;
     }
    
    @Override
    public void run() { 
        file name= new file(sign);
        
        try 
        {
            name.read();
            name.write();
        } 
        
        catch (IOException e) { 
            System.out.println("Error");
        }
         
    }
    public static void main(String args[]) 
    { 
        for(int i=0;i<10;i++)
        {
            Thread ob = new Thread(new Run(i));
            ob.start();
        }
    }

    
}
