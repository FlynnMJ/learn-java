package com.flynn.basic.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Flynn on 2018-07-16.
 */
@FunctionalInterface
public interface TestLambda {

    public void run();

    static int addUp(Stream<Integer> numbers) {
        Integer sum = numbers.reduce((x, y) -> x + y).get();
        return sum;
    }

    public static void main(String[] args) {
        /*1.没有参数
        Runnable runnable = () -> System.out.println(11);
        new Thread(runnable).start();*/
        /*2.有参数
        ActionListener oneArgument = event2 -> System.out.println("button clicked");*/
        /*3.代码块
        Runnable runnable2 = () -> {
            System.out.println(11);
            System.out.println(22);
        };
        new Thread(runnable2).start();*/
        /*4.多个参数（隐式 一般由编译器推断）
        BinaryOperator<Long> add = (x, y) -> x + y;
        System.out.println(add.apply(2L, 3L));*/
        /*5.显式声明
        BinaryOperator<Long> binaryOperator = (Long x, Long y) -> x - y;
        System.out.println(binaryOperator.apply(3l, 1l));*/
        /*6.流 filter()
        ArrayList<Integer> places = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 100000000; i++) {
            places.add(random.nextInt(100));
        }

        long count = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i) > 50) {
                count += 1;
            }
        }
        System.out.println("花费时间：" + (System.currentTimeMillis() - startTime) + "ms" + "， 结果：" + count);
        startTime = System.currentTimeMillis();
        count = places.parallelStream().filter(x -> x > 50).count();
        System.out.println("花费时间：" + (System.currentTimeMillis() - startTime) + "ms" + "， 结果：" + count);*/
        /*
        7.流 collect()

        List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        System.out.println(list.containsAll(list));*/

        /* 8.流 map
        List<Integer> list = Stream.of(1, 2, 3, 4).map(x -> x * x).collect(Collectors.toList());
        System.out.println(list);*/

       /* 9.流 flatmap

        List<Integer> list = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(x -> x.stream()).collect(Collectors.toList());
        System.out.println(list);*/

        /*10.流 reduce
        Integer sum = Stream.of(1, 2, 3, 4).map(x -> x * x).reduce((x, y) -> x + y).get();
        System.out.println(sum);
        */
        /*11.流 max min
        long max = Stream.of(2, 1, 3, 4).max(Comparator.comparing(t -> t)).get();
        long min = Stream.of(2, 1, 3, 4).min(Comparator.comparing(t -> t)).get();
        System.out.println(max);
        System.out.println(min);
        */
        /*12.1practise 求和

        int result = addUp(Stream.of(1, 2, 3, 4));
        System.out.println(result);*/
       /*13.收集器：partitioningBy()
        List<Person> list = new ArrayList<>();
        Person p = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        p.setAge(1);
        list.add(p);
        list.add(p);
        list.add(p);
        list.add(p2);
        list.add(p3);
        Map<Boolean, List<Person>> map = list.stream().collect(Collectors.partitioningBy(Person::bigger));
        System.out.println(map);*/
       /*14.收集器：groupingBy()
        Map<Integer, List<Person>> map1 = list.stream().collect(Collectors.groupingBy(p1 -> p1.getAge()));
        System.out.println(map1);*/
        /*15.map遍历
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.forEach((k, v) -> {
            System.out.println("key: " + k + ", value: " + v);
        });*/
        /*16.1 com.flynn.basic.thread.lambda 并行 蒙特卡洛模拟法
        long startTime = System.currentTimeMillis();
        Map<Integer, Double> map = parallelDiceRolls(10000000);
        System.out.println("jdk8并行花费" + (System.currentTimeMillis() - startTime) + "ms");
        map.forEach((k, v) -> {
            System.out.println("点数: " + k + ", 概率: " + v);
        });*/
        /*16.2 threadpool 蒙特卡洛模拟法
        System.out.println("线程数:" + Runtime.getRuntime().availableProcessors());
        startTime = System.currentTimeMillis();
        Map<Integer, Double> map1 = useThreadPool(10000000, 10);
        System.out.println("threadpool花费" + (System.currentTimeMillis() - startTime) + "ms");
        map1.forEach((k, v) -> {
            System.out.println("点数: " + k + ", 概率: " + v);
        });*/
        /*16.3  fork/join 蒙特卡洛模拟法
        startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        ForkJoinTask<Map<Integer, Double>> task = new MyForkJoinTask(0, 10000000, 10000000);//参数为起始值与结束值
        Map<Integer, Double> invoke = forkJoinPool.invoke(task);

        System.out.println("fork/join 花费 " + (System.currentTimeMillis() - startTime) + "ms");
        invoke.forEach((k, v) -> {
            System.out.println("点数: " + k + ", 概率: " + v);
        });*/

        /*17  浮动平均值
        double[] a = {1.0, 2.0, 3.0};
        double[] b = simpleMovingAverage(a, 2);

        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }*/
        // System.out.println(multiplyThrough(Arrays.asList(1, 2, 3)));
        /*18 数值装箱影响性能*/
        Integer[] values = new Integer[100];
        Arrays.setAll(values, Integer::new);
        List<Integer> linkedListOfNumbers = Arrays.asList(values);
        long startTime = System.currentTimeMillis();
        int a = slowSumOfSquares(linkedListOfNumbers);
        System.out.println(a);
        System.out.println("花费时间：" + (System.currentTimeMillis() - startTime) + "ms"); //花费时间：397ms
 /*
        startTime = System.currentTimeMillis();
        int b = fastSumOfSquares(linkedListOfNumbers);
        System.out.println(b);
        System.out.println("花费时间：" + (System.currentTimeMillis() - startTime) + "ms");// 花费时间：77ms


        startTime = System.currentTimeMillis();
        int c = serialFastSumOfSquares(linkedListOfNumbers);
        System.out.println(c);
        System.out.println("花费时间：" + (System.currentTimeMillis() - startTime) + "ms");//花费时间：56ms

        startTime = System.currentTimeMillis();
        int d=traditionWays(linkedListOfNumbers);
        System.out.println(d);
        System.out.println("花费时间：" + (System.currentTimeMillis() - startTime) + "ms");//花费时间：24ms*/



    }


