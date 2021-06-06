package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.AftersalesEntity;

import java.util.List;

@Transactional
public interface TblAftersalesService {
    AftersalesEntity findById(int id);
    List<AftersalesEntity> findByUserId(int user_id);
    List<AftersalesEntity> findByMerchantId(int merchant_id);
    AftersalesEntity findByOrderId(int order_id);
    void addAftersales(int order_id,int goods_status,String  reason,int processing_status,String feedback);
    void modifyAftersales(int order_id,int goods_status,String  reason,int processing_status,String feedback,int id);
    void deleteById(int id);
}
