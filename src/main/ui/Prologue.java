package ui;

import javax.swing.*;
import java.awt.*;

public class Prologue extends JDialog {

    public Prologue() {
        String string = "<html><body width='%1s'>"
                + "<p>You have a really cute puppy, and because of "
                + "you need to go to UBC for studying. Your little puppy is raised by your grandparents.<br>"
                + "<p>Now it's summer holiday, you can finally meet your cute again.<br>"
                + "<p>However, with your grandparents' overwhelmed care, this puppy get obese now.<br>"
                + "<p>You need to help your puppy regain health within the holiday.";
        UIManager.put("OptionPane.messageFont", new Font("Georgia", Font.PLAIN, 20));
        int w = 600;
        JOptionPane.showMessageDialog(null, String.format(string,w,w),
                "Prologue",JOptionPane.PLAIN_MESSAGE);
    }
}
