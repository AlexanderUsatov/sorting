package me.usatov.alexander.sorting.benchmarks

import me.usatov.alexander.sorting.Main
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole


@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@Measurement(iterations = 30, batchSize = 1)
open class CompareSortingTestMethods {
    private companion object {
        const val randomSeed = 34855347605451L
    }

    @Benchmark
    fun defaultSortOf1000Elements(blackhole: Blackhole, state: SortState) {
        blackhole.consume(state.arrayToSortWith1000Elements.sort())
    }

    @Benchmark
    fun hashMapSortOf1000Elements(blackhole: Blackhole, state: SortState) {
        blackhole.consume(Main.usatovProkuratSortUsingHashMap(state.arrayToSortWith1000Elements))
    }

    @Benchmark
    fun defaultSortOf10000Elements(blackhole: Blackhole, state: SortState) {
        blackhole.consume(state.arrayToSortWith10000Elements.sort())
    }

    @Benchmark
    fun hashMapSortOf100000Elements(blackhole: Blackhole, state: SortState) {
        blackhole.consume(Main.usatovProkuratSortUsingHashMap(state.arrayToSortWith10000Elements))
    }

    @State(Scope.Benchmark)
    open class SortState{

        @Param("348553", "65756756", "3658734", "3268345", "456455642", "54642487")
        open var seed : Int = 0

        @Setup
        open fun setUp() {
            arrayToSortWith1000Elements = generateArray(1000)
            arrayToSortWith10000Elements = generateArray(10_000)
        }

        open var arrayToSortWith1000Elements: Array<out Int> = emptyArray()
        open var arrayToSortWith10000Elements: Array<out Int> = emptyArray()

        private fun generateArray(length: Int): Array<out Int> {
            return Main.generateArr(length, 0, 10000000, seed.toLong())
        }
    }
}