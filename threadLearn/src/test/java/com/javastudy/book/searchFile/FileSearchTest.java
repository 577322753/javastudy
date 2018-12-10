package com.javastudy.book.searchFile;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.junit.Assert.*;

/**
 * ProjectName: JavaStudy
 * ClassName: SerialFileSearchTest
 * PackageName: com.javastudy.book.searchFile
 * Description: 文件搜索测试
 *
 * @author: wangjingbiao
 * @date: 2018-10-17 10:30
 */
public class FileSearchTest {
    private static final Logger logger = LoggerFactory.getLogger(SerialFileSearch.class);

    String searchStartPath = "/home/wangjingbiao/";
    String searchFileName = "hk.json";

    /**
     * 测试串行搜索文件
     */
    @Test
    public void serialSearchFile() {
        Long startTime = System.currentTimeMillis();
        SerialFileSearch.searchFiles(new File(searchStartPath), searchFileName, new Result());
        Long endTime = System.currentTimeMillis();
        logger.info("serial use time {}", endTime - startTime);
    }

    /**
     * 测试并行搜索文件
     */
    @Test
    public void parallelSearchFile() {
        Long startTime = System.currentTimeMillis();
        ParallelGroupFileSearch.searchFiles(new File(searchStartPath), searchFileName, new Result());
        Long endTime = System.currentTimeMillis();
        logger.info("parallel use time {}", endTime - startTime);
    }
}