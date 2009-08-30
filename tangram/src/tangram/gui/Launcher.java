/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tangram.gui;

/**
 *
 * @author GlauKo
 */
public class Launcher {
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
}
