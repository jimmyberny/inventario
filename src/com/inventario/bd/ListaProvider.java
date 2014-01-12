package com.inventario.bd;

import com.inventario.error.InventarioException;
import com.inventario.interfaces.Aplicacion;
import com.inventario.interfaces.DataProvider;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author José Bernardo Gómez-Andrade
 */
public abstract class ListaProvider<T> implements DataProvider<T> {

    private final Transaccion listar;

    public ListaProvider(Aplicacion app) {
        listar = new Transaccion(app.getSessionFactory()) {

            @Override
            public Object execInTransaction(Session s, Object... params) throws InventarioException {
                return s.createCriteria(getClase()).list();
            }
        };
    }

    @Override
    public List<T> cargar() throws InventarioException {
        return (List<T>) listar.exec();
    }

    public abstract Class getClase();

}
