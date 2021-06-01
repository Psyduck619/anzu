package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.CartEntity;

import java.util.List;


@Transactional
public interface TblCartDAO extends JpaRepository<CartEntity,Integer> {

    @Query(value = "select * from cart where id = ?",nativeQuery = true)
    CartEntity findById(int id);

    @Query(value = "select * from cart where user_id = ?",nativeQuery = true)
    List<CartEntity> findByUserId(int user_id);

    @Modifying
    @Query(value = "insert into cart(user_id,goods_id,goods_num) values (?,?,?)",nativeQuery = true)
    void addCartGoods(int user_id,int goods_id,int goods_num);

    @Modifying
    @Query(value = "delete from cart where id = ?",nativeQuery = true)
    void deleteCardGoodsById(int id);

    @Modifying
    @Query(value = "update cart set user_id = ?, goods_id = ?,goods_num=? where id =?",nativeQuery = true)
    void modifyGoodsNum(int user_id, int goods_id, int goods_num, int id);
}
