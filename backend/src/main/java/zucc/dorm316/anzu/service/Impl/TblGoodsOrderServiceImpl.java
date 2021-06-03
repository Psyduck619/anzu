package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblGoodsOrderDAO;
import zucc.dorm316.anzu.entity.GoodsOrderEntity;
import zucc.dorm316.anzu.service.TblGoodsOrderService;

import java.util.Date;
import java.util.List;
@Service
@Transactional
@Component
public class TblGoodsOrderServiceImpl implements TblGoodsOrderService {
    @Autowired
    private TblGoodsOrderDAO tblGoodsOrderDAO;

    @Override
    public GoodsOrderEntity findById(int id) {
        return tblGoodsOrderDAO.findById(id);
    }

    @Override
    public List<GoodsOrderEntity> findByUserId(int user_id) {
        return tblGoodsOrderDAO.findByUserId(user_id);
    }

    @Override
    public List<GoodsOrderEntity> findByGoodsId(int goods_id) {
        return tblGoodsOrderDAO.findByGoodsId(goods_id);
    }

    @Override
    public List<GoodsOrderEntity> findByMerchantId(int merchant_id) {
        return tblGoodsOrderDAO.findByMerchantId(merchant_id);
    }

    @Override
    public void addGoodsOrder(int user_id, int goods_id, int user_address, int merchant_address, int order_status, int goods_num, double goods_total_price, Date receiving_time, int lease_time, double deposit) {
        tblGoodsOrderDAO.addGoodsOrder(user_id, goods_id, user_address, merchant_address, order_status, goods_num, goods_total_price, receiving_time, lease_time, deposit);
    }

    @Override
    public void modifyGoodsOrder(int user_id, int goods_id, int user_address, int merchant_address, int order_status, int goods_num, double goods_total_price, Date receiving_time, int lease_time, double deposit, int id) {
        tblGoodsOrderDAO.modifyGoodsOrder(user_id, goods_id, user_address, merchant_address, order_status, goods_num, goods_total_price, receiving_time, lease_time, deposit, id);
    }

    @Override
    public void deleteGoodsOrder(int id) {
        tblGoodsOrderDAO.deleteGoodsOrder(id);
    }
}
