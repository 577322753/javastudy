package com.javastudy.book.searchFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ProjectName: JavaStudy
 * ClassName: ParallelGroupFileTask
 * PackageName: com.javastudy.book.searchFile
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-10-17 10:02
 */
public class ParallelGroupFileTask implements Runnable {
    private final String fileName;
    private final ConcurrentLinkedQueue<File> directories;
    private final Result parallelResult;
    private boolean found;

    private static final Logger logger = LoggerFactory.getLogger(ParallelGroupFileTask.class);

    public ParallelGroupFileTask(String fileName, ConcurrentLinkedQueue<File> directories, Result parallelResult) {
        this.fileName = fileName;
        this.directories = directories;
        this.parallelResult = parallelResult;
        this.found = false;
    }

    @Override
    public void run() {
        while (directories.size() > 0) {
            File file = directories.poll();
            try {
                processDirectory(file, fileName, parallelResult);
                if (found) {
                    logger.info("{} has found the file", Thread.currentThread().getName());
                    logger.info("Parallel Search : Path : {}", parallelResult.getFilePath());
                    return;
                }
            } catch (InterruptedException e) {
                logger.debug(" {} has been interrupted", Thread.currentThread().getName());
            }
        }
    }

    private void processDirectory(File file, String fileName, Result parallelResult) throws InterruptedException {
        File[] contents;
        contents = file.listFiles();
        if ((contents == null) || (contents.length == 0)) {
            return;
        }

        for (File content : contents) {
            if (content.isDirectory()) {
                processDirectory(content, fileName, parallelResult);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                if (found) {
                    return;
                }
            } else {
                processFile(content, fileName, parallelResult);
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                if (found) {
                    return;
                }
            }
        }
    }

    private void processFile(File content, String fileName, Result parallelResult) {
        if (content.getName().equals(fileName)) {
            parallelResult.setFilePath(content.getAbsolutePath());
            this.found = true;
        }
    }

    public boolean getFound() {
        return found;
    }
}
