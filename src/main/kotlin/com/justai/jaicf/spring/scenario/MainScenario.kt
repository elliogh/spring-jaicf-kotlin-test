package com.justai.jaicf.spring.scenario


import com.justai.jaicf.builder.createModel
import com.justai.jaicf.model.scenario.Scenario
import com.justai.jaicf.reactions.buttons
import com.justai.jaicf.spring.configuration.BotConfiguration
import org.springframework.stereotype.Component
import com.justai.jaicf.spring.services.CheckService

@Component
class MainScenario(
    private val botConfiguration: BotConfiguration
): Scenario {

    override val model = createModel {
        val answers = ArrayList<String>()
        var questionNumber = 0
        val checker = CheckService()
        val questionList = checker.storage().questionList
        val answerOptions = checker.storage().answerOptions

        state("Start") {
            globalActivators {
                regex("/start")
                intent("Hello")
            }
            action {
                questionNumber = 0
                answers.clear()
                reactions.run {
                    say("Здравствуйте. Запускаю тест?")
                    buttons(
                        "Да" to "/Question/askQuestion",
                        "Нет" to "/End"
                    )
                }
            }
        }

        state("Question") {
            state("askQuestion") {

                action {
                    if (questionNumber == questionList.size) {
                        reactions.go("../../End")
                    } else {
                        reactions.run {
                            say("Вопрос №${questionNumber + 1}")
                            say(questionList[questionNumber])
                        }

                        var num = 1
                        for (option in answerOptions[questionNumber]!!) {
                            reactions.say("$num) $option")
                            num++
                        }

                        questionNumber++

                        reactions.run {
                            buttons(
                                "1",
                                "2",
                                "3"
                            )
                            changeState("../Answer", "/Question/askQuestion")
                        }
                    }
                }
            }

            state("Answer") {
                activators {
                    catchAll()
                }

                action {

                    val answer = request.input
                    if (answer == "1" || answer == "2" || answer == "3") {
                        answers.add(answer)
                        reactions.goBack()
                    } else {
                        reactions.say("Такого варианта ответа нет")
                    }
                }
            }

        }

        state("End") {
            action {
                reactions.run {
                    say("Ваш счет: ${checker.score(answers)}/${answers.size}")
                    say("Показать правильные ответы?")
                    buttons(
                        "Да" to "./Show",
                        "Нет" to "/Bye"
                    )
                }
            }
            state("Show") {
                action {
                    reactions.run {
                        say("Ваши: $answers")
                        say("Правильные: ${checker.storage().correctAnswers}")
                        go("/Bye")
                    }
                }
            }
            fallback {
                reactions.go("..")
            }
        }

        state("Bye") {
            action {
                reactions.say("До свидания!")
            }

            fallback {
                reactions.say("Мы попрощались. Уходи!")
            }
        }

        fallback {
            reactions.say("Ты чего мне пишешь?")
            reactions.go("/Start")
        }
    }
}