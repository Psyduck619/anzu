package zucc.dorm316.anzu.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goods", schema = "anzu", catalog = "")
public class GoodsEntity {
    private int id;
    private String goodsName;
    private double price;
    private int categoryId;
    private int merchantId;
    private String intro;
    private int status;
    private String picUrl;
    private int mode;
    private double deposit;
    private int stock;
    private int sales;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "goods_name")
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "merchant_id")
    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "intro")
    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "pic_url")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "mode")
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Basic
    @Column(name = "deposit")
    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "stock")
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "sales")
    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsEntity that = (GoodsEntity) o;

        if (id != that.id) return false;
        if (categoryId != that.categoryId) return false;
        if (merchantId != that.merchantId) return false;
        if (status != that.status) return false;
        if (mode != that.mode) return false;
        if (Double.compare(that.deposit, deposit) != 0) return false;
        if (stock != that.stock) return false;
        if (sales != that.sales) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (intro != null ? !intro.equals(that.intro) : that.intro != null) return false;
        if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + categoryId;
        result = 31 * result + merchantId;
        result = 31 * result + (intro != null ? intro.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        result = 31 * result + mode;
        temp = Double.doubleToLongBits(deposit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + stock;
        result = 31 * result + sales;
        return result;
    }
}
