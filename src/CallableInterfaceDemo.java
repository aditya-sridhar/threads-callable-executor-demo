import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class CallableTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {

		int sum = 0;
		for (int i = 0; i < 5; i++) {
			sum += i;
		}
		return sum;
	}

}

public class CallableInterfaceDemo {

	public static void main(String[] args) {
		FutureTask<Integer>[] futureList = new FutureTask[5];

		for (int i = 0; i <= 4; i++) {
			Callable<Integer> callable = new CallableTask();
			futureList[i] = new FutureTask<Integer>(callable);
			Thread t = new Thread(futureList[i]);
			t.start();

		}

		for (int i = 0; i <= 4; i++) {
			FutureTask<Integer> result = futureList[i];
			try {
				System.out.println("Future Task" + i + ":" + result.get());
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} catch (ExecutionException e) {
				
				e.printStackTrace();
			}
		}

	}

}
