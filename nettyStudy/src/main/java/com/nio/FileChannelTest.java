package com.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ProjectName: JavaStudy
 * ClassName: FileChannelTest
 * PackageName: com.nio
 * Description: 使用nio和aio来操作文件
 *
 * @author: wangjingbiao
 * @date: 2018-09-26 16:48
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        String filePath = "/home/wangjingbiao/workspace/ideaWorkSpace/myWorkSpace/JavaStudy/nettyStudy/src/main/resources/testFile/a.txt";
        readFileByNio(filePath);
        readFileByIo(filePath);
    }

    /**
     * 通过Nio来读取文件，数据从通道中读入缓冲区，从缓冲区写入到通道中
     * @param filePath
     * @throws IOException
     */
    public static void readFileByNio(String filePath) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(filePath, "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            //从写模式切换到读模式
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            //再从读模式转换到写模式，clear()清除所有数据，compact()清除已读数据
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        //最后补充输出一个换行
        System.out.println();
        aFile.close();
    }

    /**
     * 通过io来读取文件，数据从流读取到byte数组中
     * @param filePath
     * @throws IOException
     */
    public static void readFileByIo(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(new File(filePath));
        byte[] buf = new byte[48];

        int bytesRead = inputStream.read(buf);

        while(bytesRead != -1){
            System.out.println("Read " + bytesRead);
            for (int i = 0; i < buf.length; i++) {
                System.out.print((char) buf[i]);
            }
            bytesRead = inputStream.read(buf);
        }
        //最后补充输出一个换行
        System.out.println();
        inputStream.close();
    }
}
