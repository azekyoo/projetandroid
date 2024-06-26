package fr.epf.min1.projetandroid

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)

object QuizData {
    val questions = listOf(
        QuizQuestion(
            question = "What is the capital of France?",
            options = listOf("Paris", "Berlin", "Madrid", "Rome"),
            correctAnswer = 0
        ),
        QuizQuestion(
            question = "Which country is known as the Land of the Rising Sun?",
            options = listOf("China", "South Korea", "Japan", "Thailand"),
            correctAnswer = 2
        ),
        QuizQuestion(
            question = "Who is the best Matériels mobiles teacher?",
            options = listOf("No one", "Not a single person", "Cyril Dumont", "Nobody"),
            correctAnswer = 2
        )

    )
}
