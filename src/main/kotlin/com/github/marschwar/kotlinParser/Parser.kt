package com.github.marschwar.kotlinParser

import com.github.marschwar.kotlinParser.parser.KotlinLexer
import com.github.marschwar.kotlinParser.parser.KotlinParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonTokenStream

class Parser {

    fun parse(input: CharStream): KotlinParser.KotlinFileContext {
        val lexer = KotlinLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = KotlinParser(tokens)
        return parser.kotlinFile()
    }
}