    public static int traditionWays(List<Integer> linkedListOfNumbers) {
        int sum = 0;
        for (Integer a : linkedListOfNumbers) {
            sum += a * a;
        }
        return sum;
    }

    public static int slowSumOfSquares(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .map(x -> x * x)
                .peek(x -> System.out.println(x))
                .reduce(0, (acc, x) -> acc + x);
    }

    public static int fastSumOfSquares(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.parallelStream()
                .mapToInt(x -> x * x)
                .sum();
    }


    public static int serialFastSumOfSquares(List<Integer> linkedListOfNumbers) {
        return linkedListOfNumbers.stream()
                .mapToInt(x -> x * x)
                .sum();
    }

    public static int multiplyThrough(List<Integer> linkedListOfNumbers) {
        return 5 * linkedListOfNumbers.parallelStream()
                .reduce(1, (acc, x) -> x * acc);
    }

    public static int sequentialSumOfSquares(IntStream range) {

        return range.parallel().map(x -> x * x)
                .sum();

      /*  return range.map(x -> x * x)
                .sum();*/

    }

    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n - 1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i -> {
                    double prefix = i == start ? 0 : sums[i - n];
                    return (sums[i] - prefix) / n;
                })
                .toArray();
    }

    static int addIntegers(List<Integer> values) {

        return values.stream().mapToInt(x -> x).sum();
    }

    static Map<Integer, Double> useThreadPool(int N, int threadNum) {

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        CompletionService<Integer> completionService = new ExecutorCompletionService(executor);
        Map<Integer, Double> map = new HashMap<>();
        double fa = 1.0 / N;

        for (int i = 0; i < N; i++) {
            completionService.submit(() -> twoDiceThrows());
        }
        for (int i = 0; i < N; i++) {
            Future<Integer> future = null;
            try {
                future = completionService.take();
                Integer result = future.get();
                map.compute(result, (x, y) -> y == null ? fa : y + fa);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return map;

    }

    static Map<Integer, Double> parallelDiceRolls(int N) {
        double fraction = 1.0 / N;
        return IntStream.range(0, N).parallel()
                .mapToObj(x -> twoDiceThrows())
                .collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(n -> fraction)));
    }

    static int twoDiceThrows() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

}

class MyForkJoinTask<T> extends RecursiveTask<Map<Integer, Double>> {

    private Integer start;//起始值
    private Integer end;//结束值
    public static final Long critical = 100000L;//临界值
    Map<Integer, Double> result = new ConcurrentHashMap<>();
    int N;
    double fa;

    public MyForkJoinTask(Integer start, Integer end, int N) {
        this.start = start;
        this.end = end;
        this.N = N;
        fa = 1.0 / N;

    }

    static int twoDiceThrows() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int firstThrow = random.nextInt(1, 7);
        int secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

    @Override
    protected Map<Integer, Double> compute() {
        //判断是否是拆分完毕
        int lenth = end - start;
        if (lenth <= critical) {
            //如果拆分完毕就相加
            Long sum = 0L;
            for (int i = start; i <= end; i++) {
                result.compute(twoDiceThrows(), (x, y) -> y == null ? fa : y + fa);
            }
            return result;
        } else {
            //没有拆分完毕就开始拆分
            int middle = (end + start) / 2;//计算的两个值的中间值
            RecursiveTask<Map<Integer, Double>> right = new MyForkJoinTask(start, middle, N);
            right.fork();//拆分，并压入线程队列
            RecursiveTask<Map<Integer, Double>> left = new MyForkJoinTask(middle + 1, end, N);
            left.fork();//拆分，并压入线程队列

            //合并
            Map<Integer, Double> map1 = right.join();
            Map<Integer, Double> map2 = left.join();
            map2.forEach((x, y) -> {
                map1.compute(x, (k, z) -> x == k ? z + y : z);
            });

            return map1;
        }
    }
}

class A implements TestLambda {
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        TestLambda a = A::new;
    }
}

class Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean bigger() {
        if (age == 1)
            return true;
        else
            return false;
    }
}