package com.solstis.scheduler.config;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.item.ItemProcessor;

import java.io.File;
import java.util.Collection;

public class FileDeletionItemProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String filePath) throws Exception {
        String[] fileTypes = {"bin","gz"};
        Collection<File> files= FileUtils.listFiles(new File(filePath),fileTypes , true);


        for (File file:files){
            file.delete();
        }
        return "File deleted:" + filePath;
    }
}
