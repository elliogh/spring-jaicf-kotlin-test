package com.justai.jaicf.spring.configuration

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.jaicp.JaicpWebhookConnector
import com.justai.jaicf.channel.jaicp.channels.ChatWidgetChannel
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.jaicf.spring.scenario.MainScenario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration(
    private val botConfiguration: BotConfiguration,
) {

    @Bean
    fun botApi(mainScenario: MainScenario) = BotEngine(
        scenario = mainScenario,
        activators = arrayOf(RegexActivator, createCailaForToken(botConfiguration.accessToken)),
        conversationLoggers = arrayOf(
            JaicpConversationLogger(botConfiguration.accessToken),
            Slf4jConversationLogger()
        )
    )

    companion object {
        private fun createCailaForToken(token: String) = CailaIntentActivator.Factory(CailaNLUSettings(token))
    }
}