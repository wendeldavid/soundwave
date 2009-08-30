/*
 * 
 * 
 */

package tangram.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import tangram.gui.Msg;

/**
 *
 * @author GlauKo
 *             n
 *             i
 *             h
 *             s
 */
public class ReadWriteFile {
    
    public static String read(File file){
        String texto = "";
        try{
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1];
            while(fis.read(b) != -1){
                texto += new String(b);
            }
            fis.close();
        } catch( Exception ex){
            System.out.println("Erro Na Leitura do Arquivo");
        }
        texto = texto.replaceAll(Msg.newLine, "\n");
        return texto;
    }
    
    public static void write(File file, String code){
        code = code.replaceAll("\n", Msg.newLine);
        byte[] b = code.getBytes();
        try{
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(b);
            fos.close();
        } catch (Exception ex){
            System.out.println("Erro Na Gravação do Arquivo");
        }
    }
}
