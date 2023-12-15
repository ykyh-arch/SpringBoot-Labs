package cn.iocoder.springboot.labs.java21;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SequencedCollection;

/**
 * DemoWithSequencedCollectionsTest -  序列集合、SequencedCollection、SequencedMap、SequencedSet
 *
 * @author fw001
 * @date 2023/12/14 10:58
 **/
public class DemoWithSequencedCollectionsTest {

    public static void main(String[] args) {
        myMethod();
    }

    public static void myMethod(){
        SequencedCollection<String> seqCollection = new ArrayList<>();
        seqCollection.add("a");
        seqCollection.add("b");
        seqCollection.add("c");

        String firstElement = seqCollection.getFirst(); // 获取第一个元素 "a"
        String lastElement = seqCollection.getLast(); // 获取最后一个元素 "c"
        Iterator<String> listIter = seqCollection.iterator(); // 获取列表迭代器
        while (listIter.hasNext()) {
            System.out.println(listIter.next()); // 遍历集合并打印每个元素
        }
    }

}
