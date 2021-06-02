package zucc.dorm316.anzu.service;


import zucc.dorm316.anzu.entity.MerchantAddressEntity;
import java.util.List;

public interface TblMerchantAddressService {
    List<MerchantAddressEntity> findAllByMerchantId(int merchant_id);
    MerchantAddressEntity findById(int id);
    void addMerchantAddress(int merchant_id,String prov,String city,String area,String detail,String name,String tel);
    void deleteMerchantAddressById(int id);
    void modifyMerchantAddress(int merchant_id,String prov,String city,String area,String detail,String name,String tel,int id);
}
