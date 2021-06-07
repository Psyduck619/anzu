package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.GoodsOrderEntity;

import java.util.Date;
import java.util.List;

@Transactional
public interface TblGoodsOrderService {
    GoodsOrderEntity findById(int id);
    List<GoodsOrderEntity> findAll();
    List<GoodsOrderEntity> findByUserId(int user_id);
    List<GoodsOrderEntity> findByGoodsId(int goods_id);
    List<GoodsOrderEntity> findByMerchantId(int merchant_id);
    void addGoodsOrder(int user_id, int goods_id, int merchant_id,int user_address, int merchant_address, int order_status, int goods_num,double goods_total_price, Date receiving_time, int lease_time,double deposit);
    void modifyGoodsOrder(int user_id, int goods_id, int merchant_id,int user_address, int merchant_address, int order_status, int goods_num, double goods_total_price, Date receiving_time, int lease_time,double deposit,int id);
    void deleteGoodsOrder(int id);
}
