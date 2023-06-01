import java.awt.{BorderLayout, Color, Dimension, GridLayout}
import javax.swing.{ImageIcon, JButton, JFrame, JPanel, WindowConstants}
import javax.imageio.ImageIO


  @main def main(): Unit = {
    val frame: JFrame = new JFrame("Board Games")
    val panel: JPanel = new JPanel()

    val xo: JButton = new JButton("XO")
    val chess: JButton = new JButton("Chess")
    val sudoku: JButton = new JButton("Sudoku")
    val checkers: JButton = new JButton("Checkers")
    val connect4: JButton = new JButton("Connect-4")
    val eightQueens: JButton = new JButton("Eight Queens")
    //  val reverse: JButton = new JButton("Reverse")

    frame.setMinimumSize(new Dimension(600, 600))
    frame.setLocationRelativeTo(null)
    frame.setResizable(true)
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
    frame.setLayout(new BorderLayout(10, 10))

    panel.setBounds(0, 50, 800, 800)
    panel.setLocation(2000, 2000)
    panel.setBackground(Color.white)
    panel.setLayout(new GridLayout(2, 3))

    def loadImageIcon(resourcePath: String, width: Int, height: Int): ImageIcon = {
      new ImageIcon(ImageIO.read(getClass().getClassLoader.getResource(resourcePath)).getScaledInstance(width, height, 5))
    }

    chess.setIcon(loadImageIcon("resources/chess.png", 100, 100))
    xo.setIcon(loadImageIcon("resources/tic-tac-toe.png", 100, 100))
    checkers.setIcon(loadImageIcon("resources/checkers.png", 100, 100))
    connect4.setIcon(loadImageIcon("resources/connect-four.png", 100, 100))
    sudoku.setIcon(loadImageIcon("resources/sudoku.png", 100, 100))
    eightQueens.setIcon(loadImageIcon("resources/eight-queens.png", 100, 100))


    val buttonList = List(chess, xo, checkers, connect4, sudoku, eightQueens)
    buttonList.foreach(_.setBackground(Color.white))

    xo.addActionListener(_ => {
      val initialStatus = Array(
        Array("", "", ""),
        Array("", "", ""),
        Array("", "", "")
      )
      frame.dispose() // To delete it
      abstractEngine(ticTacToeController, ticTacToeDrawer, initialStatus, "multi", null)
    })

    sudoku.addActionListener(_ => {
      val initialStatus =generate();
      frame.dispose() // To delete it
      abstractEngine(sudokuController, SudokuDrawer, initialStatus, "single", sudokoSolver)
    })

    connect4.addActionListener(_ => {
      val initialStatus = Array(
        Array("", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", "")
      )
      frame.dispose() // To delete it
      abstractEngine(connect4Controller, connect4Drawer, initialStatus, "multi", null)
    })

    chess.addActionListener(_ => {
      val initialStatus = Array(
        Array("black_rook", "black_knight", "black_bishop", "black_queen",
          "black_king", "black_bishop", "black_knight", "black_rook"),
        Array("black_pawn", "black_pawn", "black_pawn", "black_pawn",
          "black_pawn", "black_pawn", "black_pawn", "black_pawn"),
        Array("", "", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", "", ""),
        Array("white_pawn", "white_pawn", "white_pawn", "white_pawn",
          "white_pawn", "white_pawn", "white_pawn", "white_pawn"),
        Array("white_rook", "white_knight", "white_bishop", "white_queen",
          "white_king", "white_bishop", "white_knight", "white_rook")
      )
      frame.dispose() // To delete it
      abstractEngine(chessController, ChessDrawer, initialStatus, "multi", null)
    })
    checkers.addActionListener(_ => {
      val initialStatus = Array(
        Array("", "white", "", "white",
          "", "white", "", "white"),
        Array("white", "", "white", "",
          "white", "", "white", ""),
        Array("", "white", "", "white",
          "", "white", "", "white"),
        Array("", "", "", "", "", "", "", ""),
        Array("", "", "", "", "", "", "", ""),
        Array("black", "", "black", "",
          "black", "", "black", ""),
        Array("", "black", "", "black",
          "", "black", "", "black"),
        Array("black", "", "black", "",
          "black", "", "black", "")
      )
      frame.dispose() // To delete it
      abstractEngine(checkersController, CheckersDrawer, initialStatus, "multi", null)
    })
    eightQueens.addActionListener(_ => {
      val initialStatus = Array(
        Array(".", ".", ".", ".", ".", ".", ".", "."),
        Array(".", ".", ".", ".", ".", ".", ".", "."),
        Array(".", ".", ".", ".", ".", ".", ".", "."),
        Array(".", ".", ".", ".", ".", ".", ".", "."),
        Array(".", ".", ".", ".", ".", ".", ".", "."),
        Array(".", ".", ".", ".", ".", ".", ".", "."),
        Array(".", ".", ".", ".", ".", ".", ".", "."),
        Array(".", ".", ".", ".", ".", ".", ".", ".")
      )
      frame.dispose() // To delete it
      abstractEngine(eightQueensController, eightQueensDrawer, initialStatus, "single", eightQueensSolver)
    })
    //  reverse.addActionListener(_ => {
    //      panel.setVisible(true)
    //  })

    frame.add(panel)
    //  frame.add(reverse, BorderLayout.NORTH)
    buttonList.foreach(panel.add)

    frame.pack()
    frame.setVisible(true)

  }

