package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblMerchantDAO;
import zucc.dorm316.anzu.entity.MerchantEntity;
import zucc.dorm316.anzu.service.TblMerchantService;

@Service
@Transactional
@Component
public class TblMerchantServiceImpl implements TblMerchantService {
    @Autowired
    private TblMerchantDAO tblMerchantDAO;


    @Override
    public MerchantEntity findByMerchantAccount(String Account) {
        return tblMerchantDAO.findByMerchantAccount(Account);
    }

    @Override
    public void deleteMerchantByMerchantId(int merchant_id) {
        tblMerchantDAO.deleteMerchantByMerchantId(merchant_id);
    }

    @Override
    public void deleteUserByMerchantAccount(String Account) {
        tblMerchantDAO.deleteMerchantByMerchantAccount(Account);
    }

    @Override
    public void addMerchant(String account, String password, String merchantName, double balance, int admin_flag) {
        tblMerchantDAO.addMerchant(account, password, merchantName, balance, admin_flag);
    }

    @Override
    public void modifyMerchant(int id, String account, String password, String merchantName, double balance, int admin_flag) {
        tblMerchantDAO.modifyMerchant(account,password,merchantName,balance,admin_flag,id);
    }
}
