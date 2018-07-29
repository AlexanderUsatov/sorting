package me.usatov.alexander.sorting.benchmarks

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import me.usatov.alexander.sorting.Main

class SortingTests : FreeSpec(){
    init {
        "usatovProkuratSortUsingHashMap should sort array" {
            // Given
            val array = createPreCreatedArray()
            val expectedArray = array.sortedArray()

            // When
            Main.usatovProkuratSortUsingHashMap(array)

            // Then
            array shouldBe expectedArray
        }

        "usatovProkuratSortUsingMyHashTable should sort array" {
            // Given
            val array = createPreCreatedArray()
            val expectedArray = array.sortedArray()

            // When
            Main.usatovProkuratSortUsingMyHashTable(array)

            // Then
            array shouldBe expectedArray
        }
    }

    private fun createPreCreatedArray(): Array<Int> {
        return arrayOf(720, 672, 128, 965, 694, 214, 778, 42, 893, 991)
    }
}