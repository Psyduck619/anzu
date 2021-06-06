package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.MerchantEntity;

@Transactional
public interface TblMerchantService {
    MerchantEntity findByMerchantId(int id);
    MerchantEntity findByMerchantAccount(String Account);
    void deleteMerchantByMerchantId(int merchant_id);
    void deleteUserByMerchantAccount(String Account);
    void addMerchant(String account,String password,String merchantName,double balance,int admin_flag);
    void modifyMerchant(int id,String account,String password,String merchantName,double balance,int admin_flag);
}
