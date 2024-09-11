package com.vitorota.detektrules.rules

import io.github.detekt.test.utils.KotlinCoreEnvironmentWrapper
import io.github.detekt.test.utils.createEnvironment
import io.gitlab.arturbosch.detekt.test.lintWithContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DoNotNameClassWithTest {
    private lateinit var rule: DoNotNameClassWith
    private lateinit var envWrapper: KotlinCoreEnvironmentWrapper

    @BeforeEach
    fun setup() {
        rule = DoNotNameClassWith()
        envWrapper = createEnvironment()
    }

    @AfterEach
    fun tearDown() {
        envWrapper.dispose()
    }

    @Test
    fun `test file with classes with wrong names`() {
        val findings = rule.lintWithContext(
            envWrapper.env,
            """
         package foo

         class BatataShow{}
         class ShitzuAuAu{}
         class AlbertEinstein{}

        """
        )
        assert(findings.size == 2)
        assert(findings.all { it.id == DoNotNameClassWith::class.java.simpleName })
    }
}