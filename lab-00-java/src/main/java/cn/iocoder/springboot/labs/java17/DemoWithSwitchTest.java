package cn.iocoder.springboot.labs.java17;

/**
 * DemoWithSwitchTest - Switch 表达式
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithSwitchTest {

    public static void main(String[] args) {
        withSwitchExpression(Fruit.APPLE,4);

    }

    // switch 表达式
    private static void withSwitchExpression(Fruit fruit,Integer type) {

        if (type == 1) {
            // 将冒号（:）替换为箭头（->），并且switch表达式默认不会失败，所以不需要break
            switch (fruit) {
                case APPLE, PEAR -> System.out.println("普通水果");
                case MANGO, AVOCADO -> System.out.println("进口水果");
                default -> System.out.println("未知水果");
            }
        }

        if (type == 2) {
            // 支持返回值
            String text = switch (fruit) {
                case APPLE, PEAR -> "普通水果";
                case MANGO, AVOCADO -> "进口水果";
                default -> "未知水果";
            };
            System.out.println(text);
        }

        if (type == 3) {
            // 支持打印
            System.out.println(switch (fruit) {
                case APPLE, PEAR -> "普通水果";
                case MANGO, AVOCADO -> "进口水果";
                default -> "未知水果";
            });
        }

        if (type == 4) {
            // 关键字yield进行返回。
            String text = switch (fruit) {
                case APPLE, PEAR -> {
                    System.out.println("给的水果是: " + fruit);
                    yield "普通水果";
                }
                case MANGO, AVOCADO -> "进口水果";
                default -> "未知水果";
            };
            System.out.println(text);
        }

        if (type == 5) {
            // 冒号与关键字yield结合进行返回。
            System.out.println(switch (fruit) {
                case APPLE, PEAR:
                    yield "普通水果";
                case MANGO, AVOCADO:
                    yield "进口水果";
                default:
                    yield "未知水果";
            });
        }

    }

}
