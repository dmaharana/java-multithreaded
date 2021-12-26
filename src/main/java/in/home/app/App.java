package in.home.app;

import java.util.Iterator;
import java.util.List;

import in.home.app.models.RequestResponseObject;
import in.home.app.services.ProcessMultipleRequests;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		demoMultithreadedAppCall();
	}

	private static void demoMultithreadedAppCall() {
		int dataSize = 10;
		int batchSize = 3;
		List<RequestResponseObject> requestResponseObjects;

		ProcessMultipleRequests processMultipleRequests = new ProcessMultipleRequests(dataSize, batchSize);
		processMultipleRequests.multiThreadedAppCall();

		requestResponseObjects = processMultipleRequests.getRequestResponseObjects();

		for (Iterator iterator = requestResponseObjects.iterator(); iterator.hasNext();) {
			RequestResponseObject elemObject = (RequestResponseObject) iterator.next();
			System.out.println("Input: " + elemObject.getRequest() + ", Response: " + elemObject.getResponse());
		}

	}

}
