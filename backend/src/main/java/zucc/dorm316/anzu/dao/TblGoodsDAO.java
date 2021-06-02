package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.GoodsEntity;

import java.util.List;

@Transactional
public interface TblGoodsDAO extends JpaRepository<GoodsEntity,Integer> {

    @Query(value = "select * from goods where id=?",nativeQuery = true)
    GoodsEntity findById(int id);

    @Query(value = "select * from goods where mode=?",nativeQuery = true)
    List<GoodsEntity> findAllByMode(int mode);;

    @Query(value = "select * from goods where merchant_id=?",nativeQuery = true)
    List<GoodsEntity> findAllByMerchant(int merchant_id);

    @Query(value = "select * from goods where category_id=?",nativeQuery = true)
    List<GoodsEntity> findAllByCategory(int category_id);

    @Modifying
    @Query(value = "insert into goods(goods_name,category_id,merchant_id,intro,status,pic_url,mode,deposit,stock,sales) values(?,?,?,?,?,?,?,?,?,?)",nativeQuery = true)
    void addGoods(String goods_name,int category_id,int merchant_id,String intro,int status,String pic_url,int mode,double deposit,int stock,int sales);

    @Modifying
    @Query(value = "update category set goods_name=?,category_id=?,merchant_id=?,intro=?,status=?,pic_url=?,mode=?,deposit=?,stock=?,sales = ? where id = ?",nativeQuery = true)
    void modifyGoods(String goods_name,int category_id,int merchant_id,String intro,int status,String pic_url,int mode,double deposit,int stock,int sales,int id);

    @Modifying
    @Query(value = "delete from goods where id=?",nativeQuery = true)
    void deleteById(int id);
}
