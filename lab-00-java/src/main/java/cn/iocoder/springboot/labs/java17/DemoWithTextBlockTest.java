package cn.iocoder.springboot.labs.java17;

/**
 * DemoWithTextBlockTest - 文本块
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithTextBlockTest {

    public static void main(String[] args) {
        withTextBlock();

    }

    // 文本块
    private static void withTextBlock() {
        String text = """
            {
              "name": "小黑说Java",
              "age": 18,
              "address": "北京市西城区"
            }
            """;
        System.out.println(text);
    }

}
