fun main() {
    println("Wordle")
    println("Guess the 5-letter word with 10 attempts.")
    println()

    val words = readWordList("/workspaces/comp2850/portfolio/wordle/data/words.txt")
    val target = pickRandomWord(words)
    val maxAttempts = 10
    var hasWon = false

    for (attempt in 1..maxAttempts) {
        val targetGuess = obtainGuess(attempt)
        val targetMatches = evaluateGuess(targetGuess, target)
        displayGuess(targetGuess, targetMatches)
        
        if (targetGuess == target) {
            println("You guessed the word!")
            hasWon = true
            break } }

    if (!hasWon) {
        println("Out of Attempts! The word was: $target") }
}
