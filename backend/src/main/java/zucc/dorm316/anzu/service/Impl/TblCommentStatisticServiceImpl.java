package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblCommentStatisticDAO;
import zucc.dorm316.anzu.entity.CommentStatisticEntity;
import zucc.dorm316.anzu.service.TblCommentStatisticService;
@Service
@Transactional
@Component
public class TblCommentStatisticServiceImpl implements TblCommentStatisticService {
    @Autowired
    private TblCommentStatisticDAO tblCommentStatisticDAO;

    @Override
    public CommentStatisticEntity findById(int id) {
        return tblCommentStatisticDAO.findById(id);
    }

    @Override
    public CommentStatisticEntity findByOrderId(int order_id) {
        return tblCommentStatisticDAO.findByOrderId(order_id);
    }

    @Override
    public void addCommentStatistic(int order_id, String content, int rate, int des_star, int logistics_star, int attitude_star) {
        tblCommentStatisticDAO.addCommentStatistic(order_id, content, rate, des_star, logistics_star, attitude_star);
    }

    @Override
    public void modifyCommentStatistics(int order_id, String content, int rate, int des_star, int logistics_star, int attitude_star, int id) {
        tblCommentStatisticDAO.modifyCommentStatistics(order_id, content, rate, des_star, logistics_star, attitude_star, id);
    }

    @Override
    public void deleteById(int id) {
        tblCommentStatisticDAO.deleteById(id);
    }

    @Override
    public void deleteByOrderId(int order_id) {
        tblCommentStatisticDAO.deleteByOrderId(order_id);
    }
}
