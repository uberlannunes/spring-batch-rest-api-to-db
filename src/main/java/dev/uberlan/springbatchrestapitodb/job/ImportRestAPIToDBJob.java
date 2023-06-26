package dev.uberlan.springbatchrestapitodb.job;

import dev.uberlan.springbatchrestapitodb.client.BinanceClient;
import dev.uberlan.springbatchrestapitodb.mapper.SymbolPriceMapper;
import dev.uberlan.springbatchrestapitodb.model.SymbolPrice;
import dev.uberlan.springbatchrestapitodb.model.SymbolPriceDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Configuration
public class ImportRestAPIToDBJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final BinanceClient binanceClient;
    private final SymbolPriceMapper mapper;

    public ImportRestAPIToDBJob(JobBuilderFactory jobBuilderFactory,
                                StepBuilderFactory stepBuilderFactory,
                                EntityManagerFactory entityManagerFactory,
                                BinanceClient binanceClient,
                                SymbolPriceMapper mapper) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.binanceClient = binanceClient;
        this.mapper = mapper;
    }

    @Bean
    public ItemReader<SymbolPriceDTO> reader() {
        List<SymbolPriceDTO> prices = binanceClient.getPrices();
        return new ListItemReader<>(prices);
    }

    @Bean
    public ItemProcessor<SymbolPriceDTO, SymbolPrice> processor() {
        return mapper::mapToEntity;
    }

    @Bean
    public JpaItemWriter<SymbolPrice> writer() {
        return new JpaItemWriterBuilder<SymbolPrice>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step restAPIToDbStep() {
        return stepBuilderFactory.get("rest-api-to-db-step")
                .<SymbolPriceDTO, SymbolPrice>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job restAPIToDbJob() {
        return jobBuilderFactory.get("rest-api-to-db-job")
                .incrementer(new RunIdIncrementer())
                .start(restAPIToDbStep())
                .build();
    }
}
