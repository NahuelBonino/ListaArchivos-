import java.io.File;

import javax.swing.DefaultListModel;

public class Busqueda extends Thread{

    public Principal f;
    public File c;
    public String doc;
    public DefaultListModel<String> mod;
    public CargandoAudios carg;
    public listaLlamadas ll;

    Busqueda(final File car,Principal p,String documento,CargandoAudios ca){
        f = p;
        c = car;
        doc = documento;
        mod = new DefaultListModel<>(); 
        carg = ca;
        
    }
    
    public void run(){

      
        archivosRec(c,mod,doc,carg);
        f.remove(carg);
        ll = new listaLlamadas(f, mod);

    }

    public void archivosRec(File dir, final DefaultListModel<String> mod, String cedula,CargandoAudios ca){
        File[] files = dir.listFiles();    
          
        if (files != null && files.length > 0) {
            for (File file : files) {               
                carg.setLB_C(file.getAbsolutePath());  
                // nos fijamos si es un directorio
                if (file.isDirectory()) {
                    //recorremos la carpeta para imprimir sus archivos de forma recursiva                
                    archivosRec(file, mod, cedula,ca);
                } else {
  
                    if (file.getName().contains(cedula.trim())){
                        mod.addElement(file.getAbsolutePath());                                         
                   }
  
                }
             }
        }
    }
}
