package langrensha.io.notify;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;

import langrensha.game.data.Result;
import langrensha.io.protocol.ClientReq;

/**
 * 消息处理工厂
 * 
 * @author 10040
 *
 */
public class ProcessFactory {
	/**
	 * 处理消息时长
	 */
	private static int TIMEOUT = 1000;

	private static Gson gson = new Gson();
	/**
	 * 
	 * @Fields executor: 线程池
	 */
	private static ExecutorService executor = new ThreadPoolExecutor(4, 4, 1000L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());
	/**
	 * 消息分发器
	 */
	private static NotifyBox notifyBox = NotifyBox.getIntance();

	/**
	 * 
	 * @param msg
	 */
	public static void Consumer(final String msg) {

		Result result = Result.MSGSUCCEED;

		if (msg != null) {

			Future<Result> future = executor.submit(new Callable<Result>() {

				@Override
				public Result call() throws Exception {

					// TODO 处理业务
					ClientReq clientReq = new ClientReq();
					try {
						clientReq = gson.fromJson(msg, ClientReq.class);
						
						notifyBox.MsgProcess(clientReq);

						return Result.MSGSUCCEED;
					} catch (Exception e) {
						// TODO: handle exception
						System.err.println("不被接受的数据");
						// 发一条错误信息给客户端
						return Result.MSGFAILED;
					}
					
				}
			});

			try {
				result = future.get(TIMEOUT, TimeUnit.MILLISECONDS);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				// 中断执行此任务的线程
				future.cancel(true);
				// 返回值
				result = Result.MSGFAILED;
				System.err.println(result);

			}
		} else {
			System.out.println("消息为空");
		}
	}
}
