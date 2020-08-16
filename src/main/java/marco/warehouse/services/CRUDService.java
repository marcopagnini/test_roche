package marco.warehouse.services;

import java.util.List;

public interface CRUDService<T> {
    List<?> listAll();

    T getBySKU(Integer sku);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
