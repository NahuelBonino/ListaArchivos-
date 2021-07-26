
import javax.swing.*;
import javax.swing.ImageIcon;


public class Principal extends JFrame{

    Principal(){		
        
        //FRAME
        this.setTitle("Llamadas de Ingenia");
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setResizable(false);
        //PANEL
        Inicio inicio = new Inicio(this);
        
    }
        
}