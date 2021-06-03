package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.GoodsStatisticsEntity;
@Transactional
public interface TblGoodsStatisticDAO extends JpaRepository<GoodsStatisticsEntity,Integer> {
    @Query(value = "select * from goods_statistics where id=?",nativeQuery = true)
    GoodsStatisticsEntity findById(int id);

    @Query(value = "select * from goods_statistics where goods_id=?",nativeQuery = true)
    GoodsStatisticsEntity findByGoodsId(int goods_id);

    @Modifying
    @Query(value = "insert into goods_statictics(goods_id,rate_num,good_rate,mid_rate,bad_rate,desc_star,logistics_star,attitude_star) values (?,?,?,?,?,?,?,?)",nativeQuery = true)
    void addGoodsStatistic(int goods_id,int rate_num, int good_rate, int mid_rate,int bad_rate,int desc_star,int logistics_star,int attitude_star);

    @Modifying
    @Query(value = "update goods_statictics set goods_id = ?,rate_num=?, good_rate=?, mid_rate=?, bad_rate=?, desc_star=?, logistics_star=?, attitude_star=? where  id= ?",nativeQuery = true)
    void modifyGoodsStatistic(int goods_id,int rate_num, int good_rate, int mid_rate,int bad_rate,int desc_star,int logistics_star,int attitude_star,int id);

    @Modifying
    @Query(value = "delete from goods_statics where id = ?",nativeQuery = true)
    void deleteGoodsStatistic(int id);
}
