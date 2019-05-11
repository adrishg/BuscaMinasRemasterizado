/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminasjava.clases;

import buscaminasjava.Anuncio;
import buscaminasjava.Inicio;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author LENOVO
 */
public class Celda extends JButton{
    private int x;
    private int y;
    private int tipoCelda;
    private boolean visible;
    private Color colores[];

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible=false;
        this.colores=new Color[]{Color.BLUE,Color.GREEN,Color.ORANGE,Color.YELLOW,Color.PINK,Color.BLACK};
        
        
        this.setMinimumSize(new Dimension(33,9));
        this.setBackground(new java.awt.Color(33,91,115));
        this.setFont(new java.awt.Font("Arial",1,12));
        this.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    celdaActionPerformed(evt);
                }
    });
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public boolean getVisible(){
        return visible;
    }

    public void setTipoCelda(int tipoCelda) {
        this.tipoCelda = tipoCelda;
    }
    public int getTipoCelda(){
        return tipoCelda;
    }
        private void celdaActionPerformed(java.awt.event.ActionEvent evt){
            darClick();
            int it=0;
            for (int i = 0; i < Inicio.filas; i++) {
                for (int j = 0; j < Inicio.columnas; j++) {
                    if (Inicio.celda[i][j].getVisible()){
                    it++;
                }
                }
            }
            if (it==((Inicio.filas*Inicio.columnas)-Inicio.minas)){
                Inicio.gano=true;
                
            }
}
public void darClick(){
    if(!visible && Inicio.gano==false){
                this.visible=true;
                this.setBackground(new java.awt.Color(240,240,240));
                /////////////////CAMBIO TURNO
                switch(this.tipoCelda){
                    case 0:
                        for (int i = 0; i < Inicio.filas; i++) {
                            for (int j = 0; j < Inicio.columnas; j++) {  
                                if(Inicio.celda[i][j].getTipoCelda()==0){
                                Inicio.celda[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mina.png")));
                                Inicio.celda[i][j].setBackground(new java.awt.Color(240,240,240));
                            }}
                        }
                        Inicio.gano=true;
                        Anuncio stevatoGano= new Anuncio(Inicio.jDesktopPane1);
                        Inicio.jDesktopPane1.add(stevatoGano);
                        stevatoGano.show();
                        stevatoGano.setVisible(true);
                        //anuncioGano();
                        break;
                    case 1:
                        int cont=0;
                        this.setBackground(new java.awt.Color(240,240,240));
                        for(int i=-1; i<=1; i++){
                            if(x+i>0&&x+i<Inicio.filas) {
                                for (int j=-1;j<=1;j++){
                                    System.out.println(y+i+" "+y+i+" "+Inicio.columnas);
                                    if ((y+j>0&&(y+j)<Inicio.columnas)&&Inicio.celda[x+i][y+j].getTipoCelda()==0){
                                        cont++;
                                    }
                                }
                            }
                        } 
                        this.setText(""+cont); 
                        this.setForeground(this.colores[cont]);
                        break;
                    default:
                        for (int i=-1; i<=1; i++) {
                            if ((x+i)>=0&&(x+i)<Inicio.filas){
                                for(int j=-1; j<=1; j++)
                                 {
                                    if((y+j>=0&&(y+j)<Inicio.columnas) && Inicio.celda[x-i][y+j].getTipoCelda()!=0) {
                                     if(!Inicio.celda[x+i][y+j].getVisible()){
                                         Inicio.celda[x+i][y+j].darClick();
                                     }
                                 }
                                }
                            }
                        System.out.println("Nada por aquí");
                        
                }
            }
//System.out.println(tipoCelda);
        }
    
    
}
/*
public JPanel anuncioGano(){
    JButton salir = new JButton("Salir");
    JButton jugarAgain = new JButton("Jugar");

    JPanel anuncio = new JPanel();
    
    anuncio.add(salir);
    salir.addActionListener((ActionListener) this);
    anuncio.add(jugarAgain);
    jugarAgain.addActionListener((ActionListener) this);
    
    return anuncio;

}*/
        
       

}
