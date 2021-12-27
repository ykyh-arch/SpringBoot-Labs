package cn.iocoder.springboot.lab82.demo.prototype;

/**
 * 客户端测试类
 *
 * @author jaquez
 * @date 2021/12/27 10:52
 **/
public class Client {

    public static void main(String[] args) {
        // 原型 A 对象
        Resume a = new Resume("小李子");
        a.setPersonInfo("2.16", "男", "XX大学");
        a.setWorkExperience("2012.09.05", "XX科技有限公司");

        // 克隆 B 对象
        Resume b = (Resume) a.clone();

        // 输出 A 和 B 对象
        System.out.println("A 对象的信息为：\n");
        a.display();
        System.out.println("B 对象的信息为：\n");
        b.display();

        /*
         * 测试 A==B?
         * 对任何的对象x，都有x.clone() !=x，即克隆对象与原对象不是同一个对象
         */
        System.out.print("A 对象与 B 对象一样吗？\n");
        System.out.println(a == b);

        /*
         * 对任何的对象x，都有x.clone().getClass()==x.getClass()，即克隆对象与原对象的类型一样。
         */
        System.out.print("A 对象与 B 对象的类类型一样吗?\n");
        System.out.println(a.getClass() == b.getClass());
    }

}
