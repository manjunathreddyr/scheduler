package com.solstis.scheduler.config;


import org.springframework.batch.item.ItemProcessor;

import java.io.File;

public class FileProcessor implements ItemProcessor<File, File> {


    @Override
    public File process(File file) throws Exception {
        return file;
    }
}
