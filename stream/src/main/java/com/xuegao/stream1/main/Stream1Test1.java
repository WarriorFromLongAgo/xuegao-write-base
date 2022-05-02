package com.xuegao.stream1.main;

import com.xuegao.stream1.river.River;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <br/> @PackageName：com.xuegao.stream1.main
 * <br/> @ClassName：Stram1Test1
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2022/5/2 22:14
 */
public class Stream1Test1 {
    public static void main(String[] args) {
        List<Integer> collect = River.of(1, 2, 3)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        System.out.println("filter:" + integer);
                        return integer > 2;
                    }
                }).collect(Collectors.toList());
        System.out.println(collect);

    }
}