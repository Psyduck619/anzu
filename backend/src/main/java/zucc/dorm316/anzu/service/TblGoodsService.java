package zucc.dorm316.anzu.service;


import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.GoodsEntity;
import java.util.List;

@Transactional
public interface TblGoodsService {
    List<GoodsEntity> findAllByCategory(int category_id);
    List<GoodsEntity> findAllByMerchant(int merchant_id);
    List<GoodsEntity> findAllByMode(int mode);
    GoodsEntity findById(int goods_id);
    void addGoods(String goods_name,int category_id,int merchant_id,String intro,int status,String pic_url,int mode,double deposit,int stock,int sales);
    void modifyGoods(String goods_name,int category_id,int merchant_id,String intro,int status,String pic_url,int mode,double deposit,int stock,int sales,int id);
    void deleteById(int id);
}
