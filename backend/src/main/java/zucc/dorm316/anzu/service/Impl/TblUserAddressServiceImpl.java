package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblUserAddressDAO;
import zucc.dorm316.anzu.dao.TblUserDAO;
import zucc.dorm316.anzu.entity.UserAddressEntity;
import zucc.dorm316.anzu.service.TblUserAddressService;

import java.util.List;

@Service
@Transactional
@Component
public class TblUserAddressServiceImpl implements TblUserAddressService {
    @Autowired
    private TblUserAddressDAO tblUserAddressDAO;

    @Override
    public List<UserAddressEntity> findAllByUserid(int user_id) {
        return tblUserAddressDAO.findAllByUserid(user_id);
    }

    @Override
    public UserAddressEntity findById(int id) {
        return tblUserAddressDAO.findById(id);
    }

    @Override
    public void addUserAddress(int user_id, String prov, String city, String detail, String name, String tel) {
        tblUserAddressDAO.addUserAddress(user_id, prov, city, detail, name, tel);
    }

    @Override
    public void deleteUserAddressById(int id) {
        tblUserAddressDAO.deleteUserAddressById(id);
    }

    @Override
    public void modifyUserAddress(int user_id, String prov, String city, String detail, String name, String tel, int id) {
        tblUserAddressDAO.modifyUserAddress(user_id, prov, city, detail, name, tel, id);
    }
}
