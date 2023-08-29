package cn.iocoder.springboot.lab73.eldemo;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.*;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.*;

/**
 * ElDemoTest
 * El 参考自：https://mp.weixin.qq.com/s/U2fT4Pxpg1oyDm0o3sUTmQ
 *
 * @author jaquez
 * @date 2023/06/19 15:16
 **/
public class ElDemoTest {

    @Test
    public void test1() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        System.out.println(expression.getValue(context));
    }

    @Test
    public void test2() {
        ExpressionParser parser = new SpelExpressionParser();
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }
            @Override
            public String getExpressionPrefix() {
                return "#{";
            }
            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "#{'Hello '}#{'World!'}";
        Expression expression = parser.parseExpression(template, parserContext);
        System.out.println(expression.getValue());
    }

    @Test
    public void test3() {
        // 字面量表达式
        ExpressionParser parser = new SpelExpressionParser();

        String str1 = parser.parseExpression("'Hello World!'").getValue(String.class);
        int int1 = parser.parseExpression("1").getValue(Integer.class);
        long long1 = parser.parseExpression("-1L").getValue(long.class);
        float float1 = parser.parseExpression("1.1").getValue(Float.class);
        double double1 = parser.parseExpression("1.1E+2").getValue(double.class);
        int hex1 = parser.parseExpression("0xa").getValue(Integer.class);
        long hex2 = parser.parseExpression("0xaL").getValue(long.class);
        boolean true1 = parser.parseExpression("true").getValue(boolean.class);
        boolean false1 = parser.parseExpression("false").getValue(boolean.class);
        Object null1 = parser.parseExpression("null").getValue(Object.class);

        System.out.println("str1=" + str1);
        System.out.println("int1=" + int1);
        System.out.println("long1=" + long1);
        System.out.println("float1=" + float1);
        System.out.println("double1=" + double1);
        System.out.println("hex1=" + hex1);
        System.out.println("hex2=" + hex2);
        System.out.println("true1=" + true1);
        System.out.println("false1=" + false1);
        System.out.println("null1=" + null1);
    }

    @Test
    public void test4() {

        // 算数运算表达式、关系表达式、逻辑表达式
        ExpressionParser parser = new SpelExpressionParser();
        int result1 = parser.parseExpression("1+2-3*4/2").getValue(Integer.class);//-3
        int result2 = parser.parseExpression("4%3").getValue(Integer.class);//1
        int result3 = parser.parseExpression("2^3").getValue(Integer.class);//8
        boolean v1 = parser.parseExpression("1>2").getValue(boolean.class);
        boolean between1 = parser.parseExpression("1 between {1,2}").getValue(boolean.class);

        boolean result4 = parser.parseExpression("2>1 and (!true or !false)").getValue(boolean.class);
        boolean result5 = parser.parseExpression("2>1 && (!true || !false)").getValue(boolean.class);
        boolean result6 = parser.parseExpression("2>1 and (NOT true or NOT false)").getValue(boolean.class);
        boolean result7 = parser.parseExpression("2>1 && (NOT true || NOT false)").getValue(boolean.class);
        System.out.println("v1=" + v1);
        System.out.println("between1=" + between1);

    }

    @Test
    public void test5() {
        // 类类型表达式
        ExpressionParser parser = new SpelExpressionParser();
        // java.lang包类访问
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(result1);

        // 其他包类访问
        String expression2 = "T(cn.iocoder.springboot.lab73.eldemo.ElDemoTest)";
        Class<ElDemoTest> value = parser.parseExpression(expression2).getValue(Class.class);
        System.out.println(value == ElDemoTest.class);

        // 类静态字段访问
        int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        System.out.println(result3 == Integer.MAX_VALUE);

        // 类静态方法调用
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        System.out.println(result4);
    }

    @Test
    public void test6() {
        // 类实例化、instanceof 表达式
        ExpressionParser parser = new SpelExpressionParser();
        String result1 = parser.parseExpression("new String('路人甲java')").getValue(String.class);
        System.out.println(result1);

        Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
        System.out.println(result2);

        Boolean value = parser.parseExpression("'路人甲' instanceof T(String)").getValue(Boolean.class);
        System.out.println(value);
    }

    @Test
    public void test7() {

        // 变量的定义及引用
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", "路人甲java");
        context.setVariable("lesson", "Spring系列");

        // 获取name变量，lesson变量
        String name = parser.parseExpression("#name").getValue(context, String.class);
        System.out.println(name);
        String lesson = parser.parseExpression("#lesson").getValue(context, String.class);
        System.out.println(lesson);

        // StandardEvaluationContext构造器传入root对象，可以通过#root来访问root对象
        context = new StandardEvaluationContext("我是root对象");
        String rootObj = parser.parseExpression("#root").getValue(context, String.class);
        System.out.println(rootObj);

        // #this用来访问当前上线文中的对象
        String thisObj = parser.parseExpression("#this").getValue(context, String.class);
        System.out.println(thisObj);

    }

    @Test
    public void test8() throws NoSuchMethodException {
        // 自定义函数
        // 定义2个函数,registerFunction和setVariable都可以，不过从语义上面来看用registerFunction更恰当
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt1", parseInt);
        context.setVariable("parseInt2", parseInt);

        ExpressionParser parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("#parseInt1('3')").getValue(context, int.class));
        System.out.println(parser.parseExpression("#parseInt2('3')").getValue(context, int.class));

        String expression1 = "#parseInt1('3') == #parseInt2('3')";
        boolean result1 = parser.parseExpression(expression1).getValue(context, boolean.class);
        System.out.println(result1);
    }

    @Test
    public void test9() {
        // 表达式赋值
        Object user = new Object() {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "$classname{" +
                        "name='" + name + '\'' +
                        '}';
            }
        };
        {
            // user为root对象
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext(user);
            parser.parseExpression("#root.name").setValue(context, "路人甲java");
            System.out.println(parser.parseExpression("#root").getValue(context, user.getClass()));
        }
        {
            // user为变量
            ExpressionParser parser = new SpelExpressionParser();
            EvaluationContext context = new StandardEvaluationContext();
            context.setVariable("user", user);
            parser.parseExpression("#user.name").setValue(context, "路人甲java");
            System.out.println(parser.parseExpression("#user").getValue(context, user.getClass()));
        }
    }

    public static class Car {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class User {
        private Car car;

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        @Override
        public String toString() {
            return "User{" +
                    "car=" + car +
                    '}';
        }
    }

    @Test
    public void test10() {
        // 对象属性存取及安全导航表达式
        User user = new User();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", user);

        ExpressionParser parser = new SpelExpressionParser();
        // 使用.符号，访问user.car.name会报错，原因：user.car为空
        try {
            System.out.println(parser.parseExpression("#user.car.name").getValue(context, String.class));
        } catch (EvaluationException | ParseException e) {
            System.out.println("出错了：" + e.getMessage());
        }
        // 使用安全访问符号?.，可以规避null错误
        System.out.println(parser.parseExpression("#user?.car?.name").getValue(context, String.class));

        Car car = new Car();
        car.setName("保时捷");
        user.setCar(car);

        System.out.println(parser.parseExpression("#user?.car?.toString()").getValue(context, String.class));

    }

    @Test
    public void test11() {
        // Bean 引用
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        User user = new User();
        Car car = new Car();
        car.setName("保时捷");
        user.setCar(car);
        factory.registerSingleton("user", user);

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(factory));

        ExpressionParser parser = new SpelExpressionParser();
        User userBean = parser.parseExpression("@user").getValue(context, User.class);
        System.out.println(userBean);
        System.out.println(userBean == factory.getBean("user"));
    }

    @Test
    public void test12() {
        // 内联List
        ExpressionParser parser = new SpelExpressionParser();
        // 将返回不可修改的空List
        List<Integer> result2 = parser.parseExpression("{}").getValue(List.class);
        // 对于字面量列表也将返回不可修改的List
        List<Integer> result1 = parser.parseExpression("{1,2,3}").getValue(List.class);
        // Assert.assertEquals(new Integer(1), result1.get(0));
        try {
            result1.set(0, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 对于列表中只要有一个不是字面量表达式，将只返回原始List，
        // 不会进行不可修改处理
        String expression3 = "{{1+2,2+4},{3,4+4}}";
        List<List<Integer>> result3 = parser.parseExpression(expression3).getValue(List.class);
        result3.get(0).set(0, 1);
        System.out.println(result3);
        // 声明二维数组并初始化
        int[] result4 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);
        System.out.println(result4[1]);
        // 定义一维数组并初始化
        int[] result5 = parser.parseExpression("new int[1]").getValue(int[].class);
        System.out.println(result5[0]);

    }

    @Test
    public void test13() {
        // 集合、字典元素的访问
        ExpressionParser parser = new SpelExpressionParser();
        // SpEL内联List访问
        int result1 = parser.parseExpression("{1,2,3}[0]").getValue(int.class);

        // SpEL目前支持所有集合类型的访问
        Collection<Integer> collection = new HashSet<Integer>();
        collection.add(1);
        collection.add(2);

        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("collection", collection);
        int result2 = parser.parseExpression("#collection[1]").getValue(context2, int.class);

        // SpEL对Map字典元素访问的支持
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);

        EvaluationContext context3 = new StandardEvaluationContext();
        context3.setVariable("map", map);
        int result3 = parser.parseExpression("#map['a']").getValue(context3, int.class);

    }

    @Test
    public void test14() {
        // 列表list，字典map，数组元素修改
        ExpressionParser parser = new SpelExpressionParser();

        // 修改list元素值
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);

        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("collection", list);
        parser.parseExpression("#collection[1]").setValue(context1, 4);
        int result1 = parser.parseExpression("#collection[1]").getValue(context1, int.class);
        System.out.println(result1);

        // 修改map元素值
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        parser.parseExpression("#map['a']").setValue(context2, 4);
        Integer result2 = parser.parseExpression("#map['a']").getValue(context2, int.class);
        System.out.println(result2);

    }

    @Test
    public void test15() {
        // 集合投影
        ExpressionParser parser = new SpelExpressionParser();

        // 1.测试集合或数组
        List<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(5);
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("list", list);
        Collection<Integer> result1 = parser.parseExpression("#list.![#this+1]").getValue(context1, Collection.class);
        result1.forEach(System.out::println);

        System.out.println("------------");
        // 2.测试字典
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        List<Integer> result2 = parser.parseExpression("#map.![value+1]").getValue(context2, List.class);
        result2.forEach(System.out::println);

    }

    @Test
    public void test16() {
        // 集合选择
        ExpressionParser parser = new SpelExpressionParser();

        // 1.测试集合或数组
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(7);
        EvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("list", list);
        Collection<Integer> result1 = parser.parseExpression("#list.?[#this>4]").getValue(context1, Collection.class);
        result1.forEach(System.out::println);

        System.out.println("------------");

        // 2.测试字典
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        EvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("map", map);
        Map<String, Integer> result2 = parser.parseExpression("#map.?[key!='a']").getValue(context2, Map.class);
        result2.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        System.out.println("------------");
        List<Integer> result3 = parser.parseExpression("#map.?[key!='a'].![value+1]").getValue(context2, List.class);
        result3.forEach(System.out::println);
    }

    @Test
    public void test17() {
        // 表达式模板
        // 创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 创建解析器上下文
        ParserContext context = new TemplateParserContext("%{", "}");
        Expression expression = parser.parseExpression("你好:%{#name},我们正在学习:%{#lesson}", context);

        // 创建表达式计算上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("name", "路人甲java");
        evaluationContext.setVariable("lesson", "spring高手系列!");
        // 获取值
        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);
    }

    @Test
    public void test18() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        LessonModel lessonModel = context.getBean(LessonModel.class);
        System.out.println(lessonModel); // LessonModel{desc='你好,路粉,欢迎和我一起学习java各种技术！'}

    }


}
