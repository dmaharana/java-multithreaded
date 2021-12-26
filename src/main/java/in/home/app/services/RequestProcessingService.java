package in.home.app.services;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import in.home.app.models.RequestResponseObject;

public class RequestProcessingService implements Callable<RequestResponseObject> {

	private RequestResponseObject requestResponseObject;
	private CountDownLatch countDownLatch;

	public RequestProcessingService(RequestResponseObject requestResponseObject, CountDownLatch countDownLatch) {
		super();
		this.requestResponseObject = requestResponseObject;
		this.countDownLatch = countDownLatch;
	}

	public RequestResponseObject populateResponseObject() {
		requestResponseObject.setResponse(Math.acos(requestResponseObject.getRequest()));
		System.out.println(
				"Current Thread: " + Thread.currentThread().getName() + ", Req: " + requestResponseObject.getRequest());

		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (countDownLatch != null) {
			countDownLatch.countDown();
		}
		return requestResponseObject;
	}

	@Override
	public RequestResponseObject call() throws Exception {
		return populateResponseObject();
	}
}
