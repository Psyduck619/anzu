package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.AftersalesEntity;
@Transactional
public interface TblAftersalesDAO {

    @Query(value = "select * from aftersale where id = ?",nativeQuery = true)
    AftersalesEntity findById(int id);

    @Query(value = "select * from aftersale where id = ?",nativeQuery = true)
    AftersalesEntity findByOrderId(int order_id);

    @Modifying
    @Query(value = "insert into aftersale(order_id,refund_type,goods_status,reason,processing_status,feedback) values (?,?,?,?,?,?)",nativeQuery = true)
    void addAftersales(int order_id,int refund_type,int goods_status,String  reason,int processing_status,String feedback);

    @Modifying
    @Query(value = "update aftersale set order_id = ?, refund_type = ?, goods_status = ?,reason=?,processing_status=?,feedback=? where id =?",nativeQuery = true)
    void modifyAftersales(int order_id,int refund_type,int goods_status,String  reason,int processing_status,String feedback,int id);


    @Modifying
    @Query(value = "delete from aftersales where id = ?",nativeQuery = true)
    void deleteById(int id);
}
