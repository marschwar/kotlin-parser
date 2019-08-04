package com.github.marschwar.kotlinParser

import com.github.marschwar.kotlinParser.parser.KotlinParser
import org.antlr.v4.runtime.CharStreams
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

internal object ParserSpek : Spek({
    val subject by memoized { Parser() }

    describe("parsing a kotlin source from string") {
        lateinit var result: KotlinParser.KotlinFileContext
        val input = "data class Foo(bar:String)"

        before { result = subject.parse(CharStreams.fromString(input)) }

        it("successfully parses the code snippet") {
            assertThat(result).isNotNull
        }

        it("contains a top level type definition Foo") {
            val simpleIdentifier = result.topLevelObject().first().declaration().classDeclaration().simpleIdentifier()
            assertThat(simpleIdentifier.Identifier().symbol.text).isEqualTo("Foo")
        }

        it("identifies the type as data class") {
            val classModifiers = result.topLevelObject().first().declaration().classDeclaration().modifiers()
            assertThat(classModifiers.modifier()).anyMatch({ m -> m.classModifier()?.DATA() != null })
        }
    }
})