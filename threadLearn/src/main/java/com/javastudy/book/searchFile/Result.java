package com.javastudy.book.searchFile;

/**
 * ProjectName: JavaStudy
 * ClassName: Result
 * PackageName: com.javastudy.book.searchFile
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-10-17 09:55
 */
public class Result {
    private boolean found;
    private String filePath;

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
