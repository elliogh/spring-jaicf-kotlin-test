package com.justai.jaicf.spring.services


import com.justai.jaicf.spring.Parser
import com.justai.jaicf.spring.Storage
import kotlin.collections.ArrayList

class CheckService {
//    listOf("1", "3", "2", "1", "1", "2", "2", "3", "1", "1", "2", "3", "2", "1", "1", "3", "3", "3", "1", "1")

    private val storage = Storage(
        listOf(
            "Кто является создателем Kotlin?",
            "Какой из нижеприведенных вариантов используется для обработки исключений типа Nothing?",
            "По умолчанию, классы в Kotlin являются:",
            "Что из этого в настоящее время не поддерживается в Kotlin?",
            "Какое выражение Kotlin эквивалентно int x = a ? b : c из Java?",
            "Что применимо для следующего объявления класса? class Person (val name: String)",
            "Есть ли у Kotlin примитивные типы данных, такие как int, long, float?",
            "Что такое to в данном примере: val test = 33 to 42",
            "Какое из объявлений функций является валидным?",
            "В чем ключевое отличие Iterable<T> и Sequence<T> в Kotlin?",
            "Чего не предлагает dataclass?",
            "В чем разница между var a: String? = \"Aboba\" и var b: String = \"Aboba\"",
            "Как в Kotlin правильно объявить переменную целочисленного типа?",
            "Какой тип у arr? val arr = arrayOf(1, 2, 3)",
            "Для чего нужен оператор !!",
            "Укажите правильный синтаксис для преобразования строки “42” в long",
            "Что такое корутины (coroutines)?",
            "Что делает этот код foo()()",
            "Совместим ли Kotlin с Java?",
            "В чем разница между val и var в Kotlin?"
        ),
        mapOf(
            0 to arrayOf(
                "JetBrains",
                "Google",
                "Microsoft"),
            1 to arrayOf(
                "Лямбда-функция (Lambda functoin)",
                "Изолированные классы (Sealed Class)",
                "Элвис-оператор (Elvis Operator)"),
            2 to arrayOf(
                "Публичными",
                "Финализированными",
                "Абстрактными"),
            3 to arrayOf(
                "LLVM",
                "JVM",
                ".NET CLR"),
            4 to arrayOf(
                "val x = if (a) b else c",
                "val x = a ? b : c",
                "val x = a ?: b, c"),
            5 to arrayOf(
                "Он package-private",
                "Он public",
                "У него приватное свойство \"name\""),
            6 to arrayOf(
                "Нет, Kotlin не имеет и не использует примитивные типы данных.",
                "Нет, не на уровне языка. Но компилятор Kotlin использует примитивы JVM для лучшей производительности.",
                "Да, Kotlin в этом отношении похож на Java."),
            7 to arrayOf(
                "Ключевое слово для создания диапазона от 33 до 42",
                "Ключевое слово Kotlin для создания пары (33, 42)",
                "Инфиксная функция, создающая пару (33, 42)"),
            8 to arrayOf(
                "fun sum(a: Int, b: Int): Int",
                "int sum(int a, int b)",
                "int sum(a: Int, b: Int)"),
            9 to arrayOf(
                "Последовательности обрабатываются лениво, итераторы жадно",
                "Последовательности обрабатываются по очереди, итераторы параллельно (многопоточно)",
                "Iterable<T> работает только с immutable коллекциями, Sequence<T> применим к mutable"),
            10 to arrayOf(
                "Авто-генерируемый метод toString()",
                "Автоматическое преобразование из/в JSON",
                "Авто-генерируемые методы hashCode() и equals()"),
            11 to arrayOf(
                "a является volatile, как в Java",
                "b является final и не может быть изменено",
                "b никогда не сможет стать null"),
            12 to arrayOf(
                "var i : int = 42",
                "var i : Int = 42",
                "int i = 42"),
            13 to arrayOf(
                "Array<Int>",
                "IntArray",
                "Int[]"),
            14 to arrayOf(
                "Он преобразует любое значение в ненулевой тип и выбрасывает исключение, если значение равно null",
                "Он возвращает левый операнд, если он не равен null, иначе возвращает правый операнд",
                "Это оператор модуля, аналог % в Java"),
            15 to arrayOf(
                "val l: Long = (Long)\"42\"",
                "val l: Long = Long.parseLong(\"42\")",
                "val l: Long = \"42\".toLong()"),
            16 to arrayOf(
                "Автоматически сгенерированные методы hashCode() и equals() в data classes.",
                "Функции, которые принимают другие функции в качестве аргументов или возвращают их.",
                "Штуки, обеспечивающие асинхронный код без блокировки потока."),
            17 to arrayOf(
                "Создает двумерный массив",
                "Не скомпилируется",
                "Вызывает функцию, которая вернется после вызова foo"),
            18 to arrayOf(
                "Kotlin может легко вызвать Java код и наоборот",
                "Kotlin может легко вызвать код Java, в то время как Java не может получить доступ к коду на Kotlin",
                "Kotlin предоставляет уровень совместимости для взаимодействия с Java, который становится доступен в рантайме"),
            19 to arrayOf(
                "Переменные, объявленные с помощью val, являются final, а переменные var – нет.",
                "Переменные, объявленные с помощью var, являются final, а переменные val – нет.",
                "var ограничен видимостью ближайшего функционального блока, а у val видимость заканчивается на ({ }).")
        ),
        Parser.parseCSV()[0]
//        listOf("1", "3", "2", "1", "1", "2", "2", "3", "1", "1", "2", "3", "2", "1", "1", "3", "3", "3", "1", "1")
    )


    fun score(answers: ArrayList<String>): Int {
        var score: Int = 0
        var i: Int = 0
        for (answer in answers) {
            if (answer == storage.correctAnswers[i]) {
                score++
            }
            i++
        }
        return score
    }

    fun storage(): Storage {
        return storage
    }
}