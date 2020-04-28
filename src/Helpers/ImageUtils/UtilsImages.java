package Helpers.ImageUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class UtilsImages {

    public static boolean limitImageSize(String imagePath) {
        //Limits iamge size
        boolean temp = false;
        File file = new File(imagePath);
        long length = file.length();

        if (length < 1000000) //limits iamge size to 5500000 5.5 mb
        {
            temp = true;
        }

        return temp;
    }


    public static ImageIcon resizeImage(String photopath, byte[] photo, JLabel label) {
        //Resizes the image in the label
        ImageIcon myPhoto = null;
        if (photopath != null) {
            myPhoto = new ImageIcon(photopath);
        } else {
            myPhoto = new ImageIcon(photo);
        }
        Image img = myPhoto.getImage();
        Image img1 = img.getScaledInstance(label.getWidth(), label.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon picture = new ImageIcon(img1);
        return picture;
    }



    public static void addPhoto(JLabel label) {
        String imagePath = null;
        JFileChooser chooser = new JFileChooser();//File choser
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));//Opens home directory in current user
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");//file extention filter
        chooser.addChoosableFileFilter(filter);//Sets the filter
        int ans = chooser.showSaveDialog(null);
        if (ans == JFileChooser.APPROVE_OPTION) {//if image chosen closes with yes
            File selectedPhoto = chooser.getSelectedFile();
            String path = selectedPhoto.getAbsolutePath();// sets chosen file's path
            if (UtilsImages.limitImageSize(path)) {//Limit image
                label.setIcon(UtilsImages.resizeImage(path, null, label));//Resizes selected iamge to fit in jlabel
                //  imagePath = path;
            } else {//if photo is larger
                JOptionPane.showMessageDialog(null, "Photo larger than 1 mb please select different photo", "Selected photo size is too large", 2);
            }
        } else {//Image  exited or closed
            System.out.println("Photo Not Selected ");
        }
    }

}
