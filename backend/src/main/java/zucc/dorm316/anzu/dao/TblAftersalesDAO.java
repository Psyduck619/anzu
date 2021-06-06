package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.AftersalesEntity;
import zucc.dorm316.anzu.entity.CartEntity;

import java.util.List;

@Transactional
public interface TblAftersalesDAO extends JpaRepository<AftersalesEntity,Integer> {

    @Query(value = "select * from aftersales where id = ?",nativeQuery = true)
    AftersalesEntity findById(int id);

    @Query(value = "select * from aftersales where id = ?",nativeQuery = true)
    AftersalesEntity findByOrderId(int order_id);

    @Query(value = "select a.feedback,a.goods_status,a.id\n" +
            ",a.order_id,a.processing_status, a.reason from aftersales a, goods_order g where g.user_id = ? AND a.order_id = g.id",nativeQuery = true)
    List<AftersalesEntity> findByUserId(int user_id);

    @Query(value = "select a.feedback,a.goods_status,a.id\n" +
            ",a.order_id,a.processing_status, a.reason from aftersales a, goods_order g where g.merchant_id = ? AND a.order_id = g.id",nativeQuery = true)
    List<AftersalesEntity> findByMerchantId(int merchant_id);

    @Modifying
    @Query(value = "insert into aftersales(order_id,goods_status,reason,processing_status,feedback) values (?,?,?,?,?)",nativeQuery = true)
    void addAftersales(int order_id,int goods_status,String  reason,int processing_status,String feedback);

    @Modifying
    @Query(value = "update aftersales set order_id = ?, goods_status = ?,reason=?,processing_status=?,feedback=? where id =?",nativeQuery = true)
    void modifyAftersales(int order_id,int goods_status,String  reason,int processing_status,String feedback,int id);


    @Modifying
    @Query(value = "delete from aftersales where id = ?",nativeQuery = true)
    void deleteById(int id);
}
