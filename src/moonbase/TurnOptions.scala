//package moonbase
//
//object TurnOptions {
//Moonbase - story 1
//
//abstract class GameAction {
//  def execute(gameState: GameState): GameState {
//  }
//}
//
//def gameLoop(gameState: GameState): Unit = gameState match {
//    case Start => gameLoop(StartGameAction.execute(Start))
//    case s: StartAgain => gameLoop(StartAgainGameAction.execute(s))
//    case w: Wager => gameLoop(RegularTurn.execute(w))
//    case c: CallFlip => gameLoop(CallFlipGameAction.execute(c))
//    case r: Results => gameLoop(ResultsGameAction.execute(r))
//    case End => println(leave)
//  }
//
//sealed class GameState
//  case object Start extends GameState
//  case class NormalTurn
//
//object RegularTurn extends GameAction
//  def execute(gameState: GameState): GameState = {
//    println("It's your turn. You have " + bases + " functioning bases.")
//    println("Your bases have produced " + rocks + resource + ".")
//    println("This brings your stored rock total to " + storage + ".")
//   println()
//    println("A new base requires 75 " + resource + " to construct.")
//    println("An established base requires 50 " + resource + " to upgrade.")
//    println("Remember, you must upgrade all bases to the same level before you can upgrade any base a second time.")
//    println("If you do not have enough " + resource + " to construct or upgrade, you can choose to  keep digging for today.")
//    println()
//    println("Which command will you give for today? Construct, Upgrade, or Dig?")
//    val today = readline()
//    today.toLowerCase match {
//      case construct => 
//      case upgrade =>
//      case dig =>
//      case _ =>
//    }
//  }
//}
//
//
//
//
//def main(args: Array[String]): Unit = {
//
//GameLoop(Start)
//
//
//}
//
//
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
//}