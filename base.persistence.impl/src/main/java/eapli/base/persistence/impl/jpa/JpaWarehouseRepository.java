package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.repository.WarehouseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class JpaWarehouseRepository extends JpaAutoTxRepository<Warehouse, WarehouseName, WarehouseName> implements WarehouseRepository {

    public JpaWarehouseRepository(final TransactionalContext autoTx) {
        super(autoTx, "warehouseName");
    }

    public JpaWarehouseRepository(final String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "warehouseName");
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        Query q = entityManager().createQuery("SELECT wh FROM Warehouse wh");
        return q.getResultList();
    }

    @Override
    public List<String> getAllWarehousesNames() {
        Query q = entityManager().createQuery("SELECT w.warehouseName FROM Warehouse w");
        return q.getResultList();
    }

    @Override
    public Warehouse getWarehouseByName(WarehouseName name) {
        Query q = entityManager().createQuery("SELECT w FROM Warehouse w" +
                                            " WHERE warehouseName =: name");
        q.setParameter("name", name);
        return (Warehouse) q.getSingleResult();
    }

    @Override
    public Warehouse getWarehouseByName2(String name) {
        Query q = entityManager().createQuery("SELECT w FROM Warehouse w" +
                                                 " WHERE warehouseName.warehouseName =: name");
        q.setParameter("name", name);
        return (Warehouse) q.getSingleResult();
    }


}
