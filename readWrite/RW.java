// import java.io.File; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
// import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class RW
{
    Semaphore mutex;
    Semaphore wrt;
    int readCount=5;

    RW()
    {
        mutex= new Semaphore(1);
        wrt= new Semaphore(1);
        readCount=0;

    }

    class Reader implements Runnable
    {
        int sign;
        public Reader(int i) 
        {
            this.sign=i;
           
        }

        @Override
        public void run() 
        {
            try 
            {
                mutex.acquire();
                readCount++;
                if(readCount==1)
                wrt.acquire();
                mutex.release();

                this.read();
                // System.out.println("Reading"+this.sign); 

                mutex.acquire();
                readCount--;
                if(readCount==0) 
                wrt.release();
                mutex.release();
            } 
            
            catch ( Exception e) 
            {
                 System.out.println("Error");
            }  

        }

        public void read() throws IOException
        {
            // FileWriter writer = new FileWriter("RW.txt",true);
            // writer.write(-this.sign+" ");
            // writer.close();
            
            File f = new File("RW.txt");
            Scanner read = new Scanner(f);
            String line= read.nextLine(); 
            System.out.println("(Reading"+this.sign+") "+line);  
            read.close(); 
        }
        
    }

    class Writer implements Runnable
    {

        int sign;
        public Writer(int i) 
        {
            this.sign=i;
        }

        @Override
        public void run() 
        {
            try 
            {
                wrt.acquire();
                this.write();
                System.out.println("Writing"+this.sign); 
                wrt.release();
            } 
            
            catch ( Exception e) 
            {
                System.out.println("Error");
            }
              
        } 

        public void write() throws IOException
        {
            FileWriter writer = new FileWriter("RW.txt" );
            writer.write("Changed Sample at write thread "+ this.sign+" "+"\n\nOriginal text: \'Sample\'"); 
            writer.close(); 
        }

    }

    public static void main(String args[]) 
    {
        RW a = new RW();
 
        int w=10;
        int r=8;
        // System.out.println("\n\nOriginal text: \'Sample\'");

        for(int i=1;i<=5;i++)
        {
            Thread obj = new Thread(a.new Writer(i));
            obj.setPriority(w);        
            obj.start(); 
            w=5; 


            Thread ob = new Thread(a.new Reader(i));
            ob.setPriority(r);
            ob.start();

            // System.out.println("P "+ob.getPriority());
            
            // System.out.println(Thread.activeCount());
        }
         

    }
    
}
