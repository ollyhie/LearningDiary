import kotlin.random.Random

fun main(args: Array<String>) {
    guessingGame()
}

var m: Int = 0
var n: Int = 0

fun guessingGame() {
    val numbers = generateNumbers()
    var running = true

    println("Guess the 4-digit number")
    println("You can quit the game by typing 'q'")
    //println(numbers)

    while (running) {
        val input = readLine()
        m = 0
        n = 0

        if (input?.toIntOrNull() != null) {

            if (input.length != 4) {
                println("Invalid Input! Enter a 4-digit number.")
            } else {

                checkDigit(input, numbers)
                println("$n : $m")
                if (m < 4) {
                    println("Try Again!")
                } else {
                    println("Congrats!")
                }
            }

        } else if (input.equals("q")) {
            // Stops the game if the input was 'q'-uit
            println("Thanks for playing!")
            running = false
        } else {
            // Asks the user to enter valid input
            println("Invalid Input! Enter a 4-digit number.")
        }

    }
}

fun generateNumbers(): ArrayList<Int> {
    val numbers = arrayListOf<Int>()

    for (i in 0..3) {
        var number = 0
        while (number != -1) {
            number = Random.nextInt(0, 10)
            if (!numbers.contains(number)) {
                numbers.add(i, number)
                number = -1
            }
        }
    }
    return numbers
}

fun checkDigit(input: String, numbers: ArrayList<Int>) {

    for (i in 0..3) {
        val digit = input.get(i).digitToInt()

        // Checks if the digit is part of the 4-digit number
        if (numbers.contains(digit)) {

            // If the digit is at the same position in the number
            if (numbers.indexOf(digit) == i) {
                m++
            }
            // If the digit occurs more than once in the number
            if (input.indexOf(input.get(i)) != input.lastIndexOf(input.get(i))) {
                // If it's the first occurrence of this digit
                if (input.indexOf(input.get(i)) == i) {
                    n++
                }
            } else {
                n++
            }
        }
    }
}