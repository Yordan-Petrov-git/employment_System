package Views.Panels.Users;

import DataProvider.DataProviderCreateNewJobOffer;
import DataProvider.DataProviderCreateNewUser;
import Views.MainFrame;

import javax.swing.*;

public class ApplyForOffer extends JPanel {

    JLabel jLabelTitle;
    JTextArea jTextAreaDescription;
    JTextField jTextFieldYearsOfExp;
    JButton jButtonApply;
    JButton jButtonBack;
    public MainFrame jFrame;

    public ApplyForOffer(MainFrame jFrame) {
        this.jFrame = jFrame;

        jLabelTitle = new JLabel("Apply for the job");
        add(jLabelTitle);

        jTextAreaDescription = new JTextArea("Write short motivational letter .");
        add(jTextAreaDescription);

        jTextFieldYearsOfExp = new JTextField("How many years of experience do you have ?");
        add(jTextFieldYearsOfExp);

        jButtonApply = new JButton("Apply");
        jButtonApply.addActionListener(e -> {

            String mLetter = jTextAreaDescription.getText();
            String yExp = jTextFieldYearsOfExp.getText();
            apply(HomePageUsersPanel.id,HomePageUsersPanel.userID,mLetter,yExp);

        });
        add(jButtonApply);

        jButtonBack = new JButton("Back");
        jButtonBack.addActionListener(e -> {

            MainFrame.router.removePanel(jFrame);
            MainFrame.router.showHomepageUserPanel(jFrame);

        });
        add(jButtonBack);

    }

    public  static void apply(int id,int userId,String mLetter,String yExp) {
        DataProviderCreateNewJobOffer.applyForJobOffer(id,userId,mLetter,yExp);
    }
}
