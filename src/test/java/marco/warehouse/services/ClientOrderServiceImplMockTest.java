package marco.warehouse.services;

import marco.warehouse.domain.ClientOrder;
import marco.warehouse.repositories.ClientOrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ClientOrderServiceImplMockTest {

    private ClientOrderServiceImpl clientOrderServiceImpl;
    @Mock
    private ClientOrderRepository clientOrderRepository;
    @Mock
    private ClientOrder order;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        clientOrderServiceImpl=new ClientOrderServiceImpl();
        clientOrderServiceImpl.setClientOrderRepository(clientOrderRepository);
    }
    @Test
    public void shouldReturnOrder_whenGetOrderByIdIsCalled() throws Exception {
        // Arrange
        when(clientOrderRepository.findById(4)).thenReturn(Optional.of(order));
        // Act
        ClientOrder retrievedOrder = clientOrderServiceImpl.getClientOrderById(4);
        // Assert
        assertThat(retrievedOrder, is(equalTo(order)));

    }
    @Test
    public void shouldReturnProduct_whenSaveProductIsCalled() throws Exception {
        // Arrange
        when(clientOrderRepository.save(order)).thenReturn(order);
        List<Integer> skuList = new ArrayList<>();
        order.getProductList().forEach(product -> skuList.add(product.getSku()));
        // Act
        ClientOrder savedOrder = clientOrderServiceImpl.saveNewClientOrder(order.getBuyerEmail(), skuList.toArray(new Integer[0]));
        // Assert
        assertThat(savedOrder, is(equalTo(order)));
    }
}
