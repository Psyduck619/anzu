package zucc.dorm316.anzu.service.Impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblGoodsStatisticDAO;
import zucc.dorm316.anzu.entity.GoodsStatisticsEntity;
import zucc.dorm316.anzu.service.TblGoodsStatisticService;

@Service
@Transactional
@Component
public class TblGoodsStatisticServiceImpl implements TblGoodsStatisticService {
    private TblGoodsStatisticDAO tblGoodsStatisticDAO;

    @Override
    public GoodsStatisticsEntity findById(int id) {
        return tblGoodsStatisticDAO.findById(id);
    }

    @Override
    public GoodsStatisticsEntity findByGoodsId(int goods_id) {
        return tblGoodsStatisticDAO.findByGoodsId(goods_id);
    }

    @Override
    public void addGoodsStatistic(int goods_id, int rate_num, int good_rate, int mid_rate, int bad_rate, int desc_star, int logistics_star, int attitude_star) {
        tblGoodsStatisticDAO.addGoodsStatistic(goods_id, rate_num, good_rate, mid_rate, bad_rate, desc_star, logistics_star, attitude_star);
    }

    @Override
    public void modifyGoodsStatistic(int goods_id, int rate_num, int good_rate, int mid_rate, int bad_rate, int desc_star, int logistics_star, int attitude_star, int id) {
        tblGoodsStatisticDAO.modifyGoodsStatistic(goods_id, rate_num, good_rate, mid_rate, bad_rate, desc_star, logistics_star, attitude_star, id);
    }

    @Override
    public void deleteGoodsStatistic(int id) {
        tblGoodsStatisticDAO.deleteGoodsStatistic(id);
    }
}
