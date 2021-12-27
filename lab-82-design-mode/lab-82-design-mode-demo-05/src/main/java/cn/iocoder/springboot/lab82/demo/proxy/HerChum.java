package cn.iocoder.springboot.lab82.demo.proxy;

/**
 * 她闺蜜
 *
 * @author jaquez
 * @date 2021/12/27 11:38
 **/
public class HerChum implements GiveGift{

    You you;

    public HerChum(BeautifulGirl mm){
        you = new You(mm);
    }

    public void giveBook() {
        you.giveBook();
    }

    public void giveChocolate() {
        you.giveChocolate();
    }

    public void giveFlowers() {
        you.giveFlowers();
    }

}
