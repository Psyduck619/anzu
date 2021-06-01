package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblCartDAO;
import zucc.dorm316.anzu.entity.CartEntity;
import zucc.dorm316.anzu.service.TblCartService;

import java.util.List;

@Service
@Transactional
@Component
public class TblCartServiceImpl implements TblCartService {
    @Autowired
    private TblCartDAO tblCartDAO;
    @Override
    public CartEntity findById(int id) {
        return tblCartDAO.findById(id);
    }

    @Override
    public List<CartEntity> findByUserId(int user_id) {
        return tblCartDAO.findByUserId(user_id);
    }

    @Override
    public void addCartGoods(int user_id, int goods_id, int goods_num) {
        tblCartDAO.addCartGoods(user_id, goods_id, goods_num);
    }

    @Override
    public void deleteCardGoodsById(int id) {
        tblCartDAO.deleteCardGoodsById(id);
    }

    @Override
    public void modifyGoodsNum(int user_id, int goods_id, int goods_num, int id) {
        tblCartDAO.modifyGoodsNum(user_id, goods_id, goods_num, id);
    }
}
