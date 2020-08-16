package marco.warehouse.controllers;

import io.swagger.annotations.*;
import marco.warehouse.domain.ClientOrder;
import marco.warehouse.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
@Api(value="onlinestore", tags="Operation pertaining orders in Online Store")
public class ClientOrderController {

    private ClientOrderService clientOrderService;

    @Autowired
    public void setClientOrderService(ClientOrderService clientOrderService) {
        this.clientOrderService = clientOrderService;
    }

    @ApiOperation(value = "View a list of all orders in between date range",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<ClientOrder> listInDateRange(
            //Model model,
            @RequestParam(name = "start", required = false, defaultValue = "2000-01-01")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @ApiParam(value = "A string representation of a date in following format: yyyy-MM-dd")
            Date start,
            @RequestParam(name = "end", required = false, defaultValue = "2100-12-31")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @ApiParam(value = "A string representation of a date in following format: yyyy-MM-dd")
            Date end){
        return clientOrderService.getAllClientOrdersInRange(start, end);
    }

    @ApiOperation(value = "Create a new order")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createOrder(@RequestParam String email, @RequestParam Integer[] productSkuList){
        clientOrderService.saveNewClientOrder(email, productSkuList);
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }


}
