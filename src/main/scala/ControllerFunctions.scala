import javax.swing.{ImageIcon, JButton, JFrame, JLabel, JOptionPane, JPanel, JTextField}
import java.awt.{Color, FlowLayout, Graphics, Image}
import scala.collection.immutable.NumericRange
import scala.util.control.Breaks.break
import java.awt.{Color, Graphics, Graphics2D, RenderingHints, Shape}
import java.awt.geom.{Ellipse2D, Path2D}
import javax.swing.{ImageIcon, JFrame, JLabel, JPanel}
import scala.annotation.tailrec
import scala.util.Random

def isWhite(piece: String): Boolean = {
  if (piece == "white_queen" || piece == "white_king" || piece == "white_bishop" || piece == "white_rook" || piece ==
    "white_knight" || piece == "white_pawn")
    return true;
  return false
}
def blackPawnMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (i2 - i == 1) {
    if (i3 - i1 == 0) {
      if (state(i2)(i3) != "") {
        return false
      }
      return true
    }
    else if (i3 - i1 == -1 || i3 - i1 == 1) {
      if (isWhite(state(i2)(i3))) {
        return true
      }
      return false
    }
    else
      return false
  }
  else if (i2 - i == 2) {
    if (i3 == i1) {
      if (i == 1) {
        if (state(i2)(i3) == "") {
          return true
        }
        return false
      }
      else
        return false
    }
    else
      return false
  }
  false
}

def blackKnightMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (Math.abs(i - i2) == 2 && Math.abs(i1 - i3) == 1 ||
    Math.abs(i - i2) == 1 && Math.abs(i1 - i3) == 2) {
    if (state(i2)(i3) == "" || isWhite(state(i2)(i3))) {
      return true
    }
    return false
  }
  false
}

def whiteKnightMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (Math.abs(i - i2) == 2 && Math.abs(i1 - i3) == 1 ||
    Math.abs(i - i2) == 1 && Math.abs(i1 - i3) == 2) {
    if (!isWhite(state(i2)(i3))) {
      return true
    }
    return false
  }
  false
}

def whitePawnMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (i2 - i == -1) {
    if (i3 - i1 == 0) {
      if (state(i2)(i3) != "") {
        return false
      }
      return true
    }
    else if (i3 - i1 == -1 || i3 - i1 == 1) {
      if (!isWhite(state(i2)(i3)) &&
        state(i2)(i3) != "") {
        return true
      }
      return false
    }
    else
      return false
  }
  else if (i2 - i == -2) {
    if (i3 == i1) {
      if (i == 6) {
        if (state(i2)(i3) == "") {
          return true
        }
        return false
      }
      else
        return false
    }
    else
      return false
  }
  return false
}


def blackRockMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (i == i2) {
    val min = Math.min(i1, i3) + 1
    val max = Math.max(i1, i3)
    for (k <- min until max) {
      if (state(i)(k) != "") {
        println("Bazet 3nd " + i + " " + k)
        return false
      }
    }
    if (isWhite(state(i2)(i3)) || state(i2)(i3) == "") {
      return true
    }
    println("Bazet 3nd")
    return false
  }
  if (i1 == i3) {
    val min = Math.min(i2, i) + 1
    val max = Math.max(i2, i)
    for (k <- min until max) {
      if (state(k)(i1) != "") {
        println("Bazet 3nd " + k + " " + i1)
        return false
      }
    }
    if (isWhite(state(i2)(i3)) || state(i2)(i3) == "") {
      return true
    }
    println("Bazet 3nd")
    return false
  }
  return false
}

def whiteBishopMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (Math.abs(i - i2) == Math.abs(i1 - i3)) {
    val stepHor = if (i2 > i) 1 else -1
    val stepVer = if (i3 > i1) 1 else -1
    var (x, y) = (i + stepHor, i1 + stepVer)
    while (x != i2 && y != i3) {
      if (state(x)(y) != "") {
        return false
      }
      x += stepHor
      y += stepVer
    }
    if (!isWhite(state(i2)(i3))) {
      return true
    }
    return false
  }
  false
}

def blackBishopMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (Math.abs(i - i2) == Math.abs(i1 - i3)) {
    val stepHor = if (i2 > i) 1 else -1
    val stepVer = if (i3 > i1) 1 else -1
    var (x, y) = (i + stepHor, i1 + stepVer)
    while (x != i2 && y != i3) {
      if (state(x)(y) != "") {
        return false
      }
      x += stepHor
      y += stepVer
    }
    if (isWhite(state(i2)(i3)) || state(i2)(i3) == "") {
      return true
    }
    return false
  }
  false
}


def whiteKingMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if ((Math.abs(i - i2) == 1 && Math.abs(i1 - i3) == 1) ||
    (Math.abs(i - i2) == 1 && Math.abs(i1 - i3) == 0) ||
    (Math.abs(i - i2) == 0 && Math.abs(i1 - i3) == 1)) {
    if (!isWhite(state(i2)(i3))) {
      return true
    }
    else
      return false
  }
  false
}

def blackKingMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if ((Math.abs(i - i2) == 1 && Math.abs(i1 - i3) == 1) ||
    (Math.abs(i - i2) == 1 && Math.abs(i1 - i3) == 0) ||
    (Math.abs(i - i2) == 0 && Math.abs(i1 - i3) == 1)) {
    if (isWhite(state(i2)(i3)) || state(i2)(i3) == "") {
      return true
    }
    else
      return false
  }
  false
}

def whiteRockMover(i: Int, i1: Int, i2: Int, i3: Int, state: Array[Array[String]]): Boolean = {
  if (i == i2) {
    val min = Math.min(i1, i3) + 1
    val max = Math.max(i1, i3)
    for (k <- min until max) {
      if (state(i)(k) != "") {
        println("Bazet 3nd " + k + " " + i)
        return false
      }
    }
    if (!isWhite(state(i2)(i3))) {
      return true
    }
    println("Bazet 3nd")
    return false
  }
  if (i1 == i3) {
    val min = Math.min(i2, i) + 1
    val max = Math.max(i2, i)
    for (k <- min until max) {
      if (state(k)(i1) != "") {
        println("Bazet 3nd " + k + " " + i1)
        return false
      }
    }
    if (!isWhite(state(i2)(i3))) {
      return true
    }
    println("Bazet 3nd")
    return false
  }
  return false
}
def toIndices(input: String): Array[Int] = {
  val myArray: Array[Int] = new Array[Int](4)
  myArray(0) = input.charAt(0) - '1'
  myArray(1) = input.charAt(1) - 'A'
  myArray(2) = input.charAt(3) - '1'
  myArray(3) = input.charAt(4) - 'A'
  myArray
}

/** chessController */

