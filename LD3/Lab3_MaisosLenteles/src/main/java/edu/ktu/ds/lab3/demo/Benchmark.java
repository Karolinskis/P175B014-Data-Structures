package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.HashManager;
import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.HashMapOa;
import edu.ktu.ds.lab3.utils.Map;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class FullMap {

        List<String> ids;
        List<Car> cars;
        HashMap<String, Car> carsMap;

        @Setup(Level.Iteration)
        public void generateIdsAndCars(BenchmarkParams params) {
            ids = Benchmark.generateIds(Integer.parseInt(params.getParam("elementCount")));
            cars = Benchmark.generateCars(Integer.parseInt(params.getParam("elementCount")));
        }

        @Setup(Level.Invocation)
        public void fillCarMap(BenchmarkParams params) {
            carsMap = new HashMap<>(HashManager.HashType.DIVISION);
            putMappings(ids, cars, carsMap);
        }
    }

    @Param({"10000", "20000", "40000", "80000"})
    public int elementCount;

    List<String> ids;
    List<Car> cars;

    @Setup(Level.Iteration)
    public void generateIdsAndCars() {
        ids = generateIds(elementCount);
        cars = generateCars(elementCount);
    }

    static List<String> generateIds(int count) {
        return new ArrayList<>(CarsGenerator.generateShuffleIds(count));
    }

    static List<Car> generateCars(int count) {
        return new ArrayList<>(CarsGenerator.generateShuffleCars(count));
    }

    @org.openjdk.jmh.annotations.Benchmark
    public HashMap<String,Car> HashMapPut(FullMap fullMaps) {
        HashMap<String,Car> carHashMap;
        carHashMap = new HashMap<String, Car>(HashManager.HashType.DIVISION);

        List<String> ids = fullMaps.ids;
        List<Car> cars = fullMaps.cars;

        for (int i = 0; i < ids.size(); i++) {
            carHashMap.put(ids.get(i), cars.get(i));
        }

        return carHashMap;
    }

    @org.openjdk.jmh.annotations.Benchmark
    public HashMapOa<String, Car> HashMapPutOa(FullMap fullMaps) {
        HashMapOa<String, Car> carHashMapOa;
        carHashMapOa = new HashMapOa<String, Car>(HashManager.HashType.DIVISION);

        List<String> ids = fullMaps.ids;
        List<Car> cars = fullMaps.cars;

        for (int i = 0; i < ids.size(); i++) {
            carHashMapOa.put(ids.get(i), cars.get(i));
        }

        return carHashMapOa;
    }
    

    public static void putMappings(List<String> ids, List<Car> cars, Map<String, Car> carsMap) {
        for (int i = 0; i < cars.size(); i++) {
            carsMap.put(ids.get(i), cars.get(i));
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
