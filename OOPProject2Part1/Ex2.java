import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Ex2 {
    public static void deleteFiles(int n)
    {
        for (int i = 1; i <= n; i++) {
            String name = "file_"+i;
            File file = new File(name);
            file.delete();
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String[]arr = Ex2_1.createTextFiles(1000,12566,50);
        long startTime=0,endTime=0,totalTime=0;

        startTime = System.nanoTime();
        Ex2_1.getNumOfLines(arr);
        endTime = System.nanoTime();
        totalTime= endTime - startTime;
        System.out.println("Total time for first function is: " + totalTime/1000000000.0);

        startTime = System.nanoTime();
        Ex2_1.getNumOfLinesThreads(arr);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Total time for second function is: " + totalTime/1000000000.0);

        startTime = System.nanoTime();
        Ex2_1.getNumOfLinesThreadPool(arr);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Total time for third function is: " + totalTime/1000000000.0);

        deleteFiles(1000);
    }
}