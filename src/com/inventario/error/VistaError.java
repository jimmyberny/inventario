package com.inventario.error;

import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.Vista;
import javax.swing.JComponent;

/**
 *
 * @author Zulma
 */
public class VistaError extends javax.swing.JPanel implements Vista {

    public VistaError() {
        initComponents();
    }

    @Override
    public void inicializar(Aplicacion app) throws InvException {
        
    }

    @Override
    public JComponent getVista() {
        return this;
    }

    @Override
    public String getTitulo() {
        return "Error de la aplicación";
    }

    @Override
    public void activar() throws InvException {
        
    }

    @Override
    public boolean ocultar() {
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}