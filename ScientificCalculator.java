/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scintificcalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// New ProjectInfoFrame class
// New ProjectInfoFrame class
class ProjectInfoFrame extends JFrame implements ActionListener {
    private JButton gotoprojectButton;

    public ProjectInfoFrame() {
        setTitle("Project Information");
        setSize(600, 500); // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a JTextArea to display the project information
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 10, 10, 10)); // Add padding for readability

        // Add the text to the JTextArea
        String projectInfo = "Department - IT\n"
                + "Section 1\n"
                + "OBJECT ORIENTED IN JAVA\n"
                + "Project title :- Calculator\n\n"
                + "NAME\t\tID NO\n"
                + "1. MERDEKIOS ESTIFANOS\tRU1421/15\n"
                + "2. ELHAM ABDU\t\tRU0693/15\n"
                + "3. FERDAWS MUSSA\tRU0814/15\n"
                + "4. SEBRIN MAHMUD\tRU1824/15\n"
                + "5. MELAKU TSEGAYE\tRU1403/15\n"
                + "6. SEWIT TIRUNEH\tRU1866/15\n"
                + "7. KIRUBEL YONAS\tRU1249/15\n"
                + "8. BEMNET TESHOME\tRU0375/15\n"
                + "9. HUNDATE SUAD\tRU1229/15\n";

        textArea.setText(projectInfo);

        // Add the JTextArea inside a JScrollPane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create a "Next" button
        gotoprojectButton = new JButton("Go to project");
        gotoprojectButton.setFont(new Font("Arial", Font.BOLD, 20));
        gotoprojectButton.addActionListener(this);
        add(gotoprojectButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gotoprojectButton) {
            // Close the current frame
            this.dispose();

            // Open the ScientificCalculator frame
            ScientificCalculator calculator = new ScientificCalculator();
            calculator.setVisible(true);
        }
    }
}


// Existing ScientificCalculator class (unchanged)
public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double firstNumber;
    private boolean isNewInput = true; // To track if the display should be cleared for new input

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(600, 500); // width and height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 40)); // text field size
        display.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 6, 5, 5)); // 6x6 grid with gaps

        // Button labels in the order of a standard scientific calculator
        String[] buttons = {
            "C", "B", "√", "/", "sin", "cos",
            "7", "8", "9", "*", "tan", "log",
            "4", "5", "6", "-", "sinh", "cosh",
            "1", "2", "3", "+", "tanh", "ln",
            "0", ".", "+/-", "=", "exp", "X^Y",
            "π", "e", "X^2", "3^√", "||", "e^x"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            button.setBackground(Color.LIGHT_GRAY); // Set button background color
            button.setForeground(Color.BLACK); // Set button text color
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if ("0123456789.".contains(command)) {
                if (isNewInput) {
                    display.setText("");
                    isNewInput = false;
                }
                display.setText(display.getText() + command);
            } else if (command.equals("+/-")) {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    double currentValue = Double.parseDouble(currentText);
                    currentValue = -currentValue;
                    display.setText(String.valueOf(currentValue));
                }
            } else if (command.equals("=")) {
                double secondNumber = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        display.setText(String.valueOf(firstNumber + secondNumber));
                        break;
                    case "-":
                        display.setText(String.valueOf(firstNumber - secondNumber));
                        break;
                    case "*":
                        display.setText(String.valueOf(firstNumber * secondNumber));
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            display.setText(String.valueOf(firstNumber / secondNumber));
                        } else {
                            display.setText("cann't divide by 0 "); // Division by zero
                        }
                        break;
                    case "X^Y":
                        display.setText(String.valueOf(Math.pow(firstNumber, secondNumber)));
                        break;
                }
                isNewInput = true;
            } else if (command.equals("B")) {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (command.equals("X^2")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(number * number));
                isNewInput = true;
            } else if (command.equals("√")) {
                double number = Double.parseDouble(display.getText());
                if (number >= 0) {
                    display.setText(String.valueOf(Math.sqrt(number)));
                } else {
                    display.setText("Error"); // Square root of a negative number
                }
                isNewInput = true;
            } else if (command.equals("exp")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.exp(number)));
                isNewInput = true;
            } else if (command.equals("||")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.abs(number)));
                isNewInput = true;
            } else if (command.equals("3^√")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.cbrt(number)));
                isNewInput = true;
            } else if (command.equals("e")) {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    double number = Double.parseDouble(currentText);
                    display.setText(String.valueOf(number * Math.E)); // Multiply by e
                } else {
                    display.setText(String.valueOf(Math.E)); // Display e
                }
                isNewInput = true;
            } else if (command.equals("e^x")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.exp(number)));
                isNewInput = true;
            } else if (command.equals("π")) {
                String currentText = display.getText();
                if (!currentText.isEmpty()) {
                    double number = Double.parseDouble(currentText);
                    display.setText(String.valueOf(number * Math.PI)); // Multiply by π
                } else {
                    display.setText(String.valueOf(Math.PI)); // Display π
                }
                isNewInput = true;
            } else if (command.equals("ln")) {
                double number = Double.parseDouble(display.getText());
                if (number > 0) {
                    display.setText(String.valueOf(Math.log(number))); // Natural logarithm
                } else {
                    display.setText("Error"); // Logarithm of a non-positive number
                }
                isNewInput = true;
            } else if (command.equals("log")) {
                double number = Double.parseDouble(display.getText());
                if (number > 0) {
                    display.setText(String.valueOf(Math.log10(number))); // Base-10 logarithm
                } else {
                    display.setText("Error"); // Logarithm of a non-positive number
                }
                isNewInput = true;
            } else if (command.equals("sin")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sin(Math.toRadians(number))));
                isNewInput = true;
            } else if (command.equals("cos")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.cos(Math.toRadians(number))));
                isNewInput = true;
            } else if (command.equals("tan")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.tan(Math.toRadians(number))));
                isNewInput = true;
            } else if (command.equals("sinh")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sinh(number)));
                isNewInput = true;
            } else if (command.equals("cosh")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.cosh(number)));
                isNewInput = true;
            } else if (command.equals("tanh")) {
                double number = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.tanh(number)));
                isNewInput = true;
            } else if (command.equals("C")) {
                display.setText(""); // Clear the display
                isNewInput = true;
            } else {
                operator = command;
                firstNumber = Double.parseDouble(display.getText());
                isNewInput = true;
            }
        } catch (NumberFormatException ex) {
            // Do nothing for invalid inputs (e.g., pressing buttons without a number)
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Display the ProjectInfoFrame first
            ProjectInfoFrame projectInfoFrame = new ProjectInfoFrame();
            projectInfoFrame.setVisible(true);
        });
    }
}