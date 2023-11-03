package com.solstis.scheduler.config;

import com.solstis.scheduler.purge.PurgeFileTasklet;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {


    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job fileDeletionJob(Step deleteFilesStep) {
        return jobBuilderFactory
                .get("fileDeletionJob")
                .start(deleteFilesStep)
                .build();
    }

    @Bean
    public Step deleteFilesStep(ItemReader<String> itemReader, ItemProcessor<String, String> itemProcessor, ItemWriter<String> itemWriter) {
        return stepBuilderFactory
                .get("deleteFilesStep")
                .<String, String>chunk(10) // Process 10 files at a time
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemReader<String> itemReader() {
        List<String> filePaths = Arrays.asList("D:\\SOLSTISCODEBASE\\solstis\\Solstis");
        return new FilePathItemReader(filePaths);
    }

    @Bean
    public ItemProcessor<String, String> itemProcessor() {
        return new FileDeletionItemProcessor();
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        return items -> {
            for (String item : items) {
                System.out.println(item);
            }
        };
    }
}
