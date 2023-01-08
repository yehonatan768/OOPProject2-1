import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Ex2_1 {
    public static String[] createTextFiles(int n,int seed,int bound) throws IOException {
        String[] file_names = new String[n];
        Random rnd = new Random(seed);
        for (int i = 1; i <= n; i++) {
            String name = "file_" + i;
            file_names[i-1] = name;
            File file = new File(name);
            file.createNewFile();
            int x = rnd.nextInt(bound);
            FileWriter fw = new FileWriter(name);
            for (int j = 0; j < x-1; j++) {
                fw.write("This is a line !\n");
            }
            fw.write("This is a line !");
            fw.close();
        }
        return file_names;
    }
    public static int getNumOfLines(String []fileNames) throws FileNotFoundException {
        int count_rows=0;
        for (int i = 0; i < fileNames.length; i++) {
            File file = new File(fileNames[i]);
            Scanner sc = new Scanner(file);
            while(sc.hasNext())
            {
                count_rows++;
                sc.nextLine();
            }
            sc.close();
        }
        return count_rows;
    }
    public int getNumOfLinesThreads(String[] fileNames) throws InterruptedException {
        int sum = 0;
        section_3[] array = new section_3 [fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            array[i] = new section_3(fileNames[i]);
            array[i].start();
        }
        for (int i = 0; i < array.length; i++) {
            array[i].join();
            sum+=array[i].getCount_rows();
        }
        return sum;
    }
    public int getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException {
        int sum = 0;
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
        Future<Integer> [] futures = new Future[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            section_4 s4 = new section_4(fileNames[i]);
            futures[i] = executor.submit(s4);
        }
        for (int i = 0; i < futures.length; i++) {
            sum+=futures[i].get();
        }
        executor.shutdown();
        return sum;
    }
}
