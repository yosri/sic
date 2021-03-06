package com.gb.tosca.si_carriere.batch.sngc;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchMain {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext cpt = new ClassPathXmlApplicationContext("spring-batch-context.xml");
		cpt.start();
		JobLauncher jobLauncher = (JobLauncher) cpt.getBean("jobLauncher");
		Job job = (Job) cpt.getBean("importMir");
		JobParameters parameter = new JobParametersBuilder().addDate("date", new Date()).addString("inputFileName", args[0]).toJobParameters();
		jobLauncher.run(job, parameter);
	}
}
