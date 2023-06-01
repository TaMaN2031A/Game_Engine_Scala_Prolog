import java.awt.{BorderLayout, Color, Dimension, FlowLayout, Font, GridBagConstraints, GridBagLayout, GridLayout}
import javax.swing.*
import scala.annotation.{tailrec, unused}
import scala.util.Random

// The higher order function Abstract engine will be defined here
def errorMessage(message: String, frame: JFrame): Unit = {
  val panel = new JPanel
  val layout = new FlowLayout
  panel.setLayout(layout)
  JOptionPane.showMessageDialog(frame, message,
    "Error", JOptionPane.WARNING_MESSAGE)
}
def abstractEngine(controller: (input: String, states: Array[Array[String]], turn: Int) => (Boolean, Array[Array[String]])
                   , drawer: (status: Array[Array[String]]) => Unit, state: Array[Array[String]], gameType: String,
                   solver: (currentState: Array[Array[String]]) => (Boolean, Array[Array[String]]))
: Unit = {
  
  val start: Boolean = true
  var Turn: Int = 1
  /** * Start of input Context * */
  val inputFrame = new JFrame("Input")
  val inputPanel = new JPanel(new GridBagLayout())
  val c = new GridBagConstraints()
  val inputField = new JTextField("")
  val inputButton = new JButton("Insert input")
  val solverButton = new JButton("Solve!")
  val dimension = new Dimension(200, 100)
  inputField.setPreferredSize(new Dimension(200, 20))
  inputPanel.setPreferredSize(dimension)
  c.gridx = 0
  c.gridy = 0
  inputPanel.add(inputField, c)
  c.gridy = 1
  inputPanel.add(inputButton, c)
  if(!gameType.equals("multi")){
    c.gridy = 2
    inputPanel.add(solverButton, c)
  }
  inputFrame.add(inputPanel)
  val screenSize = inputFrame.getToolkit.getScreenSize
  val x = (screenSize.width - inputFrame.getWidth) / 2
  val y = (screenSize.height - inputFrame.getHeight) / 2
  inputFrame.setLocation(x, y)
  inputFrame.pack()
  inputFrame.setVisible(true)

  /** * End of input Context * */
  /** Transient status array de */
  // The transient start of the app, happens only once
  drawer.apply(state)
  var State = state

  /** The core of the app */
  inputButton.addActionListener(_ => {
    val (valid, newState) = controller(inputField.getText, State, Turn)
    if (!valid) {
      errorMessage("invalid input", inputFrame)
    }
    else {
      drawer(newState)
      Turn = Turn + 1
      State = newState
    }
  })
  
  solverButton.addActionListener(_ => {
    val (valid, newState) = solver(State)
    if (!valid) {
      errorMessage("Current Board is not solvable", inputFrame)
    }
    else {
      drawer(newState)
      State = newState
    }
  })
}


