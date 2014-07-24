package moonbase

object Game {
  
//  Moonbase - story 1

def gameLoop(gameState: GameState): Unit = gameState match {
    case Start => gameLoop(StartGameAction.execute(Start))
    case o: Orientation => gameLoop(OrientationGameAction.execute(o))
    case n: NewGame => gameLoop(NewGameGameAction.execute(n))
    case c: CreateNewGame => gameLoop(RefresherGameAction.execute(c))
    case t: NormalTurn => gameLoop(NormalTurnGameAction.execute(t))
    case b: Build => gameLoop(BuildGameAction.execute(b))
    case u: Upgrade => gameLoop(UpgradeGameAction.execute(u))
    case d: Dig => gameLoop(DigGameAction.execute(d))
    case End => println()
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
  case class Upgrade(savedGame: SavedGame) extends GameState
  case class Dig(savedGame: SavedGame) extends GameState
  case class Income(savedGame: SavedGame) extends GameState
  case object End extends GameState

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
      CreateNewGame(name, new Moonbase(List((1, 0)), 50))
    }
  }
}

object RefresherGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case CreateNewGame(name, moonbase) => {
      println()
      println("Your workforce, all the non-military types, work as a single unit.")
      println("They will either build a new base for you, upgrade an existing base,")
      println("or focus on collecting more materials to complete the first two options.")
      println("Once you have built a base, it will automatically start excavating more")
      println("building materials. Each day your amount of usable material will go up.")
      println("If you set your workforce to collect usable material as well,")
      println("they will collect as much material as your lowest base collected that day.")
      println()
      NormalTurn(SavedGame(name, moonbase))
    }
  }
}


object NormalTurnGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case NormalTurn(val savedGame: SavedGame(val name, val moonbase)) => {
      println()
      println("It's a new day. You have " + NormalTurn.savedGame.moonbase.bases.size + " functioning bases.")
      println("Your bases and working unit have produced a total of " + moonbase.resources + " usable building material.")
      println()
      println("A new base requires 75 units of building material to construct.")
      println("An established base requires 50 units of building material to upgrade.")
      println("If you do not have enough units of building material to construct or upgrade, you can instruct your work unit to keep digging for today.")
      println()
      println("Which command will you give for today? Build, Upgrade, or Dig?")
     val today = readline()
      today.toLowerCase match {
        case build => Build(SavedGame)
        case upgrade => Upgrade(SavedGame)
        case dig => Dig(SavedGame)
        case _ => NormalTurn(SavedGame)
      }
    }
  }
}

object BuildGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case Build(SavedGame(name, moonbase)) => {
      Income(SavedGame(name, (BaseOptions.addBase(moonbase))))     
    }
  }
}
object UpgradeGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case Upgrade(SavedGame(name, moonbase)) => {
      Income(SavedGame(name, (BaseOptions.upgradeMoonbase(moonbase)))) 
    }
  }
}

object DigGameAction extends GameAction {
  def execute(gameState: GameState): GameState = gameState match {
    case Dig(SavedGame(name, moonbase)) => {
      Income(SavedGame(name, (BaseOptions.dig(moonbase))))
    }
  }
}



def main(args: Array[String]): Unit = {
println(PlayerInputs.getName)

GameLoop(Start)


  }
}

//
//abstract class GameAction {
//    def execute(gameState: GameState): GameState
//  }
// 
//sealed trait GameState
//  case object Start extends GameState
//  case class StartAgain(stake: Int) extends GameState
//  case class Wager(stake: Int) extends GameState
//  case class CallFlip(stake: Int, bet: Int) extends GameState
//  case class Results(list: (Boolean,Boolean, Int)) extends GameState
//  case object End extends GameState
// 
//  object StartGameAction extends GameAction {
//    def execute(gameState: GameState): GameState = {
//      println(ready)
//      val input = readLine()
//      input.toLowerCase match {
//        case "yes" => Wager(500)
//        case "no" => End
//        case _ => StartAgain(500)
//      }
//    }
//  }
//
// 
//def gameLoop(gameState: GameState): Unit = gameState match {
//    case Start => gameLoop(StartGameAction.execute(Start))
//    case s: StartAgain => gameLoop(StartAgainGameAction.execute(s))
//    case w: Wager => gameLoop(WagerGameAction.execute(w))
//    case c: CallFlip => gameLoop(CallFlipGameAction.execute(c))
//    case r: Results => gameLoop(ResultsGameAction.execute(r))
//    case End => println(leave)
//  }
//
//
//
//  object WagerGameAction extends GameAction {
//    def execute(gameState: GameState): GameState = gameState match {
//      case Wager(stake) => {
//        println(wagers)
//        print(increments25)
//        print(increments25ForYou)
//        val betVal = readLine()
//        val realBet = confirm(scala.math.abs(betVal.toInt))
//        if (!afford(realBet, stake)) {
//          println("You don't have enough credits to back that bet. You have " + stake + " credits. ")
//          Wager(stake)
//        } else {
//          CallFlip(stake, realBet)
//        }
//      }
//      case _ => Start
//    }
//  }
// 
//}