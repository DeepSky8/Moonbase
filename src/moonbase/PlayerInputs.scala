package moonbase

import scala.io.StdIn._

object PlayerInputs {
  def getName: String = {

    println("Welcome to Moonbase! We know you're anxious to get started right away. But first, we need to know how you would like to be addressed. What is your name?")
    val name = readLine()
    println("Welcome to Moonbase, " + name + "!")
    name
  }
}