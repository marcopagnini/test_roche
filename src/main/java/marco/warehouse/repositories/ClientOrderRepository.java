package marco.warehouse.repositories;

import marco.warehouse.domain.ClientOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface ClientOrderRepository extends CrudRepository<ClientOrder, Integer> {

    @Query("select e from ClientOrder e where e.buyingDate >= :start AND e.buyingDate <= :end")
    List<ClientOrder> findAllInRange(@Param("start") Date start, @Param("end") Date end);

}