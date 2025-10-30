import java.io.File
import kotlin.random.Random

fun isValid(word: String): Boolean {
    if (word.length != 5) {
        return false }
    for (letter in word) {
        if (!letter.isLetter()) {
            return false } }
    return true }

fun readWordList(filename: String): MutableList<String> {
    val words = mutableListOf<String>()
    val lines = File(filename).readLines()
    for (line in lines) {
        val word = line.trim()
        words.add(word.uppercase()) }
    return words }

fun pickRandomWord(words: MutableList<String>): String {
    val randomInt = Random.nextInt(words.size)
    val chosenWord = words[randomInt]
    words.removeAt(randomInt)
    return chosenWord }

fun obtainGuess(attempt: Int): String {
    while (true) {
        print("Attempt $attempt: ")
        val input = readln().uppercase()
        if (isValid(input)) {
            return input
        } else {
            println("Invalid entry, enter a 5-letter word.") } } }

fun evaluateGuess(guess: String, target: String): List<Int> {
    val matches = mutableListOf<Int>()
    for (i in 0..4) {
        if (guess[i] == target[i]) {
            matches.add(1)
        } else {
            matches.add(0) } }
    return matches }

fun displayGuess(guess: String, matches: List<Int>) {
    var display = ""
    for (i in 0..4) {
        if (matches[i] == 1) {
            display = display + guess[i]
        } else {
            display = display + "?" } }
    println(display) }
