package zucc.dorm316.anzu.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "goods_order", schema = "anzu", catalog = "")
public class GoodsOrderEntity {
    private int id;
    private int userId;
    private int goodsId;
    private int merchantId;
    private int userAddressId;
    private int merchantAddressId;
    private int orderStatus;
    private int goodsNum;
    private BigDecimal goodsTotalPrice;
    private Timestamp receivingTime;
    private long leaseTime;
    private BigDecimal deposit;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "goods_id")
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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
    @Column(name = "user_address_id")
    public int getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(int userAddressId) {
        this.userAddressId = userAddressId;
    }

    @Basic
    @Column(name = "merchant_address_id")
    public int getMerchantAddressId() {
        return merchantAddressId;
    }

    public void setMerchantAddressId(int merchantAddressId) {
        this.merchantAddressId = merchantAddressId;
    }

    @Basic
    @Column(name = "order_status")
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "goods_num")
    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    @Basic
    @Column(name = "goods_total_price")
    public BigDecimal getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    @Basic
    @Column(name = "receiving_time")
    public Timestamp getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Timestamp receivingTime) {
        this.receivingTime = receivingTime;
    }

    @Basic
    @Column(name = "lease_time")
    public long getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(long leaseTime) {
        this.leaseTime = leaseTime;
    }

    @Basic
    @Column(name = "deposit")
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsOrderEntity that = (GoodsOrderEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (goodsId != that.goodsId) return false;
        if (merchantId != that.merchantId) return false;
        if (userAddressId != that.userAddressId) return false;
        if (merchantAddressId != that.merchantAddressId) return false;
        if (orderStatus != that.orderStatus) return false;
        if (goodsNum != that.goodsNum) return false;
        if (leaseTime != that.leaseTime) return false;
        if (goodsTotalPrice != null ? !goodsTotalPrice.equals(that.goodsTotalPrice) : that.goodsTotalPrice != null)
            return false;
        if (receivingTime != null ? !receivingTime.equals(that.receivingTime) : that.receivingTime != null)
            return false;
        if (deposit != null ? !deposit.equals(that.deposit) : that.deposit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + goodsId;
        result = 31 * result + merchantId;
        result = 31 * result + userAddressId;
        result = 31 * result + merchantAddressId;
        result = 31 * result + orderStatus;
        result = 31 * result + goodsNum;
        result = 31 * result + (goodsTotalPrice != null ? goodsTotalPrice.hashCode() : 0);
        result = 31 * result + (receivingTime != null ? receivingTime.hashCode() : 0);
        result = 31 * result + (int) (leaseTime ^ (leaseTime >>> 32));
        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
        return result;
    }
}
