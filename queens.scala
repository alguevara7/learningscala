object Queens {
  
  def solveN(n : Int) : List[List[Int]] = {
    
    def solve(k: Int) : List[List[Int]] = {
      if (k==0) List(List())
      else for {  positions <- solve(k-1)
                  newPosition <- 1 to n
                  if safe(positions, newPosition) } 
                  yield positions ::: List(newPosition)
    }

    def safe(positions: List[Int], newPosition: Int) : Boolean = {
      val unsafePositions = positions.zipWithIndex.flatMap { case (column, index) => List(
                                                      column,
                                                      column - (positions.length - index),
                                                      column + (positions.length - index) ) }
      return !(unsafePositions contains newPosition)
    }
        
    solve(n)

  }

  def main(args: Array[String]) = {
    println(solveN(args(0).toInt).length)
  }
  
}
