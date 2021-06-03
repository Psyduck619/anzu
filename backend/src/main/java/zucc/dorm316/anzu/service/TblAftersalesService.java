package zucc.dorm316.anzu.service;

import zucc.dorm316.anzu.entity.AftersalesEntity;

public interface TblAftersalesService {
    AftersalesEntity findById(int id);
    AftersalesEntity findByOrderId(int order_id);
    void addAftersales(int order_id,int refund_type,int goods_status,String  reason,int processing_status,String feedback);
    void modifyAftersales(int order_id,int refund_type,int goods_status,String  reason,int processing_status,String feedback,int id);
    void deleteById(int id);
}
