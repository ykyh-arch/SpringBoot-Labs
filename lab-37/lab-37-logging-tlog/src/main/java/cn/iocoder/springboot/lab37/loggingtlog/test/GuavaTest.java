package cn.iocoder.springboot.lab37.loggingtlog.test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * guava 常见工具类方法测试，参考：https://mp.weixin.qq.com/s/p9ujF15wYwSq_yq00p1HFA<br/>
 * 各种使用技巧参考链接
 *
 * @author jaquez
 * @date 2023/05/04 14:26
 **/
public class GuavaTest {

    // ================================= 字符串 =============================

    // 连接器、分割器、字符串匹配器
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();
    private static final CharMatcher charMatcherDigit = CharMatcher.digit();
    private static final CharMatcher charMatcherAny = CharMatcher.any();

    // ================================= 对基本类型的扩展，guava 提供了 Bytes/Shorts/Ints/Iongs/Floats/Doubles/Chars/Booleans =============================

    public static void main(String[] args) {
        // String join = joiner.join(Lists.newArrayList("a", null, "b"));
        // System.out.println(join);
        // for (String temp : splitter.split("a,, ,b,")) {
            // System.out.println(temp);
        // }
        // System.out.println(charMatcherDigit.retainFrom("abcdefg123456"));
        // System.out.println(charMatcherAny.inRange('a','f').or(charMatcherAny.is('n')).replaceFrom("zhangfenzhe","*"));

        List<Integer> list = Ints.asList(1, 3, 5, 7, 9);
        System.out.println(Ints.join(",",1,3,1,4));

    }

}
