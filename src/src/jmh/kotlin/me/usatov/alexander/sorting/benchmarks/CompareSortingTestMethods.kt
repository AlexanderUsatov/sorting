package me.usatov.alexander.sorting.benchmarks

import me.usatov.alexander.sorting.Main
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.*


@BenchmarkMode(Mode.All)
@Warmup(iterations = 10)
@Measurement(iterations = 100, batchSize = 10)
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
    fun defaultSortOf100000Elements(blackhole: Blackhole, state: SortState) {
        blackhole.consume(state.arrayToSortWith1000Elements.sort())
    }

    @Benchmark
    fun hashMapSortOf100000Elements(blackhole: Blackhole, state: SortState) {
        blackhole.consume(Main.usatovProkuratSortUsingHashMap(state.arrayToSortWith1000Elements))
    }

    @State(Scope.Benchmark)
    open class SortState{
        @Setup
        open fun setUp() {
            arrayToSortWith1000Elements = generateArray(1000)
            arrayToSortWith100000Elements = generateArray(100_000)
        }

        open var arrayToSortWith1000Elements: Array<out Int> = emptyArray()
        open var arrayToSortWith100000Elements: Array<out Int> = emptyArray()

        private fun generateArray(length: Int): Array<out Int> {
            val random = Random(randomSeed)

            return (0..length).map {
                random.nextInt()
            }.toTypedArray()
        }
    }
}