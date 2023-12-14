package cn.iocoder.springboot.labs.java17;

import java.util.List;
import java.util.stream.Stream;

/**
 * DemoWithStreamTest - Stream.toList() 支持
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithStreamTest {

    public static void main(String[] args) {
        oldStyle();
    }

    private static void oldStyle() {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        // List<String> stringList =  stringStream.collect(Collectors.toList());
        List<String> stringList =  stringStream.toList();
        for(String s : stringList) {
            System.out.println(s);
        }
    }

}