def chessController(input: String, states: Array[Array[String]], turn: Int): (Boolean, Array[Array[String]]) = {
  if (input.length != 5 || input.charAt(2) != ' ') {
    return (false, new Array[Array[String]](8))
  }
  val indices: Array[Int] = toIndices(input)
  if (indices(0) < 0 || indices(0) > 7 || indices(1) < 0 || indices(1) > 7
    || indices(2) < 0 || indices(2) > 7 || indices(3) < 0 || indices(3) > 7) {
    return (false, new Array[Array[String]](8))
  }
  if (indices(0) == indices(2) && indices(1) == indices(3)) {
    return (false, new Array[Array[String]](8))
  }
  val from = states(indices(0))(indices(1))
  val to = states(indices(2))(indices(3))
  if (from == "") {
    return (false, new Array[Array[String]](8))
  }
  println(from)
  val parts = from.split("_")
  val color = parts(0)
  val piece = parts(1)
  println(color)
  if (turn % 2 == 1) {
    if (color == "white") {
      if (piece == "rook") {
        val valid = whiteRockMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          return (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "knight") {
        val valid = whiteKnightMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          return (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "bishop") {
        val valid = whiteBishopMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          return (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "queen") {
        val valid = (whiteBishopMover(indices(0), indices(1), indices(2), indices(3), states) ||
          whiteRockMover(indices(0), indices(1), indices(2), indices(3), states))
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          return (false, new Array[Array[String]](8))
        }

      }
      else if (piece == "king") {
        val valid = whiteKingMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          return (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "pawn") {
        val valid = whitePawnMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          return (false, new Array[Array[String]](8))
        }
      }
    }
    (false, new Array[Array[String]](8))
  } // White must play
  else {
    if (color == "black") {
      if (piece == "rook") {
        println("Ayaotellesa2elen")
        val valid = blackRockMover(indices(0), indices(1), indices(2), indices(3), states)
        println(valid)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "knight") {
        val valid = blackKnightMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "bishop") {
        val valid = blackBishopMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "queen") {
        val valid: Boolean = (blackBishopMover(indices(0), indices(1), indices(2), indices(3), states) ||
          blackRockMover(indices(0), indices(1), indices(2), indices(3), states))
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          (false, new Array[Array[String]](8))
        }

      }
      else if (piece == "king") {
        val valid = blackKingMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          (false, new Array[Array[String]](8))
        }
      }
      else if (piece == "pawn") {
        val valid = blackPawnMover(indices(0), indices(1), indices(2), indices(3), states)
        if (valid) {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
        else {
          (false, new Array[Array[String]](8))
        }
      }
    }
    (false, new Array[Array[String]](8))
  }
}

/** Checkers */

def checkersController(input: String, states: Array[Array[String]], turn: Int): (Boolean, Array[Array[String]]) = {
  if (input.length != 5 || input.charAt(2) != ' ') {
    return (false, new Array[Array[String]](8))
  }
  val indices: Array[Int] = toIndices(input)
  if (indices(0) < 0 || indices(0) > 7 || indices(1) < 0 || indices(1) > 7
    || indices(2) < 0 || indices(2) > 7 || indices(3) < 0 || indices(3) > 7) {
    return (false, new Array[Array[String]](8))
  }
  if (indices(0) == indices(2) && indices(1) == indices(3)) {
    return (false, new Array[Array[String]](8))
  }
  val from = states(indices(0))(indices(1))
  val to = states(indices(2))(indices(3))
  if (from == "") {
    return (false, new Array[Array[String]](8))
  }
  println(from)
  if (turn % 2 == 1) { // White must play
    if (from == "black") {
      return (false, new Array[Array[String]](8))
    }
    if (Math.abs(indices(0) - indices(2)) == Math.abs(indices(1) - indices(3)) && indices(2) > indices(0)) {
      if (Math.abs(indices(0) - indices(2)) == 1) {
        if (states(indices(2))(indices(3)) == "") {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
      } else if (Math.abs(indices(0) - indices(2)) == 2) {
        val stepHor = if (indices(2) > indices(0)) 1 else -1
        val stepVer = if (indices(3) > indices(1)) 1 else -1
        val x = indices(0) + stepHor
        val y = indices(1) + stepVer
        if (states(x)(y) == "black" && states(x + stepHor)(y + stepVer) == "") {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(x)(y) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        } else {
          return (false, new Array[Array[String]](8))
        }
      } else {
        return (false, new Array[Array[String]](8))
      }
    } else {
      return (false, new Array[Array[String]](8))
    }
  }
  else {
    if (from == "white") {
      return (false, new Array[Array[String]](8))
    }
    if (Math.abs(indices(0) - indices(2)) == Math.abs(indices(1) - indices(3)) && indices(2) < indices(0)) {
      if (Math.abs(indices(0) - indices(2)) == 1) {
        if (states(indices(2))(indices(3)) == "") {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        }
      } else if (Math.abs(indices(0) - indices(2)) == 2) {
        val stepHor = if (indices(2) > indices(0)) 1 else -1
        val stepVer = if (indices(3) > indices(1)) 1 else -1
        val x = indices(0) + stepHor
        val y = indices(1) + stepVer
        if (states(x)(y) == "white" && states(x + stepHor)(y + stepVer) == "") {
          val newStates = Array.ofDim[String](states.length, states(0).length)
          for (i <- 0 to 7) {
            for (j <- 0 to 7) {
              if (!((i == indices(2) && j == indices(3)) || (i == indices(0) && j == indices(1)))) {
                newStates(i)(j) = states(i)(j)
              }
            }
          }
          newStates(indices(0))(indices(1)) = ""
          newStates(x)(y) = ""
          newStates(indices(2))(indices(3)) = states(indices(0))(indices(1))
          return (true, newStates)
        } else {
          return (false, new Array[Array[String]](8))
        }
      } else {
        return (false, new Array[Array[String]](8))
      }
    } else {
      return (false, new Array[Array[String]](8))
    }
  }
  (false, new Array[Array[String]](8))
}

/** Eight Queens */
def eightQueensController(input: String, states: Array[Array[String]], turn: Int): (Boolean, Array[Array[String]]) = {
  if (input.length != 5 || input.charAt(2) != ' ') {
    return (false, new Array[Array[String]](8))
  }
  val indices: Array[Int] = Array(input.charAt(3) - '1', input.charAt(4) - 'A')
  if (indices(0) < 0 || indices(0) > 7 || indices(1) < 0 || indices(1) > 7) {
    return (false, new Array[Array[String]](8))
  }
  val parts = input.split(" ")
  val order = parts(0)


  if (order != "ON" && order != "OF") {
    return (false, new Array[Array[String]](8))
  }
  val cell = states(indices(0))(indices(1))
  if (order == "ON") {
    println("cell = " + cell)
    if (cell != ".") {
      return (false, new Array[Array[String]](8))
    }
    var newStates = Array.ofDim[String](states.length, states(0).length)
    for (i <- 0 to 7) {
      for (j <- 0 to 7) {
        if (!(i == indices(0) || j == indices(1))) {
          newStates(i)(j) = states(i)(j)
        }
        else {
          if (i == indices(0) && j == indices(1)) {
            newStates(i)(j) = "black_queen"
          } else {
            newStates(i)(j) = states(i)(j)
            newStates(i)(j) += "l"
          }
        }
      }
    }
    val row = indices(0)
    val col = indices(1)

    // Iterate up-left diagonal
    var i = row - 1
    var j = col - 1
    while (i >= 0 && j >= 0) {
      newStates(i)(j) += "l"
      i -= 1
      j -= 1
    }

    // Iterate up-right diagonal
    i = row - 1
    j = col + 1
    while (i >= 0 && j < 8) {
      newStates(i)(j) += "l"
      i -= 1
      j += 1
    }

    // Iterate down-left diagonal
    i = row + 1
    j = col - 1
    while (i < 8 && j >= 0) {
      newStates(i)(j) += "l"
      i += 1
      j -= 1
    }

    // Iterate down-right diagonal
    i = row + 1
    j = col + 1
    while (i < 8 && j < 8) {
      newStates(i)(j) += "l"
      i += 1
      j += 1
    }
    return (true, newStates)
  } else {
    if (cell != "black_queen") {
      return (false, new Array[Array[String]](8))
    } else {
      var newStates = Array.ofDim[String](states.length, states(0).length)
      for (i <- 0 to 7) {
        for (j <- 0 to 7) {
          if (!(i == indices(0) || j == indices(1))) {
            newStates(i)(j) = states(i)(j)
          }
          else {
            if (i == indices(0) && j == indices(1)) {
              newStates(i)(j) = "."
            } else {
              newStates(i)(j) = states(i)(j).dropRight(1)
            }
          }
        }
      }
      val row = indices(0)
      val col = indices(1)

      // Iterate up-left diagonal
      var i = row - 1
      var j = col - 1
      while (i >= 0 && j >= 0) {
        newStates(i)(j) = states(i)(j).dropRight(1)
        i -= 1
        j -= 1
      }

      // Iterate up-right diagonal
      i = row - 1
      j = col + 1
      while (i >= 0 && j < 8) {
        newStates(i)(j) = states(i)(j).dropRight(1)
        i -= 1
        j += 1
      }

      // Iterate down-left diagonal
      i = row + 1
      j = col - 1
      while (i < 8 && j >= 0) {
        newStates(i)(j) = states(i)(j).dropRight(1)
        i += 1
        j -= 1
      }

      // Iterate down-right diagonal
      i = row + 1
      j = col + 1
      while (i < 8 && j < 8) {
        newStates(i)(j) = states(i)(j).dropRight(1)
        i += 1
        j += 1
      }
      return (true, newStates)
    }

  }
  (false, new Array[Array[String]](8))
}

/** Tic Tac Toe */
def ticTacToeController(input: String, states: Array[Array[String]], turn: Int): (Boolean, Array[Array[String]]) = {
  val horizontalIndexes: NumericRange.Inclusive[Char] = 'A' to ('A'.toInt + 3 - 1).toChar
  val verticalIndexes: Range = 3 to 1 by -1
  if (
    input.length() != 2
      || !horizontalIndexes.contains(input.charAt(0))
      || !verticalIndexes.contains(input.charAt(1) - 48)
  ) {
    return (false, new Array[Array[String]](8))
  }

  val row = input.charAt(1) - 49
  val col = input.charAt(0) - 65

  if states(row)(col) == "" then {
    val newStates = states.clone()
    newStates(row)(col) = if turn % 2 == 1 then "X" else "O"
    return (true, newStates)
  } else
    return (false, new Array[Array[String]](8))

}
/** connect four */
def connect4Controller(input: String, states: Array[Array[String]], turn: Int): (Boolean, Array[Array[String]]) = {
  val horizontalIndexes: NumericRange.Inclusive[Char] = 'A' to ('A'.toInt + 6 - 1).toChar
  if (
    input.length() != 1
      || !horizontalIndexes.contains(input.charAt(0))
  ) {
    return (false, new Array[Array[String]](8))
  }
  val col = input.charAt(0) - 65
  var row = 5
  while (row >= 0 && states(row)(col) != "") {
    row -= 1
  }
  if (row == -1) {
    return (false, new Array[Array[String]](8))
  }
  val newStates = states.clone()
  newStates(row)(col) = if turn % 2 == 1 then "r" else "y"
  return (true, newStates)
}

/** sudoku */
def sudokuController(input: String, states: Array[Array[String]], turn: Int): (Boolean, Array[Array[String]]) = {
  val horizontalIndexes: NumericRange.Inclusive[Char] = 'A' to ('A'.toInt + 9 - 1).toChar
  val verticalIndexes: Range = 9 to 1 by -1
  if (input.charAt(2) != ' ' && input.charAt(5) != ' ') {
    return (false, new Array[Array[String]](8))
  }
  if (input.length < 5) {
    return (false, new Array[Array[String]](8))
  }
  if(input.charAt(0) == 'O' && input.charAt(1) == 'N'){
    val col = input.charAt(3) - 65
    val row = input.charAt(4) - 49
    val value = input.charAt(6).toInt - 48

    var buffer = Array.ofDim[String](9, 9)
    for (i <- 0 to 8) {
      for (j <- 0 to 8) {
        buffer(i)(j) = states(i)(j).charAt(0) + ""
      }
    }
//    println
//
//    println(input.length() != 7)
//    println(horizontalIndexes.contains(input.charAt(3)))
//    println(verticalIndexes.contains(input.charAt(4) - 48))
//    println(verticalIndexes.contains(input.charAt(6) - 48))
//    println(buffer(row)(col).equals("0"))
//    println(isValid(buffer.clone().map(_.map(_.toInt)), row, col, value))
//    for (i <- 0 to 8) {
//      for (j <- 0 to 8) {
//        print(buffer(i)(j) + " ")
//      }
//      println
//    }
//    println
    if (
      input.length() != 7
        || !horizontalIndexes.contains(input.charAt(3))
        || !verticalIndexes.contains(input.charAt(4) - 48)
        || !verticalIndexes.contains(input.charAt(6) - 48)
        || !buffer(row)(col).equals("0")
        || !isValid(buffer.clone().map(_.map(_.toInt)), row, col, value)
    ) {
      return (false, new Array[Array[String]](8))
    }


    val newStates = states.clone()
    newStates(row)(col) = input.charAt(6).toString
    return (true, newStates)
  }else if(input.charAt(0) == 'O' && input.charAt(1) == 'F'){
    val col = input.charAt(3) - 65
    val row = input.charAt(4) - 49
//    val value = input.charAt(6).toInt - 48

    if(states(row)(col).length !=1 && states(row)(col).charAt(1) == 'x'){
      return (false, new Array[Array[String]](8))
    }else{
      val newStates = states.clone()
      newStates(row)(col) = "0"
      return (true, newStates)
    }
  }
  (false, new Array[Array[String]](8))
}


/** sudoku initial generator */

def generate(): Array[Array[String]] = {
  var newStates = Array.ofDim[String](9, 9)
  val board = Array.fill(9, 9)(0)
  fillDiagonalBlocks(board)
  fillRemainingCells(board, 0, 3)
  eraseCells(board, 0, 3)
  for(i <- 0 to 8){
    for(j <- 0 to 8){
      if(board(i)(j) == 0) {
        newStates(i)(j) = board(i)(j) + ""
      }else{
        newStates(i)(j) = board(i)(j) + "x"
      }
    }
  }
  newStates
}

private def fillDiagonalBlocks(board: Array[Array[Int]]): Unit = {
  for (i <- 0 until 9 by 3) {
    fillBlock(board, i, i)
  }
}

@tailrec
private def eraseCells(board: Array[Array[Int]], row: Int, col: Int): Unit = {
  if (col == 9) {
    eraseCells(board, row + 1, 0)
  } else if (row == 9) {
    return
  } else {
    val random = Random.nextInt(2)
    if (random == 0) {
      board(row)(col) = 0
    }
    eraseCells(board, row, col + 1)
  }
}

private def fillBlock(board: Array[Array[Int]], row: Int, col: Int): Unit = {
  val values = Random.shuffle((1 to 9).toList)
  var index = 0
  for (i <- row until row + 3) {
    for (j <- col until col + 3) {
      board(i)(j) = values(index)
      index += 1
    }
  }
}

private def fillRemainingCells(board: Array[Array[Int]], row: Int, col: Int): Boolean = {
  if (col == 9) {
    fillRemainingCells(board, row + 1, 0)
  } else if (row == 9) {
    true
  } else if (board(row)(col) != 0) {
    fillRemainingCells(board, row, col + 1)
  } else {
    val values = Random.shuffle((1 to 9).toList)
    values.exists { value =>
      if (isValid(board, row, col, value)) {
        board(row)(col) = value
        if (fillRemainingCells(board, row, col + 1)) {
          true
        } else {
          board(row)(col) = 0
          false
        }
      } else {
        false
      }
    }
  }
}

private def isValid(board: Array[Array[Int]], row: Int, col: Int, value: Int): Boolean = {
  !usedInRow(board, row, value) &&
    !usedInCol(board, col, value) &&
    !usedInBlock(board, row - row % 3, col - col % 3, value)
}

private def usedInRow(board: Array[Array[Int]], row: Int, value: Int): Boolean = {
  board(row).contains(value)
}

private def usedInCol(board: Array[Array[Int]], col: Int, value: Int): Boolean = {
  board.exists(row => row(col) == value)
}

private def usedInBlock(board: Array[Array[Int]], blockStartRow: Int, blockStartCol: Int, value: Int): Boolean = {
  val blockEndRow = blockStartRow + 3 - 1
  val blockEndCol = blockStartCol + 3 - 1
  for (i <- blockStartRow to blockEndRow) {
    for (j <- blockStartCol to blockEndCol) {
      if (board(i)(j) == value) {
        return true
      }
    }
  }
  false
}
