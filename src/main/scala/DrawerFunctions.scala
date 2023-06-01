import java.awt.geom.Ellipse2D
import java.awt.{BorderLayout, Button, Color, Dimension, Font, Frame, Graphics, Graphics2D, GridBagLayout, GridLayout, Image, RenderingHints, Shape}
import javax.swing.{BorderFactory, ImageIcon, JButton, JFrame, JLabel, JPanel, JTextField, SwingConstants, WindowConstants}
import javax.imageio.ImageIO
import java.io.File
import javax.swing.border.EmptyBorder
import scala.collection.immutable.NumericRange


/** load image icons */
def loadImageIcon(resourcePath: String, width: Int, height: Int): ImageIcon = {
  new ImageIcon(ImageIO.read(getClass.getClassLoader.getResource(resourcePath)).getScaledInstance(width, height, 5))
}

/** chess */
def ChessDrawer(state: Array[Array[String]]): Unit = {
  removeAllFrames()
  val frame = new JFrame()
  var layout = new GridLayout(8, 8)
  val panel = new JPanel()
  panel.setLayout(layout)
  for (i <- 0 to 7) {
    for (j <- 0 to 7) {
      val color = if ((i + j) % 2 == 0) Color.WHITE else Color.GRAY
      val button = new JButton("")
      button.setBackground(color)
      panel.add(button)
      if (state(i)(j) != "") {
        button.setIcon(loadImageIcon("ChessPieces/" + state(i)(j) + ".png", 50, 50))
      } else {
        if (button.getIcon != null)
          button.setIcon(null)
      }
    }
  }
  frame.add(verticalIndex(8), BorderLayout.WEST)
  frame.add(verticalIndex(8), BorderLayout.EAST)
  frame.add(horizontalIndex(8), BorderLayout.NORTH)
  frame.add(horizontalIndex(8), BorderLayout.SOUTH)

  frame.add(panel, BorderLayout.CENTER)
  frame.setTitle("Chess Board")
  frame.pack()
  frame.setVisible(true)
}

/** checkers */
def CheckersDrawer(state: Array[Array[String]]): Unit = {
  removeAllFrames()
  val frame = new JFrame()
  var layout = new GridLayout(8, 8)
  val panel = new JPanel()
  panel.setLayout(layout)
  for (i <- 0 to 7) {
    for (j <- 0 to 7) {
      val color = if ((i + j) % 2 == 0) Color.WHITE else Color.BLACK
      val button = new JButton("")
      button.setBackground(color)
      panel.add(button)
      if (state(i)(j) != "") {
        button.setIcon(loadImageIcon("CheckersPieces/" + state(i)(j) + ".png", 50, 50))
      } else {
        if (button.getIcon != null)
          button.setIcon(null)
      }
    }
  }
  frame.add(verticalIndex(8), BorderLayout.WEST)
  frame.add(verticalIndex(8), BorderLayout.EAST)
  frame.add(horizontalIndex(8), BorderLayout.NORTH)
  frame.add(horizontalIndex(8), BorderLayout.SOUTH)

  frame.add(panel, BorderLayout.CENTER)
  frame.setTitle("Checkers Board")
  frame.pack()
  frame.setVisible(true)
}

/** eight queens */
def eightQueensDrawer(state: Array[Array[String]]): Unit = {
  removeAllFrames()
  val frame = new JFrame()
  frame.setPreferredSize(new Dimension(800, 800))
  var layout = new GridLayout(8, 8)
  val panel = new JPanel()
  panel.setLayout(layout)
  panel.setSize(new Dimension(400, 400))
  for (i <- 0 to 7) {
    for (j <- 0 to 7) {
      val color = if ((i + j) % 2 == 0) Color.WHITE else Color.GRAY
      val button = new JButton("")
      button.setBackground(color)
      panel.add(button)
      if (state(i)(j) == "black_queen") {
        button.setIcon(loadImageIcon("ChessPieces/" + state(i)(j) + ".png", 50, 50))
      }
      else {
        if (button.getIcon != null)
          button.setIcon(null)
      }
    }
  }

  frame.add(verticalIndex(8), BorderLayout.WEST)
  frame.add(verticalIndex(8), BorderLayout.EAST)
  frame.add(horizontalIndex(8), BorderLayout.NORTH)
  frame.add(horizontalIndex(8), BorderLayout.SOUTH)

  frame.add(panel, BorderLayout.CENTER)
  frame.setTitle("8 Queens Board")
  frame.pack()
  frame.setVisible(true)
}

/** create label */
def createLabel(value: Any): JLabel = new JLabel(value.toString, SwingConstants.CENTER)

/** vertical index */
def verticalIndex(rows: Int): JPanel = {
  val range: Range = 1 to rows by 1
  val panel = new JPanel()
  panel.setLayout(new GridLayout(rows, 1))
  panel.setBorder(new EmptyBorder(10, 10, 10, 10))
  range.map(i => {
    panel.add(createLabel(i))
  })
  panel
}

/** horizontal index */
def horizontalIndex(cols: Int): JPanel = {
  val range: NumericRange.Inclusive[Char] = 'A' to ('A'.toInt + cols - 1).toChar
  val panel = new JPanel()
  panel.setLayout(new GridLayout(1, cols))
  panel.setBorder(new EmptyBorder(10, 10, 10, 10))
  range.map(i => {
    panel.add(createLabel(i))
  })
  panel
}

/** remove all frames */
def removeAllFrames(): Unit = {
  val frames = Frame.getFrames
  for (frame <- frames) {
    if (frame.getTitle != "Input")
      frame.dispose()
  }
}

