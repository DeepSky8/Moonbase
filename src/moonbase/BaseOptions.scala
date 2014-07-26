package moonbase

object BaseOptions {


case class SavedGame(val name: String, val moonbase: Moonbase)
case class Moonbase(val bases: List[Base], val resources: Int)
case class Base(val level: Int, val troops: Int)


def addBase(currentSave: Moonbase): Moonbase = {
  if(currentSave.resources <= 75) {
    println("You do not have enough stored resources to build a new base.")
    currentSave
  } else {
    new Moonbase((new Base(1, 0) :: currentSave.bases), (currentSave.resources - 75))
  }
}

def upgradeMoonbase(currentSave: Moonbase): Moonbase = {
  if(currentSave.resources <= 50) {
    println("You do not have enough stored resources to upgrade a base.")
    currentSave
  } else {
    new Moonbase((upgradeLastBase(listLowestBases(currentSave.bases)) ++ (listRestBases(currentSave.bases))), (currentSave.resources - 50))
  }
}

def listLowestBases(currentBases: List[Base]): List[Base] = {
(currentBases.filter(x => x == currentBases.head.level))
}

def listRestBases(currentBases: List[Base]): List[Base] = {
  (currentBases.filterNot(x => x == currentBases.head.level))
}



def upgradeLastBase(baseList: List[Base]): List[Base] = baseList match {
// Is case Nil here an example of where I can use Option? I don't want to use //headOption, because I'm not sure that I'm using the head
  case Nil => List(upgradeBase(Base(1, 0) :: Nil))
  case h :: Nil => List(upgradeBase(h :: Nil))
  case h :: t => h :: upgradeLastBase(t)
}

def upgradeBase(oldBase: List[Base]): Base = {
  oldBase.head.copy(level = oldBase.head.level + 1)
}

def dig(moonbase: Moonbase): Moonbase = { 
  val findLowest = listLowestBases(moonbase.bases)
  findLowest match {
    case h :: t => Moonbase(moonbase.bases, moonbase.resources + (h.level*25))
  }
}

def calculateIncome(currentSave: SavedGame): Int = {
  incomePerLevel(compressLevelBases(currentSave.moonbase.bases))
}

def compressLevelBases(bases: List[Base]): List[(Int, Int)] = bases match{
  case Nil => Nil
  case h :: t => ((h.level), (listLowestBases(h :: t).size)) :: compressLevelBases(listRestBases(h :: t))
}

def incomePerLevel(bases: List[(Int, Int)]): Int = bases match {
  case Nil => 0
  case h :: t => (h._1*25*h._2) + incomePerLevel(t) 
}

}



//
// thisLevelBases(moonbase.bases).map((x, y) => y * 25 * (1 + x))
