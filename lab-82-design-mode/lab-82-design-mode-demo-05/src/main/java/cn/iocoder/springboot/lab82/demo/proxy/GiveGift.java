package cn.iocoder.springboot.lab82.demo.proxy;

/**
 * 案例：读大学的时候都追过女生吧！某天你看到一位美女，一见钟情，心里发誓要她做你女朋友。但是你想这样直接上去可能会唐突了。于是你采用迂回政策，先和她室友搞好关系，然后通过她室友给她礼物，然后……。
 *
 * 抽象主题，送礼物接口
 *
 * @author Jaquez
 * @date 2021/12/27 11:31
 */
public interface GiveGift {
    /**
     * 送花
     */
    void giveFlowers();

    /**
     * 送巧克力
     */
    void giveChocolate();

    /**
     * 送书
     */
    void giveBook();
}
