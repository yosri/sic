package testYAR.testSpringBatch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchMir 
{
    public static void main( String[] args ) throws Exception
    {
    	ClassPathXmlApplicationContext cpt = new ClassPathXmlApplicationContext("spring-batch-context.xml");
        cpt.start();
        JobLauncher jobLauncher = (JobLauncher) cpt.getBean("jobLauncher");
        Job job = (Job) cpt.getBean("importMir");
        
        JobParameters parameter = new JobParametersBuilder().addDate("date", new Date()).addString("input.file.name", args[0]).toJobParameters();
//        JobParameters parameter = new JobParametersBuilder().addDate("date", new Date()).addString("input.file", "input/personnes.txt").toJobParameters();
//        JobParameters parameter = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        jobLauncher.run(job, parameter);
    }
}
