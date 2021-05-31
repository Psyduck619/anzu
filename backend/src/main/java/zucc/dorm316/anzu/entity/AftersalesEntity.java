package zucc.dorm316.anzu.entity;

import javax.persistence.*;

@Entity
@Table(name = "aftersales", schema = "anzu", catalog = "")
public class AftersalesEntity {
    private int id;
    private int orderId;
    private int refundType;
    private int goodsStatus;
    private String reason;
    private String processingStatus;
    private String feedback;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "refund_type")
    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
    }

    @Basic
    @Column(name = "goods_status")
    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "processing_status")
    public String getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(String processingStatus) {
        this.processingStatus = processingStatus;
    }

    @Basic
    @Column(name = "feedback")
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AftersalesEntity that = (AftersalesEntity) o;

        if (id != that.id) return false;
        if (orderId != that.orderId) return false;
        if (refundType != that.refundType) return false;
        if (goodsStatus != that.goodsStatus) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (processingStatus != null ? !processingStatus.equals(that.processingStatus) : that.processingStatus != null)
            return false;
        if (feedback != null ? !feedback.equals(that.feedback) : that.feedback != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + refundType;
        result = 31 * result + goodsStatus;
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (processingStatus != null ? processingStatus.hashCode() : 0);
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }
}
