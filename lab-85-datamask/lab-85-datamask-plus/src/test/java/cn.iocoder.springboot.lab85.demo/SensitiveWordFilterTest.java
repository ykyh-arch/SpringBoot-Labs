package cn.iocoder.springboot.lab85.demo;

import cn.iocoder.springboot.lab85.demo.utils.SensitiveWordFilter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Set;

/**
 * 敏感词库脱敏
 *
 * @author Jaquez
 * @date 2022/09/06 13:56
 */
@Slf4j
public class SensitiveWordFilterTest {

    @Test
    @SneakyThrows
    public void test(){
        long beginTime = System.currentTimeMillis();
        File file = ResourceUtils.getFile("classpath:SensitiveWord.txt");
        SensitiveWordFilter filter = new SensitiveWordFilter(file.getPath());
        log.info("词汇中敏感词的数量：" + filter.sensitiveWordMap.size());
        String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        log.info("待检测语句字数：" + string.length());

        Set<String> set = filter.getSensitiveWord(string, 2);
        log.info("检测语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
        log.info("修正后的内容：" + filter.replaceSensitiveWord(string,2,"*"));
        long endTime = System.currentTimeMillis();
        log.info("总共消耗时间为：" + (endTime - beginTime));
    }
}
