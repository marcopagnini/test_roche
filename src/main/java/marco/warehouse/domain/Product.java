package marco.warehouse.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/* Each product should have a SKU (unique id), a name, a price and date it was created. */

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated product ID")
    private Integer sku;
    @ApiModelProperty(notes = "The product name")
    private String name;
    @ApiModelProperty(notes = "The price of the product", required = true)
    private BigDecimal price;
    @ApiModelProperty(notes = "The product's creation date", required = true)
    private LocalDateTime createdAt;
    @ApiModelProperty(notes = "The product's last update date", required = false)
    private LocalDateTime updatedAt;
    @ApiModelProperty(notes = "The product's active status", required = true)
    private Boolean active = true;

    /**
     * Gets product SKU (ID)
     *
     * @return the id (SKU)
     */
    public Integer getSku() {
        return sku;
    }
    /**
     * Sets product SKU (ID)
     *
     * @param id the id
     */
    public void setSku(Integer id) {
        this.sku = id;
    }
    /**
     * Gets Product's name.
     *
     * @return the product's name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets product's name.
     *
     * @param name product's name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets Product's price.
     *
     * @return the product's price
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * Sets product's price.
     *
     * @param price product's price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    /**
     * Gets created at.
     *
     * @return the created at
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    /**
     * Gets updated at.
     *
     * @return the created at
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    /**
     * Sets updated at.
     *
     * @param updatedAt the updated at
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
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
