package com.gb.tosca.si_carriere.batch.sngc;

import org.springframework.batch.item.file.FlatFileItemReader;

import com.gb.tosca.si_carriere.batch.sngc.exception.HeaderErrorException;

/**
 * Le ItemReader
 * 
 * @author yarrami
 *
 * @param <Sngc>
 */
public class SngcFileItemReader<Sngc> extends FlatFileItemReader<Sngc> {
	private boolean headerError = false;

	protected void doOpen() throws Exception {
		try {
			super.doOpen();
		} catch (HeaderErrorException e) {
			headerError = true;
		}
	}

	protected Sngc doRead() throws Exception {
		if (headerError) {
			return null;
		}
		return super.doRead();
	}
}