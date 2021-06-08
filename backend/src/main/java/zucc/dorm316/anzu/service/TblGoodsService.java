package zucc.dorm316.anzu.service;


import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.GoodsEntity;
import java.util.List;

@Transactional
public interface TblGoodsService {
    List<GoodsEntity> findAll();
    List<GoodsEntity> findAllForAdmin();
    List<GoodsEntity> findAllByCategory(int category_id);
    List<GoodsEntity> findAllByMerchant(int merchant_id);
    List<GoodsEntity> findAllByMode(int mode);
    GoodsEntity findById(int goods_id);
    GoodsEntity findByNameAndMerchant(String name, int merchant_id);
    void addGoods(String goods_name,double price, int category_id,int merchant_id,String intro,int status,String pic_url,int mode,double deposit,int stock,int sales,int lease_time);
    void modifyGoods(String goods_name,double price,int category_id,int merchant_id,String intro,int status,String pic_url,int mode,double deposit,int stock,int sales,int lease_time, int id);
    void deleteById(int id);
}
