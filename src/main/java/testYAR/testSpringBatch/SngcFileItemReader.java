package testYAR.testSpringBatch;

import org.springframework.batch.item.file.FlatFileItemReader;

import testYAR.testSpringBatch.exception.HeaderErrorException;

/**
 * Le ItemReader
 * 
 * @author yarrami
 *
 * @param <SngcMirTrim>
 */
public class SngcFileItemReader<SngcMir> extends FlatFileItemReader<SngcMir> {
	private boolean headerError = false;

	protected void doOpen() throws Exception {
		try {
			super.doOpen();
		} catch (HeaderErrorException e) {
			headerError = true;
		}
	}

	protected SngcMir doRead() throws Exception {
		if (headerError) {
			return null;
		}
		return super.doRead();
	}
}