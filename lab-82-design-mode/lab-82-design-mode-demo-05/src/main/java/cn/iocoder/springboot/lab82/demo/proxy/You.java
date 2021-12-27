package cn.iocoder.springboot.lab82.demo.proxy;

/**
 * 你小子
 *
 * @author jaquez
 * @date 2021/12/27 11:35
 **/
public class You  implements GiveGift{

    BeautifulGirl mm ;

    public You(BeautifulGirl mm){
        this.mm = mm;
    }


    public void giveBook() {
        System.out.println(mm.getName() +",送你一本书....");
    }

    public void giveChocolate() {
        System.out.println(mm.getName() + ",送你一盒巧克力....");
    }

    public void giveFlowers() {
        System.out.println(mm.getName() + ",送你一束花....");
    }

}
