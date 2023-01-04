import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class section_3 extends Thread {

    private String file_name;
    private volatile int count_rows;
    public section_3(String file_name)
    {
        this.file_name = file_name;
    }

    @Override
    public void run()
    {
        this.count_rows=0;
        File file = new File(file_name);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while(sc.hasNext())
        {
            this.count_rows++;
            sc.nextLine();
        }
        sc.close();
    }
    public int getCount_rows(){
        return this.count_rows;
    }

}
