package com.solstis.scheduler.config;

import org.springframework.batch.item.ItemReader;

import java.util.List;

public class FilePathItemReader implements ItemReader<String> {
    private List<String> filePaths;
    private int currentIndex = 0;

    public FilePathItemReader(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public String read() throws Exception {
        if (currentIndex < filePaths.size()) {
            return filePaths.get(currentIndex++);
        } else {
            return null;
        }
    }
}
