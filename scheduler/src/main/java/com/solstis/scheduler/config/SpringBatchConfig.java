package com.solstis.scheduler.config;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {


    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    @Value("${solstis.folders.path.purge}")
    private String folderPath;

    @Value("${solstis.folders.file.types}")
    private String fileTypes;

    @Bean
    public  Collection<File> reader() {

     Collection<File> fileCollection =  FileUtils.listFiles(new File(folderPath), fileTypes.split(","), true);
      return fileCollection;
    }


    @Bean
    public FileProcessor fileProcessor(){
        return new FileProcessor();
    }

    @Bean
    public RepositoryItemWriter<File> writer(){
        RepositoryItemWriter<File> writer = new RepositoryItemWriter<>();
        return writer;
    }

    public Step step1(){

    }
}
