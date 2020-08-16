package marco.warehouse.repositories;

import marco.warehouse.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Integer>{

    @Override
    @Query("select e from Product e where e.active=true")
    List<Product> findAll();

    //Look up deleted entities
    @Query("select e from Product e where e.active=false")
    List<Product> findAllDeleted();

    /* Doesn't work; Hibernate exception: javax.persistence.TransactionRequiredException
    //Soft delete.
    @Query("update Product e set e.active=false where e.sku= :id")
    @Modifying
    void softDelete(@Param("id") Integer id);
    */

}
