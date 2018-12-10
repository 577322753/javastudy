package com.book.testIo;

import java.io.*;

/**
 * ProjectName: JavaStudy
 * ClassName: SpiltFile
 * PackageName: com.book.testIo
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-08-30 18:37
 */
public class SpiltFile {
    public static void main(String[] args) throws IOException {
        //每个流文件保存的大小
        int FILE_LENGTH = 50*1024*1024;
        String filePath = "/home/wangjingbiao/testIo/source.mp4";
        File file = new File(filePath);
        System.out.println(file.length());
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream reader = new BufferedInputStream(inputStream);
        int count = 0;
        while (true){
            byte[] oneFile = new byte[FILE_LENGTH];
            int len = reader.read(oneFile,0,FILE_LENGTH);
            if(len == -1){
                break;
            }
            String saveFilePath = "/home/wangjingbiao/testIo/tmpSpilt/file"+count+".part";
            BufferedOutputStream writer = null;
            try{
                writer = new BufferedOutputStream(new FileOutputStream(saveFilePath));
                writer.write(oneFile,0,len);
                writer.flush();
            }catch (Exception e){
                System.out.println("write error");
            }finally {
                writer.close();
            }
            count++;
            System.out.println("read : " + count);
        }
        inputStream.close();
        System.out.println("file is spilt over,spilt : " + count);
    }
}
