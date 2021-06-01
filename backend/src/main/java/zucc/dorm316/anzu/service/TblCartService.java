package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.CartEntity;

import java.util.List;

@Transactional
public interface TblCartService {
    CartEntity findById(int id);
    List<CartEntity> findByUserId(int user_id);
    void addCartGoods(int user_id,int goods_id,int goods_num);
    void deleteCardGoodsById(int id);
    void modifyGoodsNum(int user_id, int goods_id, int goods_num, int id);
}
