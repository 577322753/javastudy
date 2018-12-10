package com.javastudy.book.searchFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * ProjectName: JavaStudy
 * ClassName: SerialFileSearch
 * PackageName: com.javastudy.book.searchFile
 * Description: 串行搜索文件实现
 *
 * @author: wangjingbiao
 * @date: 2018-10-17 09:53
 */
public class SerialFileSearch {
    private static final Logger logger = LoggerFactory.getLogger(SerialFileSearch.class);

    public static void searchFiles(File file, String fileName, Result result) {
        File[] contents;
        contents = file.listFiles();

        if (contents == null || (contents.length == 0)) {
            return;
        }

        for (File content : contents
        ) {
            if (content.isDirectory()) {
                searchFiles(content, fileName, result);
            } else {
                if (content.getName().equals(fileName)) {
                    result.setFilePath(content.getAbsolutePath());
                    result.setFound(true);
                    logger.info("Serial Search : Path : {}",result.getFilePath());
                    return;
                }
            }
        }
        if(result.isFound()){
            return;
        }
    }
}
