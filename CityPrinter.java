import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CityPrinter extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton printButton;

    public CityPrinter() {
        setTitle("City Printer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        add(inputField);

        printButton = new JButton("PRINT");
        printButton.addActionListener(this);
        add(printButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CityPrinter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == printButton) {
            int N = Integer.parseInt(inputField.getText());

            Thread thrikkakaraThread = new Thread(new CityPrinterRunnable(N, "Thrikkakara"));
            Thread kochiThread = new Thread(new CityPrinterRunnable(N, "Kochi"));

            thrikkakaraThread.start();
            kochiThread.start();
        }
    }

    class CityPrinterRunnable implements Runnable {
        private int count;
        private String city;

        public CityPrinterRunnable(int count, String city) {
            this.count = count;
            this.city = city;
        }

        @Override
        public void run() {
            synchronized (System.out) {
                for (int i = 0; i < count; i++) {
                    System.out.println(city);
                    try {
                        Thread.sleep(1000); // Sleep for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
