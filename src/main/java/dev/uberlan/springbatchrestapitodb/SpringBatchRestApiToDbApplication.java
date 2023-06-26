package dev.uberlan.springbatchrestapitodb;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.batch.operations.JobRestartException;

@SpringBootApplication
@EnableBatchProcessing
@EnableFeignClients
public class SpringBatchRestApiToDbApplication implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job jobImportCsvToDb;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchRestApiToDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters params = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();		try {
			jobLauncher.run(jobImportCsvToDb, params);
		} catch (JobExecutionAlreadyRunningException | JobRestartException |
				 JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
