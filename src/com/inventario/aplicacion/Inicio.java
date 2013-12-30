package com.inventario.aplicacion;

import com.inventario.aplicacion.gui.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.SwingUtilities;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nobody
 */
public class Inicio {
    
    private static final Logger log = LoggerFactory.getLogger(Inicio.class);
    
    private static final Map<String, String> CLAVES;
    
    static {
        CLAVES = new HashMap<String, String>(3);
        CLAVES.put("aplicacion.bd.url", "hibernate.connection.url");
        CLAVES.put("aplicacion.bd.usuario", "hibernate.connection.username");
        CLAVES.put("aplicacion.bd.contrasena", "hibernate.connection.password");
    }
    
    private static void configurar(Configuration cfgHib, Configuracion cfg) {
        Iterator<Map.Entry<String, String>> it = CLAVES.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String val = cfg.getProperty(next.getKey());
            if (val != null) {
                log.info("Setting {}", val);
                cfgHib.setProperty(next.getValue(), val);
            }
        }
        cfgHib.buildSettings(); // Actualizar la configuracion
    }

    public static void main(String[] args) {
        final Configuracion cfg = new Configuracion();
        
        // Configurar hibernate
        Configuration hc = new Configuration().configure();
        configurar(hc, cfg); // Realizar los ajustes puestos en el archivo de configuración
        final SessionFactory factory = hc.buildSessionFactory();
        
        try {
            // Intentar una transaccion vacia, si ocurre una excepcion es porque no sirve
            // la configuración que esta puesta.
            Session cs = factory.getCurrentSession();
            cs.beginTransaction();
            cs.getTransaction().commit();
        } catch (HibernateException hex) {
            log.error(hex.getMessage(), hex);
            System.exit(1);
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Principal app = new Principal(cfg, factory);
                app.setVisible(true);
            }
        });
    }
}