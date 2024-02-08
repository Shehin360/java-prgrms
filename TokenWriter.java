// Import necessary classes for the program
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// Define a class named TokenWriter
public class TokenWriter extends JFrame implements ActionListener {
    // Declare variables for GUI components
    private JTextField textField;
    private JButton readButton;

    // Constructor to initialize the GUI
    public TokenWriter() {
        // Set up the window
        setTitle("Token Writer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create a text field for input
        textField = new JTextField(20);
        add(textField);

        // Create a button to trigger action
        readButton = new JButton("Read");
        readButton.addActionListener(this);
        add(readButton);

        // Make the window visible
        setVisible(true);
    }

    // Entry point of the program
    public static void main(String[] args) {
        // Run the program on the event dispatch thread
            new TokenWriter();
    }

    // Method to handle button click event
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the button clicked is the "Read" button
        if (e.getSource() == readButton) {
            // Get the text from the text field
            String sentence = textField.getText().trim();
            // Check if the text field is not empty
            if (!sentence.isEmpty()) {
                try {
                    // Write the words to a file
                    writeWordsToFile(sentence);
                    // Show a success message
                    JOptionPane.showMessageDialog(this, "Words saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    // Show an error message if writing to file fails
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error saving words!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Show an error message if the text field is empty
                JOptionPane.showMessageDialog(this, "Please type some words!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method to write words to a file
    private void writeWordsToFile(String sentence) throws IOException {
        // Create a PrintWriter object to write to a file
        try (PrintWriter writer = new PrintWriter("output.txt")) {
            // Split the sentence into words
            String[] words = sentence.split("\\s+");
            // Write each word to the file
            for (String word : words) {
                writer.println(word);
            }
        }
    }
}
