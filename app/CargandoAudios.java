
import javax.swing.*;
import javax.swing.border.Border;

import java.util.Objects;
import java.io.File;

import java.awt.*;  
import java.awt.event.*;  

public class CargandoAudios extends JPanel{
  
    public JLabel lb_ba;
    public JLabel lb_c;

    CargandoAudios(Principal f, final File c, String doc){

        f.setSize(800, 400);  
        f.setTitle("Buscando Archivos");
        f.setLocationRelativeTo(null);     
        f.setResizable(false);
        f.getContentPane().add(this);
        this.setVisible(true);        
        this.setLayout(null);
        this.setBackground(new Color(171, 209, 69));
        
        lb_ba= new JLabel("Buscando audios... ");
        lb_ba.setBounds(50, 100, 400, 40);
        lb_ba.setFont( new Font("Arial",Font.BOLD,25)); 
        this.add(lb_ba);
        
        lb_c = new JLabel("cargando");
        lb_c.setBounds(50, 200, 700, 40);
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        lb_c.setFont(new Font("Arial",Font.PLAIN,14));
        lb_c.setBorder(border);
        this.add(lb_c);
        actualizar(f);

        //final DefaultListModel<String> model = new DefaultListModel<>(); 
        //archivosRec(c,model,doc);

        Thread t = new Thread(new Busqueda(c,f,doc,this));  
        t.start();

    }

    public void pushNameInLabel(String s){

        lb_c.setText(s);

    }
    public void actualizar(Principal f){

        f.setSize(799,400);
        f.setSize(800,400); 
      
    }
    public void setLB_C(String nom){
        lb_c.setText(nom);
    }

    
}
