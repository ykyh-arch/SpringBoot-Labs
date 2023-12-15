package cn.iocoder.springboot.labs.java11;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * DemoWithFileApiTest - Java 11 通过提供新的重载方法而无需编写大量样板代码，引入了一种读取和写入文件的简单方法。
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithFileApiTest {

    public static void main(String[] args) {
        myMethod();
    }

    public static void myMethod(){
        try {
            Path tempFilePath = Files.writeString(
                    Path.of(File.createTempFile("tempFile", ".tmp").toURI()),
                    "Welcome to yiidian.com",
                    Charset.defaultCharset(), StandardOpenOption.WRITE);

            String fileContent = Files.readString(tempFilePath);

            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
