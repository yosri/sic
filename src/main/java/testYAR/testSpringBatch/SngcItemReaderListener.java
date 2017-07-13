package testYAR.testSpringBatch;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileParseException;

import testYAR.testSpringBatch.model.sngc.SngcMirTrim;

public class SngcItemReaderListener implements ItemReadListener<SngcMirTrim> {

	@Override
	public void beforeRead() {
		System.out.println("ItemReadListener - beforeRead");
	}

	@Override
	public void afterRead(SngcMirTrim item) {
		System.out.println("ItemReadListener - afterRead");

	}

	@Override
	public void onReadError(Exception ex) {
		FlatFileParseException exception = (FlatFileParseException) ex;
		System.err.println("ItemReadListener - onReadError - ligne : " + exception.getInput());
	}
}