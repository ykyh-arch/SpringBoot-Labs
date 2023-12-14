package cn.iocoder.springboot.labs.java17;

/**
 * DemoWithRecordTest - Record 记录用于创建不可变的数据类。
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithRecordTest {

    public static void main(String[] args) {
        testPerson();

    }

    // record
    public static void testPerson() {
        Person p1 = new Person("小黑说Java", 18, "北京市西城区");
        Person p2 = new Person(null, 28, "北京市东城区");
        // 使用record定义，也可以定义成文件
        // record PersonRecord(String name,int age){}

        record PersonRecord(String name, int age) {
            // 构造方法
            PersonRecord {
                System.out.println("name " + name + " age " + age);
                if (name == null) {
                    throw new IllegalArgumentException("姓名不能为空");
                }
            }
        }

        PersonRecord p1Record = new PersonRecord(p1.getName(), p1.getAge());
        PersonRecord p2Record = new PersonRecord(p2.getName(), p2.getAge());

        System.out.println(p1Record);
        System.out.println(p2Record);
    }

}
