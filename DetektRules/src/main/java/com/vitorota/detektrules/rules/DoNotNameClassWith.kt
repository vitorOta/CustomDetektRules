package com.vitorota.detektrules.rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtClass

class DoNotNameClassWith : Rule() {

    // the ideal would be to have this at the configuration, but it didn't worked
    private val namesToAvoid = listOf("Batata", "Shitzu")
    private val placeholder = "#placeholder"

    override val issue: Issue = Issue(
        id = javaClass.simpleName,
        severity = Severity.Defect,
        description = "Do not name a class using \"$placeholder\"",
        debt = Debt.FIVE_MINS
    )

    // there are lots of these visitXXX() functions, there is no docs, so you need to
    // test to find out which one will solve your problem
    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)

        // get the analyzed class name
        val className = klass.name?.takeIf { it.isNotEmpty() } ?: return
        val filteredList = namesToAvoid.filter { it.isNotEmpty() }

        if (className.isEmpty() || filteredList.isEmpty()) return

        // verification
        filteredList.forEach {
            if (className.contains(it, false)) {
                report(
                    CodeSmell(
                        issue = issue,
                        entity = Entity.from(klass),
                        message = issue.description.replace(placeholder, it)
                    )
                )
            }
        }
    }
}