package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import zucc.dorm316.anzu.entity.GoodsOrderEntity;

import java.util.Date;
import java.util.List;

public interface TblGoodsOrderDAO extends JpaRepository<GoodsOrderEntity,Integer> {

    @Query(value = "select * from goods_order where id=?",nativeQuery = true)
    GoodsOrderEntity findById(int id);

    @Query(value = "select * from goods_order where order_status=5 OR order_status = 6",nativeQuery = true)
    List<GoodsOrderEntity> findAll();

    @Query(value = "select * from goods_order where user_id=?",nativeQuery = true)
    List<GoodsOrderEntity> findByUserId(int user_id);

    @Query(value = "select * from goods_order where goods_id=?",nativeQuery = true)
    List<GoodsOrderEntity> findByGoodsId(int goods_id);

    @Query(value = "select * from goods_order where merchant_id=?",nativeQuery = true)
    List<GoodsOrderEntity> findByMerchantId(int merchant_id);

    @Modifying
    @Query(value = "insert into goods_order(user_id,goods_id, merchant_id, user_address_id, merchant_address_id, order_status, goods_num, goods_total_price, receiving_time, lease_time, deposit) values(?,?,?,?,?,?,?,?,?,?,?)",nativeQuery = true)
    void addGoodsOrder(int user_id, int goods_id, int merchant_id,int user_address, int merchant_address, int order_status, int goods_num, double goods_total_price, Date receiving_time, int lease_time, double deposit);

    @Modifying
    @Query(value = "update goods_order set user_id = ?, goods_id=?, merchant_id = ?, user_address_id = ?, merchant_address_id = ?, order_status = ?, goods_num = ?, goods_total_price = ?, receiving_time = ?, lease_time = ?,deposit=? where id = ?",nativeQuery = true)
    void modifyGoodsOrder(int user_id, int goods_id, int merchant_id,int user_address, int merchant_address, int order_status, int goods_num, double goods_total_price, Date receiving_time, int lease_time,double deposit,int id);

    @Modifying
    @Query(value = "delete from goods_order where id = ?",nativeQuery = true)
    void deleteGoodsOrder(int id);
}
