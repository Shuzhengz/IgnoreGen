package prj.ignore.ignoregen.ui;

import prj.ignore.ignoregen.util.Logger;
import prj.ignore.ignoregen.util.Severity;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainRenderer extends JFrame {

    public static JFrame stamp;
    public static final String TITLE = "IgnoreGen";

    static String optionalSelectedFile;
    static boolean fileSelected;

    private static Button lastButtonPressed = Button.NONE;

    public enum Button {
        NONE, LANGUAGE, IDE, BUILD, OTHERS, GENERATE
    }

    public static String returnDataPath() {
        // return the csvData variable to the main app
        return optionalSelectedFile;
    }

    public static boolean returnFileSelected() {
        // return if a file is selected
        return fileSelected;
    }

    public static synchronized Button returnLastButtonPressed() {
        return lastButtonPressed;
    }

    public static void render() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        stamp = new JFrame("IgnoreGen Selector");

        JPanel panel = new JPanel();
        Logger.log("Panel Created", Severity.NORMAL);

        stamp.setLayout(new FlowLayout());

        JButton fileSelector = new JButton("<html><b><u>Select File</u></b><br>No file selected</html>");
        stamp.add(fileSelector);

        fileSelector.addActionListener(e -> {
            //Code ran when the button is clicked
            lastButtonPressed = Button.LANGUAGE;
            JFileChooser fc = new JFileChooser();
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Git Ignore Files (*.gitignore)", "gitignore");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(fc);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    // Try and get the file
                    optionalSelectedFile = chooser.getSelectedFile().getAbsolutePath();
                    Logger.log("Path read successful, Path: " + optionalSelectedFile, Severity.NORMAL);
                    fileSelector.setText("<html><b><u>Select File</u></b><br>" +
                            chooser.getSelectedFile().getName() + "</html>");
                    fileSelected = true;
                } catch (Exception ee) {
                    //  Exception handler
                    Logger.log("Invalid file type input", Severity.ERROR);
                }
            }

            SwingUtilities.invokeLater(() -> {
                try {
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceNightShadeLookAndFeel");
                    Logger.log("Substance initialized", Severity.NORMAL);
                } catch (Exception ee) {
                    Logger.log("Substance failed to initialize", Severity.ERROR);
                } finally {
                    Logger.log("Substance ran", Severity.NORMAL);
                }

                //TODO adds rendering submenus
            });
        });

        stamp.add(panel);

        JButton languages = new JButton("languages");
        stamp.add(languages);
        languages.addActionListener(e -> lastButtonPressed = Button.LANGUAGE);

        JButton ide = new JButton("IDE");
        stamp.add(ide);
        ide.addActionListener(e -> lastButtonPressed = Button.IDE);

        JButton buildTool = new JButton("Build Tools");
        stamp.add(buildTool);
        buildTool.addActionListener(e -> lastButtonPressed = Button.BUILD);

        JButton others = new JButton("Others");
        stamp.add(others);
        others.addActionListener(e -> lastButtonPressed = Button.OTHERS);

        JButton generate = new JButton("Generate Gitignore");
        stamp.add(generate);
        generate.addActionListener(e -> lastButtonPressed = Button.GENERATE);

        stamp.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR));
        stamp.setSize(1920, 1080);
        stamp.pack();
        stamp.setVisible(true);
        stamp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
