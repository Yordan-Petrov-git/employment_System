package Helpers.UtilityGui;

import javax.swing.*;
import java.awt.*;

public class GuiUtils {

    public static void showPassword(JToggleButton jToggleButton, JPasswordField passwordField) {
        if (jToggleButton.isSelected()) {//If button is presed
            jToggleButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Icons/pass_onn.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
            passwordField.setEchoChar((char) 0);//Sets cahrecter tovisible
        } else {//If button is presed again
            passwordField.setEchoChar('*');//Sets cahrecter to invisible defaut
            jToggleButton.setIcon(new ImageIcon(new javax.swing.ImageIcon(GuiUtils.class.getResource("/Icons/pass_off.png")).getImage().getScaledInstance(25, 30, Image.SCALE_SMOOTH)));
        }
    }

    public static void removeText(JTextField textField) {
        textField.setText("");
    }

}