/** tic tac toe drawer */
def ticTacToeDrawer(state: Array[Array[String]]): Unit = {
  removeAllFrames()
  val frame = new JFrame()
  frame.setPreferredSize(new Dimension(800, 800))
  var layout = new GridLayout(3, 3)
  val panel = new JPanel()
  panel.setLayout(layout)
  panel.setSize(new Dimension(400, 400))
  for (i <- 0 to 2) {
    for (j <- 0 to 2) {

      val button = new JLabel("", SwingConstants.CENTER)
      button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5))
      button.setFont(new Font(Font.DIALOG, Font.BOLD, 75))
      panel.add(button)
      if (state(i)(j) == "X") {
        button.setText("X")
      }
      else if (state(i)(j) == "O") {
        button.setText("O")
      }

      else {
        if (button.getIcon != null)
          button.setIcon(null)
      }
    }
  }
  frame.add(verticalIndex(3), BorderLayout.WEST)
  frame.add(verticalIndex(3), BorderLayout.EAST)
  frame.add(horizontalIndex(3), BorderLayout.NORTH)
  frame.add(horizontalIndex(3), BorderLayout.SOUTH)

  frame.add(panel, BorderLayout.CENTER)
  frame.setTitle("Tic Tac Toe Board")
  frame.pack()
  frame.setVisible(true)
}

/** circular label */
def createCircularLabel(): JLabel = {
  val circularLabel = new JLabel() {
    val circleColor: Color = Color.white

    override def paintComponent(g: Graphics): Unit = {
      val g2 = g.asInstanceOf[Graphics2D]
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
      val size = getSize()
      val d = Math.min(size.width, size.height)
      val x = (size.width - d) / 2
      val y = (size.height - d) / 2
      val shape: Shape = new Ellipse2D.Double(x, y, d, d)
      g2.setColor(circleColor)
      g2.fill(shape)
      super.paintComponent(g)
      //        imageIcon.paintIcon(this, g2, x, y)
    }
  }

  circularLabel.setBackground(Color.WHITE)
  circularLabel.setOpaque(true)

  circularLabel
}

/** connect4 */
def connect4Drawer(state: Array[Array[String]]): Unit = {
  removeAllFrames()
  val frame = new JFrame()
  frame.setBackground(Color.BLUE)
  frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
  frame.setPreferredSize(new Dimension(800, 800))
  var layout = new GridLayout(6, 7)
  val panel = new JPanel()
  panel.setLayout(layout)
  panel.setSize(new Dimension(400, 400))
  for (i <- 0 to 5) {
    for (j <- 0 to 6) {

      val jLabel = createCircularLabel()
      val size = 50
      jLabel.setPreferredSize(new Dimension(size, size))
      jLabel.setBorder(BorderFactory.createEmptyBorder())
      jLabel.setIcon(new ImageIcon("src/main/scala/resources/circle-outline-102.png"))
      panel.add(jLabel)
      if (state(i)(j) == "r") {
        val red = new ImageIcon("src/main/scala/resources/ChipRed.png")
        val chipImageResized = red.getImage().getScaledInstance(
          110, 110, Image.SCALE_SMOOTH)
        jLabel.setIcon(new ImageIcon(chipImageResized))

      }
      else if (state(i)(j) == "y") {
        val red = new ImageIcon("src/main/scala/resources/ChipYellow.png")
        val chipImageResized = red.getImage().getScaledInstance(
          110, 110, Image.SCALE_SMOOTH)
        jLabel.setIcon(new ImageIcon(chipImageResized))
      }
    }
  }
  frame.add(horizontalIndex(7), BorderLayout.NORTH)
  frame.add(horizontalIndex(7), BorderLayout.SOUTH)

  frame.add(panel, BorderLayout.CENTER)
  frame.setTitle("Tic Tac Toe Board")
  frame.pack()
  frame.setVisible(true)
}

/** sudoku */
def SudokuDrawer(state: Array[Array[String]]): Unit = {
  removeAllFrames()
  val range: NumericRange.Inclusive[Char] = '1' to ('1'.toInt + 9 - 1).toChar
  val frame = new JFrame()
  frame.setPreferredSize(new Dimension(800, 800))
  var layout = new GridLayout(9, 9)
  val panel = new JPanel()
  panel.setLayout(layout)
  var buffer = Array.ofDim[String](9, 9)
  panel.setSize(new Dimension(400, 400))
  for (i <- 0 to 8) {
    for (j <- 0 to 8) {
      buffer(i)(j) = state(i)(j).charAt(0) + ""
    }
  }
  val mystate = buffer.clone().flatten
  for (i <- 1 to 81) {
    val jLabel = new JLabel("", SwingConstants.CENTER)
    if ((i > 18 && i < 28) || (i > 45 && i < 55)) {
      jLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.BLACK))
      if (i % 3 == 0) {
        if (i % 9 != 0) {
          jLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.BLACK))
        }
        else {
          jLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.BLACK))
        }
      }
    }
    else {
      if (i % 3 == 0) {
        if (i % 9 != 0) {
          jLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.BLACK))
        }
        else {
          jLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK))
        }
      }
      else jLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1))
    }

    jLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25))
    panel.add(jLabel)
    if (!mystate(i - 1).equals("0")) {
      jLabel.setText(mystate(i - 1))
    }
    else {
      jLabel.setText("")
      jLabel.setBackground(Color.yellow)
    }
  }
  frame.add(verticalIndex(9), BorderLayout.WEST)
  frame.add(verticalIndex(9), BorderLayout.EAST)
  frame.add(horizontalIndex(9), BorderLayout.NORTH)
  frame.add(horizontalIndex(9), BorderLayout.SOUTH)
  frame.add(panel, BorderLayout.CENTER)
  frame.setTitle("Sudoku Board")
  frame.pack()
  frame.setVisible(true)
}
