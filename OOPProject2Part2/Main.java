import java.util.Collection;
import java.util.concurrent.*;

import java.util.concurrent.Callable;


public class Main {
    public static void main(String[] args) throws Exception {
        CustomExecutor customExecutor = new CustomExecutor();
        var task = Task.createTask(() -> {
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        System.out.println(sumTask.get());
            final int sum;
            try {
                sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
            Callable<Double> callable1 = ()-> {
                return 1000 * Math.pow(1.02, 5);
            };
            Callable<String> callable2 = ()-> {
                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                return sb.reverse().toString();
            };

            var priceTask = customExecutor.submit(()-> {
                return 1000 * Math.pow(1.02, 5);
            }, TaskType.COMPUTATIONAL);
            System.out.println(priceTask.get());

            var reverseTask = customExecutor.submit(callable2, TaskType.IO);
            System.out.println(reverseTask.get());

            final Double totalPrice;
            final String reversed;

            try {
                totalPrice = (Double) priceTask.get();
                reversed = (String) reverseTask.get();
            }
            catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            int maximum = customExecutor.getCurrentMax();
            System.out.println(maximum);
            customExecutor.gracefullyTerminate();
        }

    }