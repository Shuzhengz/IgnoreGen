package prj.ignore.ignoregen;

import prj.ignore.ignoregen.ui.MainRenderer;
import prj.ignore.ignoregen.util.Logger;
import prj.ignore.ignoregen.util.Severity;

import javax.swing.*;
import java.io.FileNotFoundException;

public class IgnoreGen {

    private IgnoreGen() throws RuntimeException {
        // The IgnoreGen class method
    }

    public static IgnoreGen app = null;


    // Application initialization
    static {
        try {
            app = new IgnoreGen();
            Logger.log("App Initializing", Severity.NORMAL);
        } catch (RuntimeException initializeError) {
            initializeError.printStackTrace();
            Logger.log("Error Initializing", Severity.FATAL);
        }
    }

    /**
     * The main application loop
     */
    public void mainloop() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Night skin for the UI
                UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceNightShadeLookAndFeel");
                Logger.log("Substance initialized", Severity.NORMAL);
            } catch (Exception e) {
                // Error initializing skin
                Logger.log("Substance failed to initialize", Severity.ERROR);
            } finally {
                Logger.log("Substance ran", Severity.NORMAL);
            }

            try {
                // Renders the new window
                MainRenderer.render();
                Logger.log("Window Created", Severity.NORMAL);
            } catch (Exception e) {
                Logger.log("Error creating window", Severity.FATAL);
            }
        });
    }
}
