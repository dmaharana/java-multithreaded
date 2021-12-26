package in.home.app.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import in.home.app.models.RequestResponseObject;
import in.home.app.utils.NamedThreadFactory;

public class ProcessMultipleRequests {

	private int dataSize;
	private int batchSize;
	List<RequestResponseObject> requestResponseObjects;

	public ProcessMultipleRequests(int dataSize, int batchSize) {
		super();
		this.dataSize = dataSize;
		this.batchSize = batchSize;
	}

	public void multiThreadedAppCall() {
		RequestResponseObject requestResponseObject;
		ExecutorService executorService = Executors.newFixedThreadPool(batchSize, new NamedThreadFactory());
		CountDownLatch countDownLatch = new CountDownLatch(batchSize);

		GenerateSampleData generateSampleData = new GenerateSampleData(dataSize);
		generateSampleData.generateSampleData();
		List<RequestResponseObject> requestResponseObjects1 = generateSampleData.getInputList();
		requestResponseObjects = new ArrayList<>();
		List<Future<RequestResponseObject>> futures = new ArrayList<>();

		for (Iterator iterator = requestResponseObjects1.iterator(); iterator.hasNext();) {
			requestResponseObject = (RequestResponseObject) iterator.next();
			futures.add(executorService.submit(new RequestProcessingService(requestResponseObject, countDownLatch)));
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		executorService.shutdown();

		for (Iterator iterator = futures.iterator(); iterator.hasNext();) {
			Future<RequestResponseObject> future = (Future<RequestResponseObject>) iterator.next();
			try {
				requestResponseObjects.add(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * for (Iterator iterator = requestResponseObjects.iterator();
		 * iterator.hasNext();) { RequestResponseObject elemObject =
		 * (RequestResponseObject) iterator.next(); System.out.println("Input: " +
		 * elemObject.getRequest() + ", Response: " + elemObject.getResponse()); }
		 */
	}

	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	public List<RequestResponseObject> getRequestResponseObjects() {
		return requestResponseObjects;
	}

	public void setRequestResponseObjects(List<RequestResponseObject> requestResponseObjects) {
		this.requestResponseObjects = requestResponseObjects;
	}
}
