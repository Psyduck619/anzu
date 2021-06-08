package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.CommentStatisticEntity;

@Transactional
public interface TblCommentStatisticDAO extends JpaRepository<CommentStatisticEntity,Integer> {
    @Query(value = "select * from comment_statistic where id=?",nativeQuery = true)
    CommentStatisticEntity findById(int id);

    @Query(value = "select * from comment_statistic where order_id=?",nativeQuery = true)
    CommentStatisticEntity findByOrderId(int order_id);

    @Modifying
    @Query(value = "insert into comment_statistic(order_id,content, rate,desc_star,logistics_star,attitude_star) values (?,?,?,?,?,?)",nativeQuery = true)
    void addCommentStatistic(int order_id,String content,int rate,int des_star,int logistics_star,int attitude_star);

    @Modifying
    @Query(value = "update comment_statistic set order_id = ?, content=?, rate=?, desc_star=?, logistics_star=?, attitude_star=? where  id= ?",nativeQuery = true)
    void modifyCommentStatistics(int order_id,String content,int rate,int des_star,int logistics_star,int attitude_star,int id);

    @Modifying
    @Query(value = "delete from comment_statistic where id = ?",nativeQuery = true)
    void deleteById(int id);

    @Modifying
    @Query(value = "delete from comment_statistic where order_id = ?",nativeQuery = true)
    void deleteByOrderId(int id);
}
