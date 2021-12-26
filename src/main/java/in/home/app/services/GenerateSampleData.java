package in.home.app.services;

import java.util.ArrayList;
import java.util.List;

import in.home.app.models.RequestResponseObject;

public class GenerateSampleData {
	private List<RequestResponseObject> inputList;
	private int inputSize;

	public GenerateSampleData(int inputSize) {
		super();
		this.inputSize = inputSize;
	}

	public void generateSampleData() {
		inputList = new ArrayList<RequestResponseObject>();

		for (int i = 0; i < this.inputSize; i++) {
			RequestResponseObject requestResponseObject = new RequestResponseObject();
			requestResponseObject.setRequest(Math.random());
			inputList.add(requestResponseObject);
		}
	}

	public List<RequestResponseObject> getInputList() {
		return inputList;
	}

	public void setInputList(List<RequestResponseObject> inputList) {
		this.inputList = inputList;
	}

	public int getInputSize() {
		return inputSize;
	}

	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}

}
