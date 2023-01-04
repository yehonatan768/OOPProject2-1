import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class section_4 implements Callable<Integer> {

    private String file_name;
    public section_4(String file_name)
    {
        this.file_name = file_name;
    }
    @Override
    public Integer call() throws Exception {
        int count_rows = 0;
        File file = new File(file_name);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            count_rows++;
            sc.nextLine();
        }
        sc.close();
        return count_rows;
    }
}
