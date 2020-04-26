package Helpers.ImageUtils;

import javax.swing.*;
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


}
