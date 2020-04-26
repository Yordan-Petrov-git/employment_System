package Helpers.UtilityGui;

import javax.swing.*;

public class GuiUtils {

    public static void showPassword(JToggleButton jToggleButton, JPasswordField passwordField) {
        if (jToggleButton.isSelected()) {//If button is presed
            //jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Pass onn.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
            passwordField.setEchoChar((char) 0);//Sets cahrecter tovisible
        } else {//If button is presed again
            passwordField.setEchoChar('*');//Sets cahrecter to invisible defaut
            //  jButtonShowPassword.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pass off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
        }
    }

    public static void removeText(JTextField textField) {
        textField.setText("");
    }

}
