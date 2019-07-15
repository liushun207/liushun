package com.util;

import com.Bank;

import javax.validation.constraints.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * 集合流处理工具.
 * @author les
 */
public class ListStreamUtil {
    /**
     * 集合有值.
     * @param source 源
     * @return true->有值，false->null或空
     */
    public static boolean hasSize(List source) {
        return source != null && !source.isEmpty();
    }

    /**
     * 获取单元素.
     * @param <T> 源类型
     * @param source 源
     * @param predicate lambda 表达式, 如：p->"1".equalsIgnoreCase(p.getXXX())
     * @return 源类型对象 t
     */
    public static <T> T get(@NotNull List<T> source, @NotNull Predicate<? super T> predicate) {

        Optional<T> t = source.stream().filter(predicate).findFirst();

        return t.orElse(null);
    }

    /**
     * 获取集合.
     * @param <T> 源类型
     * @param source 源
     * @param predicate lambda 表达式, 如：p->"1".equalsIgnoreCase(p.getXXX())
     * @return 源类型集合 list
     */
    public static <T> List<T> find(@NotNull List<T> source, @NotNull Predicate<? super T> predicate) {
        List<T> result = source.stream().filter(predicate).collect(Collectors.toList());
        return result;
    }

    /**
     * 集合去重.
     * @param <T> 源类型
     * @param source 源
     * @param comparator the comparator 如：Comparator.comparing(Bank :: getName) Comparator.comparing(o -> o.getName() + ";" + o.getSex())
     * @param predicate lambda 表达式 如：p->"1".equalsIgnoreCase(p.getXXX())
     * @return 结果类型集合 list
     */
    public static <T> List<T> distinct(@NotNull List<T> source, @NotNull Comparator<? super T> comparator, Predicate<? super T> predicate) {
        Stream<T> stream = source.stream();

        if(predicate != null) {
            stream = stream.filter(predicate);
        }

        List<T> result = stream.collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(comparator)), ArrayList ::new));

        return result;

    }

    /**
     * 获取单字段集合.
     * @param <T> 源类型
     * @param <R> 结果类型
     * @param source 源
     * @param mapper lambda 表达式 如：R::getXXX
     * @param predicate lambda 表达式 如：p->"1".equalsIgnoreCase(p.getXXX())
     * @param distinct 是否去重 true 是
     * @return 结果类型集合 list
     */
    public static <T, R> List<R> map(@NotNull List<T> source, @NotNull Function<? super T, ? extends R> mapper, Predicate<? super T> predicate, Boolean distinct) {
        Stream<T> stream = source.stream();

        if(predicate != null) {
            stream = stream.filter(predicate);
        }

        List<R> result;

        if(distinct != null && distinct) {
            result = stream.map(mapper).distinct().collect(Collectors.toList());
        }
        else {
            result = stream.map(mapper).collect(Collectors.toList());
        }

        return result;
    }

    /**
     * 获取单字段组合字符串.
     * @param <T> 源类型
     * @param source 源
     * @param mapper lambda 表达式（字符串） 如：R::getXXX
     * @param predicate lambda 表达式 如：p->"1".equalsIgnoreCase(p.getXXX())
     * @param delimiter 连接符 默认`,`
     * @return 字符串 string
     */
    public static <T> String mapToString(@NotNull List<T> source, @NotNull Function<? super T, ? extends String> mapper, Predicate<? super T> predicate, CharSequence delimiter) {
        Stream<T> stream = source.stream();

        if(predicate != null) {
            stream = stream.filter(predicate);
        }

        if(delimiter == null) {
            delimiter = ",";
        }

        String result = stream.map(mapper).collect(Collectors.joining(delimiter));
        return result;
    }

    /**
     * 计算结果.
     * @param <T> 源类型
     * @param source 源
     * @param mapper lambda 表达式 如：R::getXXX
     * @param calculateEnum 计算枚举，求合，最大值，最小值，平均值
     * @param predicate lambda 表达式 如：p->"1".equalsIgnoreCase(p.getXXX())
     * @return double double
     */
    public static <T> Double mapToDouble(@NotNull List<T> source, @NotNull ToDoubleFunction<? super T> mapper, @NotNull CalculateEnum calculateEnum, Predicate<? super T> predicate) {
        Stream<T> stream = source.stream();

        if(predicate != null) {
            stream = stream.filter(predicate);
        }

        DoubleStream doubleStream = stream.mapToDouble(mapper);

        double result = -1D;

        switch(calculateEnum) {
            case SUM: {
                result = doubleStream.sum();
                break;
            }
            case MIN: {
                OptionalDouble optionalDouble = doubleStream.min();
                result = optionalDouble.orElse(result);
                break;
            }
            case MAX: {
                OptionalDouble optionalDouble = doubleStream.max();
                result = optionalDouble.orElse(result);
                break;
            }
            case AVERAGE: {
                OptionalDouble optionalDouble = doubleStream.average();
                result = optionalDouble.orElse(result);
                break;
            }
            default: {
                break;
            }
        }

        return result;
    }

    /**
     * The entry point of application.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {

            List<Bank> list = new ArrayList<>();

            list.add(new Bank("1", "深圳", 1));
            list.add(new Bank("1", "湖北", 1));
            list.add(new Bank("1", "湖蓝", 3));
            list.add(new Bank("1", "江上", 5));
            list.add(new Bank("2", "江上", 5));

            // System.out.println(list);

            // List<Bank> b = find(list, p->"1".equalsIgnoreCase(p.getName()));
            // b.forEach(p->{
            //     p.setAddress("测试");
            // });
            //
            // String strs = mapToString(list, Bank::getAddress, p->"1".equalsIgnoreCase(p.getName()), null);

            // 根据name,sex两个属性去重
            // List<Bank> unique = distinct(list, Comparator.comparing(Bank :: getName), null);
            List<Bank> unique = distinct(list, Comparator.comparing(p-> p.getName() + ";" +p.getAge()), null);

            // Double b = mapToDouble(list, Bank::getAge, CalculateEnum.SUM,p->"1".equalsIgnoreCase(p.getName()));

            System.out.println(unique);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
