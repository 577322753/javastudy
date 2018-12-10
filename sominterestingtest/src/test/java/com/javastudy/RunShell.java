package com.javastudy;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ProjectName: JavaStudy
 * ClassName: RunShell
 * PackageName: com.javastudy
 * Description:
 *
 * @author: wangjingbiao
 * @date: 2018-09-28 17:23
 */
public class RunShell {
    @Test
    public void runBashShell(){
        try {
            String shpath="/home/wangjingbiao/workspace/ideaWorkSpace/myWorkSpace/JavaStudy/sominterestingtest/src/test/java/com/javastudy/a.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
