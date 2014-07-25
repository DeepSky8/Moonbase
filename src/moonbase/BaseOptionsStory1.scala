package moonbase

object BaseOptionsStory1 {


case class Moonbase(val bases: List[Base], val resources: Int)
case class Base(val level: Int, val troops: Int)


def addBase(currentSave: Moonbase): Moonbase = {
  new Moonbase((new Base(1, 0) :: currentSave.bases), (currentSave.resources))
}

def income(currentSave: Moonbase): Moonbase = {
  new Moonbase(currentSave.bases, (currentSave.bases.size + currentSave.resources))
}

//
def listLowestBases(currentBases: List[Base]): List[Base] = {
(currentBases.filter(x => x == currentBases.head))
}


def listRestBases(currentBases: List[Base]): List[Base] = {
  (currentBases.filterNot(x => x == currentBases.head))
}


}