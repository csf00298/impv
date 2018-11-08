package com.study.Java8.LamdaVsStream;

import com.alibaba.fastjson.JSONObject;
import com.study.Java8.prepare.DataTest;
import com.study.Java8.prepare.Person;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: Created by CaoXiaoLong on 2017/11/17.
 */
public class LamdaStreamT1 {
    @Test
    public void test01() {
        DataTest.intList.forEach(e -> System.out.println(e.intValue()));
    }

    @Test
    public void test02() {
        //filter 对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
        DataTest.intList.stream()
                .map(e -> e.intValue()).filter(f -> f < 20000)
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void test03() {
        //peek 对每个元素执行操作并返回一个新的 Stream
        DataTest.strList.stream()
                .map(e -> e.toUpperCase())
                .peek(e -> System.out.print(e + " "))
                .map(String::toLowerCase)
                .peek(e -> System.out.print(e + " "))
                .sorted()
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void test04() {
        DataTest.orderList.stream()
                .map(e -> e * e)
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void test05() {
        //flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
        DataTest.intLists.stream()
                .flatMap(list -> list.stream().filter(e -> e % 2 == 0))
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void test06() {
        String tmp = null;
        Optional<String> optional = Optional.ofNullable(tmp);
        System.out.println(optional.orElse("null..."));

        Integer length = Optional.ofNullable(tmp).map(e -> e.length()).orElse(-1);
        System.out.println(length);
    }

    @Test
    public void test07(){
        //reduce 使用

        // 字符串连接，concat = "ABCD"
        String contat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0，无初始值
        Double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce( Double::min).get();
        //求和 初始值为10
        Integer sum = Stream.of(1, 2, 3, 4).reduce(10, Integer::sum);

        // 过滤，字符串连接，concat = "ace"
        String contatStr = Stream.of("a,", "B,", "c,", "D,", "e,", "F,")
                .map(e-> e.substring(0,e.indexOf(",")))
                .filter(e -> e.compareTo("Z") > 0)
                .reduce(String::concat).get();

        //limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素
        List<Integer> collect = DataTest.intLists.stream()
                .flatMap(list -> list.stream())
                .limit(5)
                .skip(2)
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        //allMatch：Stream 中全部元素符合传入的 predicate，返回 true
        //anyMatch 只要有一个元素符合  noneMatch 没有一个元素符合
        boolean flag = DataTest.orderList.stream().allMatch(e -> e > 100);
        System.out.println("contat "+contat+" minValue "+minValue+" sum "+sum+" contatStr "
                +contatStr+" collect "+collect +" flag "+flag);
    }


    @Test
    public void test08(){
        //迭代控制,随机数生成
        Stream.iterate(0,n -> n+2).limit(10).forEach(System.out::println);

        Stream.generate(new Random()::nextInt).limit(10).forEach(System.out::println);

        Stream.generate(()-> System.nanoTime()%100).limit(10).forEach(System.out::println);

    }


    class PersonSupplier implements Supplier<Person> {
        private int index = 0;
        private Random random = new Random();

        @Override
        public Person get() {
            return new Person(index++,"StormTestUser" + index, random.nextInt(100));
        }
    }


    @Test
    public void test09(){
        //groupingBy  生成对象并 按年龄分组
        Map<Integer, List<Person>> collect = Stream
                .generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.groupingBy(Person::getAge));

        for (Map.Entry<Integer, List<Person>> persons : collect.entrySet()) {
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
        }


        // partitioningBy 筛选并分组  partitioningBy 筛选条件 并分成两个集合
        Map<Boolean, List<Person>> childrenMap = Stream
                .generate(new PersonSupplier())
                .limit(100)
                .collect(Collectors.partitioningBy(e -> e.getAge() < 18));
        System.out.println("Children number: " + childrenMap.get(true).size());
        System.out.println("Adult number: " + childrenMap.get(false).size());
    }

    @Test
    public void test10(){
        //对主叫、被叫进行统一排序 取出联系最密切的前10个号码
        List<JSONObject> formalData = JSONObject.parseObject(DataTest.telJsonStr)
                .getJSONArray("details").toJavaList(JSONObject.class);

        List<Map.Entry<String, Long>> collect = formalData.stream()
                .filter(e -> Integer.parseInt(e.getString("callDuration")) > 0
                        && StringUtils.isNotBlank(e.getString("callDuration")))
                //同时从流中一次性取出两种数据 并压平到一个List<String> (所有的主叫、被叫手机号)
                .flatMap(e -> Stream.of(e.getString("calledNum"), e.getString("callingNum")))
                .filter(StringUtils::isNotEmpty)

                //以List的元素(手机号码)为key 出现的次数为value 构成Map
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream() //遍历Map的Value
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())//对Map的value降序排序
                .limit(10).collect(Collectors.toList());

        System.out.println(collect);

    }
}
