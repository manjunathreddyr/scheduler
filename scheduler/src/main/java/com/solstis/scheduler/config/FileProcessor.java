package com.solstis.scheduler.config;


import org.springframework.batch.item.ItemProcessor;

import java.io.File;

public class FileProcessor implements ItemProcessor<File, File> {


    @Override
    public File process(File file) throws Exception {

       long lastModified =  file.lastModified();
        long hours = lastModified / (1000 * 60 * 60);
        if (hours >4){
            return file;
        }

        return null;
    }
}
