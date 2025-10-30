import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File

@Suppress("unused")
class WordleTest : StringSpec({

    "isValid should be true for a valid 5-letter word" {
        val result = isValid("HELLO")
        result shouldBe true }

    "isValid should be false for a word with the wrong length" {
        val result1 = isValid("SMOL")
        val result2 = isValid("LONGER")
        result1 shouldBe false
        result2 shouldBe false }

    "isValid should be false for a word with numbers" {
        val result = isValid("HE110")
        result shouldBe false }

    "readWordList should be reading the words from the file" {
        val words = readWordList("/workspaces/comp2850/portfolio/wordle/data/words.txt")
        words.size shouldBe 2315
        words[0] shouldBe "ABACK"
        words[1] shouldBe "ABASE" }

    "readWordList should convert words to uppercase" {
        val words = readWordList("/workspaces/comp2850/portfolio/wordle/data/words.txt")
        words[0] shouldBe "ABACK"
        val allUppercase = words.all { word -> word == word.uppercase() }
        allUppercase shouldBe true }

    "pickRandomWord should return a word from the list" {
        val words = readWordList("/workspaces/comp2850/portfolio/wordle/data/words.txt")
        val originalSize = words.size
        val chosen = pickRandomWord(words)
        chosen.length shouldBe 5
        val allLetters = chosen.all { it.isLetter() }
        allLetters shouldBe true }

    "pickRandomWord should remove the word from the list" {
        val words = readWordList("/workspaces/comp2850/portfolio/wordle/data/words.txt")
        val originalSize = words.size
        val chosen = pickRandomWord(words)
        words.size shouldBe (originalSize - 1)
        words.contains(chosen) shouldBe false }

    "evaluateGuess must return all 1s for winning match" {
        val matches = evaluateGuess("HELLO", "HELLO")
        matches.size shouldBe 5
        matches[0] shouldBe 1
        matches[1] shouldBe 1
        matches[2] shouldBe 1
        matches[3] shouldBe 1
        matches[4] shouldBe 1 }

    "evaluateGuess must return all 0s for no match" {
        val matches = evaluateGuess("HELLO", "WRONG")
        matches[0] shouldBe 0
        matches[1] shouldBe 0
        matches[2] shouldBe 0
        matches[3] shouldBe 0
        matches[4] shouldBe 0 }

    "evaluateGuess must return mixed results for partial match" {
        val matches = evaluateGuess("HELLO", "HELPS")
        matches[0] shouldBe 1
        matches[1] shouldBe 1
        matches[2] shouldBe 1
        matches[3] shouldBe 0
        matches[4] shouldBe 0 }
})
