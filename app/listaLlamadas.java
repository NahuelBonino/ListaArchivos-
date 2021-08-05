
import javax.swing.*;

import java.util.Objects;
import java.io.File;

import java.awt.*;  
import java.awt.event.*;  


public class listaLlamadas extends JPanel{

   public JList<String> list;
   public  listaLlamadas p;
   private JScrollPane js;
   public  CargandoAudios ca;
   
    listaLlamadas(Principal f, final DefaultListModel<String> mod){
      
       p = this;
              
       this.setVisible(true);        
       this.setLayout(null);
       this.setBackground(new Color(171, 209, 69));
       f.setSize(800, 800);  
       f.setLocationRelativeTo(null);     
       f.setResizable(false);
       JButton atras = new JButton("<Volver");
       atras.setBounds(0,0,80,30);
       atras.addActionListener(new ActionListener(){  
       public void actionPerformed(ActionEvent e){  
                    
           LlamarOtroInicio(f,p);
 
       }
     });
       this.add(atras);
       f.getContentPane().add(this);
       actualizar(f);
       
       listarFicherosPorCarpeta(f,mod); 

  
    }  

    
    public void listarFicherosPorCarpeta(Principal f ,final DefaultListModel<String> model){    
         
      if (!model.isEmpty()){ //hay audios para listar
     
            list = new JList(model);
            list.setBounds(150,50,500,600);
            list.setFont( new Font("Arial",Font.BOLD,15));
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.add(list);
             
            JButton rep = new JButton("Abrir");  //BOTON REPRODUCIR
            rep.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
              
                    String nombre_llamada = list.getSelectedValue();
                    reproducirLlamada(nombre_llamada);        
      
              }
            }); 
            rep.setBounds(150,680,500,50); //valores del boton y el scroll
            js =  new JScrollPane(list);
            js.setBounds(100,50,600,600);
            this.add(js);
            this.add(rep);  
            actualizar(f);
      }
      else if (model.isEmpty()){ //si la lista de audios es vacia es que no se encontro la llamada
              JOptionPane.showMessageDialog(null, "No se encontro la llamada");  
              LlamarOtroInicio(f,p);
      }         
            
   }

    public void reproducirLlamada(String nom_file){

       Procesos.cargarArchivo(nom_file);

    }

    public static void LlamarOtroInicio(Principal f, JPanel p){
      f.remove(p);
      Inicio ls = new Inicio(f); 
    }

    public void actualizar(JFrame f){

      f.setSize(799,799);
      f.setSize(800, 800); 
    
    }
 
    
    
}