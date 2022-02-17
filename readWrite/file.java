import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 

public class file {
  public static int status=0;
  int sign;

  public String toString()
  {
    return this.sign+" ";

  }
  
  file(int sign)
  {
    this.sign=sign;
  }

  public void read() throws FileNotFoundException
  {
    if(status==1) 
        while(status!=1)
          continue;

      status=1;
      File f = new File("sample.txt");
      Scanner read = new Scanner(f);
      System.out.println("Reading"+this.sign);  
      read.close(); 
      status=0;
  }

  public void write() throws IOException
  { 
    if(status==1) 
        while(status!=1)
          continue;
      status=1;
      FileWriter writer = new FileWriter("sample.txt",true);
      writer.write(this.toString());
      System.out.println("Writing"+this.sign); 
      writer.close();  
      status=0;
  }
 
}