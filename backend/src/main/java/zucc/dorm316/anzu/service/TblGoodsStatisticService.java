package zucc.dorm316.anzu.service;


import zucc.dorm316.anzu.entity.GoodsStatisticsEntity;

public interface TblGoodsStatisticService {
    GoodsStatisticsEntity findById(int id);
    GoodsStatisticsEntity findByGoodsId(int goods_id);
    void addGoodsStatistic(int goods_id,int rate_num, int good_rate, int mid_rate,int bad_rate,int desc_star,int logistics_star,int attitude_star);
    void modifyGoodsStatistic(int goods_id,int rate_num, int good_rate, int mid_rate,int bad_rate,int desc_star,int logistics_star,int attitude_star,int id);
    void deleteGoodsStatistic(int id);
}
