package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.UserEntity;


@Transactional
public interface TblUserService {
    UserEntity findByUserId(int id);
    UserEntity findByUserAccount(String Account);
    void deleteUserByUserid(int userid);
    void deleteUserByUserAccount(String Account);
    void addUser(String account,String password,String userName,double balance);
    void modifyUser(int id,String account,String password,String userName,double balance);
}
