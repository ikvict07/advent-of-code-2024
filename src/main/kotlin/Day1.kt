package org.nevertouchgrass

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs


fun getInput(l1: MutableList<Int>, l2: MutableList<Int>, filename: String = "input.txt") {
    val file =
        Files.readAllLines(Path.of("/Users/antonhorobets/IdeaProjects/advenOfCode/src/main/resources/day1/$filename"))
    file.forEach { it ->
        val (a, b) = it.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        l1.add(a)
        l2.add(b)
    }
}

fun main() {
    part2()
}

fun part2() {
    val l1 = mutableListOf<Int>()
    val l2 = mutableListOf<Int>()
    getInput(l1, l2, "input2.txt")

    val frequency = mutableMapOf<Int, Int>()

    l2.forEach {
        frequency.computeIfAbsent(it) { 0 }
        frequency[it] = frequency[it]!! + 1
    }
    println(frequency)

    var result = 0

    l1.forEach { element ->

        result += frequency.getOrDefault(element, 0) * element
    }
    println(result)
}

fun part1() {
    val l1 = mutableListOf<Int>()
    val l2 = mutableListOf<Int>()
    getInput(l1, l2)

    val indexes1 = l1.mapIndexed { index, it ->
        index to it
    }.toMap()
    val indexes2 = l2.mapIndexed { index, it ->
        index to it
    }.toMap()

    val pq1 = indexes1.entries.sortedBy { it.value }.toList()
    val pq2 = indexes2.entries.sortedBy { it.value }.toList()

    var result = 0

    pq1.forEachIndexed { index, entry ->
        val a = pq2[index]
        result += abs(entry.value - a.value)
    }

    println(result)
}