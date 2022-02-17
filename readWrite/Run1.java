import java.util.concurrent.Semaphore;  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;  

public class Run1 implements Runnable
{
   int sign;
   Semaphore sem;
     Run1(int sign, Semaphore sem)
     {
         this.sign=sign;
         this.sem=sem;
     }
    
    @Override
    public void run() { 
        file1 name= new file1(sign,sem);
        
        try 
        {
            name.read();
            name.write();
        } 
        
        catch (IOException | InterruptedException e) { 
            System.out.println("Error");
        }
         
    }
    public static void main(String args[]) 
    { 
        Semaphore sem= new Semaphore(1);
        for(int i=0;i<10;i++)
        {
            Thread ob = new Thread(new Run1(i,sem));
            ob.start();
        }
    }

    
}


class file1 {
  public static int status=0;
  int sign;
  Semaphore sem;

  public String toString()
  {
    return this.sign+" ";

  }
  
  file1(int sign, Semaphore sem)
  {
    this.sem=sem;
    this.sign=sign;
  }

  public void read() throws FileNotFoundException, InterruptedException
  {
      sem.acquire();
      File f = new File("sample1.txt");
      Scanner read = new Scanner(f);
      
      // System.out.println(read.nextLine());  
      read.close(); 
      sem.release(); 
  }

  public void write() throws IOException, InterruptedException
  { 
      sem.acquire(); 
      FileWriter writer = new FileWriter("sample1.txt",true);
      System.out.println("Writing"+this.sign); 
      writer.write(this.toString());
      writer.close();  
      sem.release();
  }
 
}