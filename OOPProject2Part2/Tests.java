import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;

class Tests {

	public static org.junit.platform.commons.logging.Logger logger = LoggerFactory.getLogger(Tests.class);

	@Test
	public void partialTest(){

		CustomExecutor customExecutor = new CustomExecutor();
		var task = Task.createTask(()->{
			int sum = 0;
			for (int i = 1; i <= 10; i++) {
				sum += i;
			}
			return sum;
		}, TaskType.COMPUTATIONAL);
		var sumTask = customExecutor.submit(task);
		final int sum;
		try {
			sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		logger.info(()-> "Sum of 1 through 10 = " + sum);
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
		var reverseTask = customExecutor.submit(callable2, TaskType.IO);
		final Double totalPrice;
		final String reversed;
		try {
			totalPrice = priceTask.get();
			reversed = reverseTask.get();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		logger.info(()-> "Reversed String = " + reversed);
		logger.info(()->String.valueOf("Total Price = " + totalPrice));
		logger.info(()-> "Current maximum priority = " + 
		customExecutor.getCurrentMax());
		customExecutor.gracefullyTerminate();
	}

}
