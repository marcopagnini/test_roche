package marco.warehouse.services;

import marco.warehouse.domain.ClientOrder;
import marco.warehouse.repositories.ClientOrderRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClientOrderServiceImplSpyTest {

    @Spy
    private ClientOrderServiceImpl orderServiceSpy;
    @Mock
    private ClientOrderRepository clientOrderRepository;
    @Mock
    private ClientOrder order;

    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetOrderByIdIsCalledWithoutContext() throws Exception {
        //Act
        ClientOrder retrievedOrder = orderServiceSpy.getClientOrderById(5);
        //Assert
        assertThat(retrievedOrder, is(equalTo(order)));
    }
    @Test
    public void shouldThrowNullPointerException_whenSaveOrderIsCalledWithoutContext() throws Exception {
        //Arrange
        Mockito.doReturn(order).when(clientOrderRepository).save(order);
        List<Integer> skuList = new ArrayList<>();
        order.getProductList().forEach(product -> skuList.add(product.getSku()));
        // Act
        ClientOrder savedOrder = orderServiceSpy.saveNewClientOrder(order.getBuyerEmail(), skuList.toArray(new Integer[0]));
        //Assert
        assertThat(savedOrder, is(equalTo(order)));
    }

    @Test
    public void shouldVerifyThatGetOrderByIdCalled() throws Exception {
        //Arrange
        Mockito.doReturn(order).when(orderServiceSpy).getClientOrderById(5);
        //Act
        ClientOrder retrievedOrder = orderServiceSpy.getClientOrderById(5);
        //Assert
        Mockito.verify(orderServiceSpy).getClientOrderById(5);
    }
}
