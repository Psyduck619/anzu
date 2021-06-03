package zucc.dorm316.anzu.service.Impl;

import zucc.dorm316.anzu.entity.CommentStatisticEntity;
import zucc.dorm316.anzu.service.TblCommentStatisticService;

public class TblCommentStatisticServiceImpl implements TblCommentStatisticService {
    @Override
    public CommentStatisticEntity findById(int id) {
        return null;
    }

    @Override
    public CommentStatisticEntity findByOrderId(int order_id) {
        return null;
    }

    @Override
    public void addCommentStatistic(int order_id, String content, int rate, int des_star, int logistics_star, int attitude_star) {

    }

    @Override
    public void modifyCommentStatistics(int order_id, String content, int rate, int des_star, int logistics_star, int attitude_star, int id) {

    }

    @Override
    public void deleteById(int id) {

    }
}
