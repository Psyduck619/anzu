package zucc.dorm316.anzu.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment_statistic", schema = "anzu", catalog = "")
public class CommentStatisticEntity {
    private int id;
    private int orderId;
    private String content;
    private int rate;
    private int descStar;
    private int logisticsStar;

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
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "rate")
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "desc_star")
    public int getDescStar() {
        return descStar;
    }

    public void setDescStar(int descStar) {
        this.descStar = descStar;
    }

    @Basic
    @Column(name = "logistics_star")
    public int getLogisticsStart() {
        return logisticsStar;
    }

    public void setLogisticsStart(int logisticsStart) {
        this.logisticsStar = logisticsStart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentStatisticEntity that = (CommentStatisticEntity) o;

        if (id != that.id) return false;
        if (orderId != that.orderId) return false;
        if (rate != that.rate) return false;
        if (descStar != that.descStar) return false;
        if (logisticsStar != that.logisticsStar) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + rate;
        result = 31 * result + descStar;
        result = 31 * result + logisticsStar;
        return result;
    }
}
