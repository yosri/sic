package com.gb.tosca.si_carriere.common;

import org.apache.log4j.Logger;

public class Loggers {
	
	private final static String OK_LOG = "OKLogger";
	private final static String KO_LOG = "KOLogger";
	private final static String DEFAULT_LOG = "rootLogger";
	
	public static Logger getLog(){
		return Logger.getLogger(DEFAULT_LOG);
	}
	
	public static Logger getOkLog(String message){
		Logger.getLogger(OK_LOG).info(message);
		return Logger.getLogger(DEFAULT_LOG);
	}
	
	public static Logger getKOLog(String message){
		Logger.getLogger(KO_LOG).info(message);
		return Logger.getLogger(DEFAULT_LOG);
	}
}
