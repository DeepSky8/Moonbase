package moonbase

object GameStory1 {

def gameLoop(gameState: GameState): Unit = gameState match {
  case Start => gameLoop(StartGameAction.execute(Start))
  case o: Orientation => gameLoop(OrientationGameAction.execute(o))
  case n: NewGame => gameLoop(NewGameGameAction.execute(n))
  case t: NormalTurn => gameLoop(NormalTurnGameAction.execute(t))
  case b: Build => gameLoop(BuildGameAction.execute(b))
  case i: Income => gameLoop(IncomeGameAction.execute(i))
  case e: End => println("Thank you for playing Moonbase. You ended with " + e.SavedGame.moonbase.bases.size + " bases, and " + e.SavedGame.resources + " resources. Congratulations!.")
}

case class SavedGame(val name: String, val moonbase: Moonbase)
case class Moonbase(val bases: List[Base], val resources: Int)
case class Base(val level: Int, val troops: Int)

abstract class GameAction {
  def execute(gameState: GameState): GameState {
  }
}

sealed class GameState
  case object Start extends GameState
  case class Orientation(name: String) extends GameState
  case class NewGame(name: String) extends GameState
  case class CreateNewGame(name: String, moonbase: Moonbase) extends GameState
  case class Refresher(name: String, moonbase: Moonbase) extends GameState
  case class NormalTurn(savedGame: SavedGame) extends GameState
  case class Build(savedGame: SavedGame) extends GameState
  case class Income(savedGame: SavedGame) extends GameState
  case class End(savedGame: SavedGame) extends GameState

object StartGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case Start => {
      println()
      println("Welcome to Moonbase! We know you're anxious to get started right away. But first, we need to know how you would like to be addressed. What is your name?")
      val name = readLine()
      println("Welcome to Moonbase, " + name + "!")
      Orientation(name)
    }
  }
}

object OrientationGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case Orientation(name) => {
      println()
      println("First off, a look around the neighborhood. There is not much to see, yet.")
      println("But that's a good thing! You have plenty of space to expand your operation.")
      println("Time here on the moon moves in one-day increments.")
      println("Each day, you have to choose what to do with your workforce.")
      NewGame(name)
    }
  }
}

object NewGameGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case NewGame(name) => {
      NormalTurn(SavedGame(name, new Moonbase(List(1, 0), 50))
    }
  }
}


object NormalTurnGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case NormalTurn(val savedGame: SavedGame(val name, val moonbase)) => {
      println()
      println("It's a new day. You have " + NormalTurn.savedGame.moonbase.bases.size + " functioning bases.")
      println("You have " + NormalTurn.savedGame.moonbase.resources + " resources.")
      println()
      println("Do you want to build a new base? Or would you like to end? Type either Yes or End.")
      val today = readline()
      today.toLowerCase match {
        case yes => Build(SavedGame)
        case end => End(SavedGame)
        case no => NormalTurn(SavedGame)
        case _ => NormalTurn(SavedGame)
      }
    }
  }
}

object BuildGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case Build(SavedGame(name, moonbase)) => {
      Income(SavedGame(name, (BaseOptionsStory1.addBase(moonbase))))
    }
  }
}
object IncomeGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case Income(SavedGame(name, moonbase)) => {
      NormalTurn(SavedGame(name, (BaseOptionsStory1.income(moonbase)))
    }
  }
}


  def main(args: Array[String]): Unit = {

    gameLoop(Start)

  }

}