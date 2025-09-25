package ui.GUI;

import modules.calendar.Calendar;
import modules.people.People;

/**
 * Main GUI class that handles the GUI application startup
 * 
 * @author Felipe Márquez
 */
public class MainGUI {
    
    /**
     * Run the GUI application
     * 
     * @param calendar The calendar instance
     * @param people The people instance
     */
    public static void run(Calendar calendar, People people) {
        // Set the Nimbus look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalendarMainForm().setVisible(true);
            }
        });
    }
}
