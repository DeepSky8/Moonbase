package moonbase

object BaseOptions {

case class Moonbase(val bases: List[Base], val resources: Int)
case class Base(val level: Int, val troops: Int)

def addBase(currentSave: Moonbase): Moonbase = {
  new Moonbase((new Base(1, 0) :: currentSave.bases), (currentSave.resources - 75))
}


def upgradeMoonbase(currentSave: Moonbase): Moonbase = {
   new Moonbase((upgradeLastBase(listLowestBases(currentSave.bases)) ++ (listRestBases(currentSave.bases))), (currentSave.resources - 50))
}

def listLowestBases(currentBases: List[Base]): List[Base] = {
  (currentBases.filter(x => x == currentBases.head))
}

def listRestBases(currentBases: List[Base]): List[Base] = {
  (currentBases.filterNot(x => x == currentBases.head))
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
}