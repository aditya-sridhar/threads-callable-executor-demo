import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Worker implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {

		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += i;
		}
		return sum;
	}

}

public class ExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(4);
		Future<Integer>[] futures = new Future[5];
		Callable<Integer> w = new Worker();
		try {
			for (int i = 0; i < 5; i++) {
				Future<Integer> future = executors.submit(w);
				futures[i] = future;

			}

			for (int i = 0; i < futures.length; i++) {
				try {
					System.out.println("Result from Future " + i + ":" + futures[i].get());
				} catch (InterruptedException e) {

					e.printStackTrace();
				} catch (ExecutionException e) {

					e.printStackTrace();
				}
			}
		} finally {
			executors.shutdown();
		}

	}

}
