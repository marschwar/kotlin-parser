package com.github.marschwar.kotlinParser

import org.antlr.v4.runtime.CharStreams
import kotlin.system.measureTimeMillis

fun main(vararg args: String) {
    val input = if (args.size == 0) {
        CharStreams.fromStream(System.`in`)
    } else {
        CharStreams.fromFileName(args[0])
    }

    val parser = Parser()

    val duration = measureTimeMillis {
        val kotlinFile = parser.parse(input)
        kotlinFile.children.forEach { child -> println(child::class) }
    }
    println("${duration}ms")
}