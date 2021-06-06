package zucc.dorm316.anzu.entity;

import javax.persistence.*;

@Entity
@Table(name = "goods_statistic", schema = "anzu", catalog = "")
public class GoodsStatisticsEntity {
    private int id;
    private int goodsId;
    private int rateNum;
    private int goodRate;
    private int midRate;
    private int badRate;
    private int descStar;
    private int logisticsStar;
    private int attitudeStar;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "rate_num")
    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }

    @Basic
    @Column(name = "good_rate")
    public int getGoodRate() {
        return goodRate;
    }

    public void setGoodRate(int goodRate) {
        this.goodRate = goodRate;
    }

    @Basic
    @Column(name = "mid_rate")
    public int getMidRate() {
        return midRate;
    }

    public void setMidRate(int midRate) {
        this.midRate = midRate;
    }

    @Basic
    @Column(name = "bad_rate")
    public int getBadRate() {
        return badRate;
    }

    public void setBadRate(int badRate) {
        this.badRate = badRate;
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
    public int getLogisticsStar() {
        return logisticsStar;
    }

    public void setLogisticsStar(int logisticsStar) {
        this.logisticsStar = logisticsStar;
    }

    @Basic
    @Column(name = "attitude_star")
    public int getAttitudeStar() {
        return attitudeStar;
    }

    public void setAttitudeStar(int attitudeStar) {
        this.attitudeStar = attitudeStar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsStatisticsEntity that = (GoodsStatisticsEntity) o;

        if (id != that.id) return false;
        if (goodsId != that.goodsId) return false;
        if (rateNum != that.rateNum) return false;
        if (goodRate != that.goodRate) return false;
        if (midRate != that.midRate) return false;
        if (badRate != that.badRate) return false;
        if (descStar != that.descStar) return false;
        if (logisticsStar != that.logisticsStar) return false;
        if (attitudeStar != that.attitudeStar) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + goodsId;
        result = 31 * result + rateNum;
        result = 31 * result + goodRate;
        result = 31 * result + midRate;
        result = 31 * result + badRate;
        result = 31 * result + descStar;
        result = 31 * result + logisticsStar;
        result = 31 * result + attitudeStar;
        return result;
    }
}
