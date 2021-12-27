package cn.iocoder.springboot.lab82.demo.proxy;

/**
 * 客户端测试类
 *
 * @author jaquez
 * @date 2021/12/27 11:41
 **/
public class Client {

    public static void main(String[] args) {
        BeautifulGirl mm = new BeautifulGirl("小屁孩❤...");

        // 她闺蜜代理你的功能
        HerChum chum = new HerChum(mm);

        chum.giveBook();
        chum.giveChocolate();
        chum.giveFlowers();
    }

}
