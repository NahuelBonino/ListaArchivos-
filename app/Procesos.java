
import java.io.IOException;
import javax.swing.JOptionPane;

public class Procesos {

   public static void cargarArchivo(String ruta) {
       abrir(ruta);
   }

  private static void abrir(String ruta) {
  //ruta del archivo en el pc
  String file = new String(ruta); 

 try{ 
   //definiendo la ruta en la propiedad file
   String c = "\"";
   Runtime.getRuntime().exec("cmd /c start " + c + c + " " +  c + file + c );
   //System.out.println("cmd /c start " + c + c + " " +  c + file + c);
    
   }catch(IOException e){
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Ocurrió un error al abrir el archivo, seleccionar un reproductor por defecto para el tipo de archivo");
   } 
  }
}