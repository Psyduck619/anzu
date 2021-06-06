package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblMerchantAddressDAO;
import zucc.dorm316.anzu.entity.MerchantAddressEntity;
import zucc.dorm316.anzu.service.TblMerchantAddressService;
import java.util.List;

@Service
@Transactional
@Component
public class TblMerchantAddressServiceImpl implements TblMerchantAddressService {
    @Autowired
    private TblMerchantAddressDAO tblMerchantAddressDAO;
    @Override
    public List<MerchantAddressEntity> findAllByMerchantId(int merchant_id) {
        return tblMerchantAddressDAO.findAllByMerchantId(merchant_id);
    }

    @Override
    public MerchantAddressEntity findDefaultByMerchantId(int merchant_id) {
        return tblMerchantAddressDAO.findDefaultByMerchantId(merchant_id);
    }

    @Override
    public MerchantAddressEntity findById(int id) {
        return tblMerchantAddressDAO.findById(id);
    }

    @Override
    public void addMerchantAddress(int merchant_id, String prov, String city, String area,String detail, String name, String tel,int is_default,String address_code) {
        tblMerchantAddressDAO.addMerchantAddress(merchant_id, prov, city, area,detail, name, tel,is_default,address_code);
    }

    @Override
    public void deleteMerchantAddressById(int id) {
        tblMerchantAddressDAO.deleteMerchantAddressById(id);
    }


    @Override
    public void modifyMerchantAddress(int merchant_id, String prov, String city,String area, String detail, String name, String tel, int is_default,String address_code,int id) {
        tblMerchantAddressDAO.modifyMerchantAddress(merchant_id, prov, city, area,detail, name, tel, is_default,address_code,id);
    }
}
