package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblGoodsDAO;
import zucc.dorm316.anzu.entity.GoodsEntity;
import zucc.dorm316.anzu.service.TblGoodsService;
import java.util.List;

@Service
@Transactional
@Component
public class TblGoodsServiceImpl implements TblGoodsService {
    @Autowired
    private TblGoodsDAO tblGoodsDAO;

    @Override
    public List<GoodsEntity> findAll() {
        return tblGoodsDAO.findAll();
    }

    @Override
    public List<GoodsEntity> findAllForAdmin() {
        return tblGoodsDAO.findAllForAdmin();
    }

    @Override
    public List<GoodsEntity> findAllByCategory(int category_id) {
        return tblGoodsDAO.findAllByCategory(category_id);
    }

    @Override
    public List<GoodsEntity> findAllByMerchant(int merchant_id) {
        return tblGoodsDAO.findAllByMerchant(merchant_id);
    }

    @Override
    public List<GoodsEntity> findAllByMode(int mode) {
        return tblGoodsDAO.findAllByMode(mode);
    }

    @Override
    public GoodsEntity findById(int goods_id) {
        return tblGoodsDAO.findById(goods_id);
    }

    @Override
    public GoodsEntity findByNameAndMerchant(String name, int merchant_id) {
        return tblGoodsDAO.findByNameAndMerchant(name,merchant_id);
    }

    @Override
    public void addGoods(String goods_name,double price, int category_id, int merchant_id, String intro, int status, String pic_url, int mode, double deposit, int stock, int sales,int lease_time) {
        tblGoodsDAO.addGoods(goods_name, price,category_id, merchant_id, intro, status, pic_url, mode, deposit, stock, sales,lease_time);
    }

    @Override
    public void modifyGoods(String goods_name, double price,int category_id, int merchant_id, String intro, int status, String pic_url, int mode, double deposit, int stock, int sales, int lease_time,int id) {
        tblGoodsDAO.modifyGoods(goods_name, price,category_id, merchant_id, intro, status, pic_url, mode, deposit, stock, sales,lease_time, id);
    }

    @Override
    public void deleteById(int id) {
        tblGoodsDAO.deleteById(id);
    }
}
