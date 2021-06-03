package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import zucc.dorm316.anzu.dao.TblAftersalesDAO;
import zucc.dorm316.anzu.entity.AftersalesEntity;
import zucc.dorm316.anzu.service.TblAftersalesService;

public class TblAftersalesServiceImpl implements TblAftersalesService {

    @Autowired
    private TblAftersalesDAO tblAftersalesDAO;

    @Override
    public AftersalesEntity findById(int id) {
        return tblAftersalesDAO.findById(id);
    }

    @Override
    public AftersalesEntity findByOrderId(int order_id) {
        return tblAftersalesDAO.findByOrderId(order_id);
    }

    @Override
    public void addAftersales(int order_id, int refund_type, int goods_status, String reason, int processing_status, String feedback) {
        tblAftersalesDAO.addAftersales(order_id, refund_type, goods_status, reason, processing_status, feedback);
    }

    @Override
    public void modifyAftersales(int order_id, int refund_type, int goods_status, String reason, int processing_status, String feedback, int id) {
        tblAftersalesDAO.modifyAftersales(order_id, refund_type, goods_status, reason, processing_status, feedback, id);
    }

    @Override
    public void deleteById(int id) {
        tblAftersalesDAO.deleteById(id);
    }
}
