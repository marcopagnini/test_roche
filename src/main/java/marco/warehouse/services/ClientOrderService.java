package marco.warehouse.services;

import marco.warehouse.domain.ClientOrder;

import java.util.Date;

public interface ClientOrderService {

    Iterable<ClientOrder> getAllClientOrdersInRange(Date start, Date end);

    ClientOrder saveNewClientOrder(String email, Integer[] productSkuList);

    ClientOrder getClientOrderById(Integer id);

}
