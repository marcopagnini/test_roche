package marco.warehouse.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Each order should have a list of product, unique id, the buyer’s e-mail, and the time the
order was placed. It should be possible to calculate the total order amount, based on the
price of the individual products. */

@Entity
public class ClientOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID")
    private Integer id;
    @ApiModelProperty(notes = "The product list")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> productList = new ArrayList<>();
    @ApiModelProperty(notes = "Buyer's email")
    private String buyerEmail;
    @ApiModelProperty(notes = "Buyer's order placing date")
    @Temporal(TemporalType.DATE)
    private Date buyingDate;
    @ApiModelProperty(notes = "The product's active status", required = true)
    private Boolean active = true;

    @Transient
    public BigDecimal getTotalOrderPrice() {
        BigDecimal sum = BigDecimal.valueOf(0);
        List<Product> orderProducts = getProductList();
        for (Product product : orderProducts) {
            sum = sum.add(product.getPrice());
        }
        return sum;
    }

    /**
     *
     * @return
     */
    public Integer getId() { return id; }
    /**
     *
     * @param id
     */
    public void setId(Integer id) { this.id = id; }
    /**
     *
     * @return
     */
    public List<Product> getProductList() { return productList; }
    /**
     *
     * @param product
     */
    public void addProduct(Product product) { this.productList.add(product); }
    /**
     *
     * @return
     */
    public String getBuyerEmail() { return buyerEmail; }
    /**
     *
     * @param buyerEmail
     */
    public void setBuyerEmail(String buyerEmail) { this.buyerEmail = buyerEmail; }
    /**
     *
     * @return
     */
    public String getBuyingDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(buyingDate);
        //return buyingDate;
    }
    /**
     *
     * @param buyingTime
     */
    public void setBuyingDate(Date buyingTime) { this.buyingDate = buyingTime; }
    /**
     * Gets active status
     *
     * @return the active status
     */
    public Boolean getActive() {return active;}
    /**
     * Sets the active status
     *
     * @param active the active status
     */
    public void setActive(Boolean active) {this.active = active;}
}
