package com.vitorota.detektrules

import com.vitorota.detektrules.rules.DoNotNameClassWith
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class RuleProvider : RuleSetProvider {
    // The id of your rule set
    override val ruleSetId: String = "vitor-rules"

    override fun instance(config: Config): RuleSet =
        RuleSet(
            id = ruleSetId,
            rules = listOf(DoNotNameClassWith(config))
        )
}