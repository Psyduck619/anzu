package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.UserAddressEntity;
import java.util.List;

@Transactional
public interface TblUserAddressService{
    List<UserAddressEntity> findAllByUserid(int user_id);
    UserAddressEntity findDefaultAddressByUserId(int id);
    UserAddressEntity findById(int id);
    void addUserAddress(int user_id,String prov,String city,String area,String detail,String name,String tel,int is_default,String address_code);
    void deleteUserAddressById(int id);
    void modifyUserAddress(int user_id,String prov,String city,String area,String detail,String name,String tel,int is_default,String address_code,int id);
}
