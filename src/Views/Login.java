package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPanel panel1;
    private JButton button1;





    public Login() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("succes" );
            }
        });
    }

    public Login(String title) throws HeadlessException {
        super(title);
    }
}
