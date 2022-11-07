package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.AvlSet;
import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.BstSetIterative;
import edu.ktu.ds.lab2.utils.SortedSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class FullSet {

        Car[] cars;
        BstSet<Car> carSetBstSearch;
        BstSet<Car> carSetBst50Percentage;
        BstSet<Car> carSetBst25Percentage;
        AvlSet<Car> carSetAvlSearch;
        AvlSet<Car> carSetAvl50Percentage;
        AvlSet<Car> carSetAvl25Percentage;

        @Setup(Level.Iteration)
        public void generateElements(BenchmarkParams params) {
            cars = Benchmark.generateElements(Integer.parseInt(params.getParam("elementCount")));
        }

        @Setup(Level.Invocation)
        public void fillCarSet(BenchmarkParams params) {
            addElements(cars, carSetBstSearch = new BstSet<>(), 100);
            addElements(cars, carSetBst50Percentage = new BstSet<>(), 50);
            addElements(cars, carSetBst25Percentage = new BstSet<>(), 25);

            addElements(cars, carSetAvlSearch = new AvlSet<>(), 100);
            addElements(cars, carSetAvl50Percentage = new AvlSet<>(), 50);
            addElements(cars, carSetAvl25Percentage = new AvlSet<>(), 25);
        }
    }

    @Param({"5000", "10000", "20000", "40000", "80000"})
    public int elementCount;

    Car[] cars;

    @Setup(Level.Iteration)
    public void generateElements() {
        cars = generateElements(elementCount);
    }

    static Car[] generateElements(int count) {
        return new CarsGenerator().generateShuffle(count, 1.0);
    }

//    @org.openjdk.jmh.annotations.Benchmark
//    public BstSet<Car> addBstRecursive() {
//        BstSet<Car> carSet = new BstSet<>(Car.byPrice);
//        addElements(cars, carSet);
//        return carSet;
//    }

    // retainAll | BST
    @org.openjdk.jmh.annotations.Benchmark
    public void retainAllBst100Percentage(FullSet carSet) {
        carSet.carSetBstSearch.retainAll(carSet.carSetBstSearch);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void retainAllBst50Percentage(FullSet carSet) {
        carSet.carSetBst50Percentage.retainAll(carSet.carSetBstSearch);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void retainAllBst25Percentage(FullSet carSet) {
        carSet.carSetBst25Percentage.retainAll(carSet.carSetBstSearch);
    }



    // retainAll | AVL
    @org.openjdk.jmh.annotations.Benchmark
    public void retainAllAvl100Percentage(FullSet carSet) {
        carSet.carSetAvlSearch.retainAll(carSet.carSetAvlSearch);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void retainAllAvl50Percentage(FullSet carSet) {
        carSet.carSetAvl50Percentage.retainAll(carSet.carSetAvlSearch);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void retainAllAvl25Percentage(FullSet carSet) {
        carSet.carSetAvl25Percentage.retainAll(carSet.carSetAvlSearch);
    }



    public static void addElements(Car[] carArray, SortedSet<Car> carSet, double percentage) {
        for (int i = 0; i < carArray.length * (percentage / 100); i++) {
            carSet.add(carArray[i]);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
