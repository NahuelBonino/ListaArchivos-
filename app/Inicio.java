
import javax.swing.*;
import javax.swing.Timer;

import java.io.*;
import java.util.*;
import java.awt.*;  
import java.awt.event.*;

import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import java.lang.Class;
import java.net.URI;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipInputStream;



public class Inicio extends JPanel{
    
    private static String ruta_archivo;
    private static JFileChooser chooser;
    private static JLabel lb_c;
    private static File c;
    private static JTextField jtf;
    private static String documento;
   


    Inicio(Principal f){

        f.getContentPane().add(this);
        this.setVisible(true);        
        this.setLayout(null);
        this.setBackground(new Color(171, 209, 69));
        componentes(f,this);
        f.setSize(800, 400);
        f.setLocationRelativeTo(null);
        

    }

    public static void componentes(Principal f, JPanel p) {
       
        JButton bRuta = new JButton("E");
        bRuta.setBounds(690, 80, 50, 40);
        bRuta.setFont( new Font("Arial",Font.BOLD,30));
        
        //LEO LA RUTA DEL ARCHIVO
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            File directorio = new File("C:/IngeniaRuta");
            if (directorio.exists()) { 
                File fichero = new File ("C:/IngeniaRuta/temp.txt");
                if(fichero.exists()){
                    fr = new FileReader (fichero);
                    br = new BufferedReader(fr);
                    // Lectura del fichero       
                    ruta_archivo = br.readLine();     
                    fr.close();         
                }
                else{
                    ruta_archivo = "C:/";
                }
            }
            else{
                ruta_archivo = "C:/";
            }
        }
         catch(Exception e){
            e.printStackTrace();
        }

        //EVENTO DEL BOTON
        bRuta.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                elegirCarpeta(p);
                c = new File(ruta_archivo);            
            }  
        }); 

        p.add(bRuta);
        //Archivo 
        c = new File(ruta_archivo);

        ImageIcon icon = new ImageIcon(Inicio.class.getResource("img/icono2.png"));
        f.setIconImage(icon.getImage());

        //texto
        JLabel lb_cn = new JLabel("Carpeta del archivo: ");
        lb_cn.setBounds(40, 80, 200, 40);
        lb_cn.setFont( new Font("Arial",Font.PLAIN,18));

        //Label carpeta actual
        lb_c = new JLabel(c.getAbsolutePath());
        lb_c.setBounds(230, 80, 450, 40);
        Border border = BorderFactory.createLineBorder(Color.gray, 2);
        lb_c.setFont( new Font("Arial",Font.PLAIN,17));
        lb_c.setBorder(border);

        //BUSCAR POR DOCUMENTO

        JLabel def = new JLabel("El archivo contiene: ");
        def.setFont( new Font("Arial",Font.PLAIN,18));
        def.setBounds(40,130,200,40);
        p.add(def);

        jtf = new JTextField();
        jtf.setBounds(230,130,200,40);
        jtf.setFont( new Font("Arial",Font.BOLD,17));
        p.add(jtf);

        //BOTON LISTAR
        JButton b = new JButton("Listar archivos");
        b.setBounds(200, 200, 400, 100);
        b.setFont( new Font("Arial",Font.BOLD,30));

        //EVENTO DEL BOTON
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
          
                        documento = jtf.getText();                                     
                        f.remove(p);  
                        CargandoAudios ca = new CargandoAudios(f,c,documento);                     
                        //listaLlamadas ls = new listaLlamadas(f,c,documento);  //cuando se apreta el boton se listan los archivos
            }
        }); 
     
        //LO AGREGO AL PANEL
        p.add(b);
        p.add(lb_c);
        p.add(lb_cn);
        p.updateUI();
    }


    public static void elegirCarpeta(JPanel p){

        chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Elegir la carpeta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);
          
        if (chooser.showOpenDialog(p) == JFileChooser.APPROVE_OPTION) { 
          String ruta = chooser.getSelectedFile().getAbsolutePath();
          lb_c.setText(ruta);
          ruta_archivo = ruta;
       }
       
      
       //ACTUALIZO LA RUTA ELEGIDA PARA QUE PERSISTA

        File directorio = new File("C:/IngeniaRuta");
      
        if (!directorio.exists()) {    // SI EL DIRECTORIO NO EXISTE
            if (directorio.mkdirs()) { //CREO EL DIRECTORIO
                System.out.println("Directorio creado");
                File fichero = new File ("C:/IngeniaRuta/temp.txt"); //CREO EL ARCHIVO       
                try{
                    fichero.createNewFile();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fichero ,true));
                    System.out.println(ruta_archivo);
                    bw.write(ruta_archivo); //ESCRIBO LA RUTA ELEGIDA 
                    bw.close();
                }catch(Exception e)
                {
                    System.out.println(e);
                }      
            } else {
                System.out.println("Error al crear directorio");
            }
        }else{ //SI YA EXISTE LA RUTA             
            File fichero = new File ("C:/IngeniaRuta/temp.txt"); //CREO EL ARCHIVO
            if(!fichero.exists()) {    //SI NO EXISTE EL ARCHIVO       
                try{
                    fichero.createNewFile();
                    BufferedWriter bwm = new BufferedWriter(new FileWriter(fichero ,true));
                    bwm.write(ruta_archivo); //ESCRIBO LA RUTA ELEGIDA 
                    bwm.close();
                }catch(Exception e)
                {
                    System.out.println(e);
                }      
            }
            else{ //EXISTE EL ARCHIVO
                 fichero.delete(); //lo borro
                 try{
                    fichero.createNewFile(); //lo creo de nuevo
                    BufferedWriter bwm = new BufferedWriter(new FileWriter(fichero ,true));
                    bwm.write(ruta_archivo); //ESCRIBO LA RUTA ELEGIDA 
                    bwm.close();
                }catch(Exception e)
                {
                    System.out.println(e);
                }      

            }
        }

        
     }

     private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }



}
