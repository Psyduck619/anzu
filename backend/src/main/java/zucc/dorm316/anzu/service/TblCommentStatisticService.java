package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.CommentStatisticEntity;
@Transactional
public interface TblCommentStatisticService {
    CommentStatisticEntity findById(int id);
    CommentStatisticEntity findByOrderId(int order_id);
    void addCommentStatistic(int order_id,String content,int rate,int des_star,int logistics_star,int attitude_star);
    void modifyCommentStatistics(int order_id,String content,int rate,int des_star,int logistics_star,int attitude_star,int id);
    void deleteById(int id);
    void deleteByOrderId(int order_id);
}
