/*
 * 
 * 
 */

package tangram.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public final class Msg{
    private static JFrame componente = new JFrame();
    private static String titulo = "Tangram 3.0";
    public static String newLine = System.getProperty ("line.separator");
    
    public static void info(String texto){
        JOptionPane.showMessageDialog(componente, texto, titulo, 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void aviso(String texto){
        JOptionPane.showMessageDialog(componente, texto, titulo, 
                JOptionPane.WARNING_MESSAGE);
    }
    
    public static void erro(String texto){
        JOptionPane.showMessageDialog(componente, texto, titulo, 
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static boolean questao(String texto){
        return (JOptionPane.showConfirmDialog(componente, 
                texto, 
                titulo, 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ?
                    true : false);
    }
}
