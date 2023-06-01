import java.awt.{BorderLayout, Color, GridLayout}
import java.io.{BufferedReader, File, FileInputStream, FileWriter, InputStreamReader}
import javax.swing.{JButton, JFrame, JPanel}
import org.jpl7.*
import scala.io.Source
def eightQueensSolver(state: Array[Array[String]]): (Boolean, Array[Array[String]]) = {


  var queries = "N = 8, Qs = "
  var Qs = "["
  for (i <- 0 to 7) {
    var flag = false
    for (j <- 0 to 7) {
      if(state(j)(i).equals("black_queen")){
        flag = true
        Qs += ((j+1)+"")
      }
    }
    if(!flag) {
      Qs += "_"
    }
    if(i != 7){
      Qs += ","
    }else{
      Qs += "]"
    }
  }
  queries += Qs + ", length(Qs, N), Qs ins 1..N, safe_queens(Qs), labeling([], Qs)."
  println(queries)

    JPL.init()
  var projectDir = System.getProperty("user.dir")
  projectDir = projectDir.replace("\\", "/")
  println(projectDir)
  val q1 = new Query("consult('" + projectDir + "/src/main/scala/QueensSolver.pl').")
  System.out.println("consult " + (if (q1.hasSolution) "succeeded" else "failed"))
  println(JPL.version_string())
  val q = Query(queries)
  if(q.hasSolution){
    println(q.oneSolution())
    val qsValue = q.oneSolution().get("Qs").toString
    println(qsValue)
    // Convert Qs to an array of integers
    val qArray = parseList(qsValue)
    val qsArray = Array.ofDim[Int](state.length)
    for(i<-qArray.indices){
      qsArray(i) = qArray(i)-1
    }
    val newStates = Array.ofDim[String](state.length, state(0).length)
    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        if(!qsArray(i).equals(j)) {
          newStates(j)(i) = "."
        }else {
          newStates(j)(i) = "black_queen"
        }
      }
    }
    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        if(newStates(i)(j) == "black_queen"){
          for(x <- 0 to 7){
            for(y <- 0 to 7){
              if(!(x == i && y == j)) {
                if (x == i || j == y) {
                  newStates(x)(y) += "l"
                }
              }
            }
          }
          val row = i
          val col = j

          // Iterate up-left diagonal
          var ii = row - 1
          var jj = col - 1
          while (ii >= 0 && jj >= 0) {
            newStates(ii)(jj) += "l"
            ii -= 1
            jj -= 1
          }

          // Iterate up-right diagonal
          ii = row - 1
          jj = col + 1
          while (ii >= 0 && jj < 8) {
            newStates(ii)(jj) += "l"
            ii -= 1
            jj += 1
          }

          // Iterate down-left diagonal
          ii = row + 1
          jj = col - 1
          while (ii < 8 && jj >= 0) {

            newStates(ii)(jj) += "l"
            ii += 1
            jj -= 1
          }

          // Iterate down-right diagonal
          ii = row + 1
          jj = col + 1
          while (ii < 8 && jj < 8) {
            newStates(ii)(jj) += "l"
            ii += 1
            jj += 1
          }
        }
      }
    }
    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        print(newStates(i)(j) + " ")
      }
      println()
    }
    return (true, newStates)

  }
  else{
    return (false, null)
  }
  (false, null)

}
def parseList(str: String): Array[Int] = {
  val list = str.split("[,\\[\\]]+").map(_.trim).filter(_.nonEmpty)
  list.map(
    n => n.toInt
  )
}

def sudokoSolver(state: Array[Array[String]]): (Boolean, Array[Array[String]]) = {

  var q = "sudoku(["
  for(i <- 0 to 8){
    q+="["
    for(j <- 0 to 8){
      if(state(i)(j).equals("0")){
        q+="_"
      }else if(state(i)(j).length == 1){
        q+=state(i)(j)
      }else if(state(i)(j).charAt(1) == 'x'){
        q+=state(i)(j).charAt(0)+""
      }


        if(j!=8){
          q+=", "
        }
    }
    q+="]"
    if(i!=8)
      q+=", "
  }
  q += "])."
  val q1 = Query("consult('C:/Users/LeNoVo/game_engine-last/src/main/scala/SudokoSolver.pl').")
  System.out.println("consult " + (if (q1.hasSolution) "succeeded" else "failed"))
  println(q)
  val query = Query(q)
  //if (query.hasNext) {
  if(query.hasSolution) {
    val filename = "example.txt"
    val file = new File(filename)
    val fullPath = file.getAbsolutePath
    val ans = Source.fromFile(fullPath).getLines().next()
    val newStates = Array.ofDim[String](state.length, state(0).length)
    val rows = ans.drop(2).dropRight(2).split("\\],\\[")
    val output = rows.map(row =>
      row.split(",")
        .map(_.trim())
        .map(_.toString)
        .toArray
    )
    for(i <- 0 to 8){
      for(j <- 0 to 8){
        if(state(i)(j).size == 1){
          newStates(i)(j) = output(i)(j)
        }else{
          newStates(i)(j) = state(i)(j)
        }
      }
    }
    return (true, newStates)
  }
  else {
    return (false, null)
  }
  (false, null)
}