
public class Gantt
{
    int start;
    int end;
    String name;

    
    public Gantt(String nam, int a , int b)
    {
      this.start=a;
      this.end=b;
      this.name=nam;
    }
    
    public void print()
    {
        System.out.print("|"+start+" "+name+" "+end+"|");
    }

    
}
