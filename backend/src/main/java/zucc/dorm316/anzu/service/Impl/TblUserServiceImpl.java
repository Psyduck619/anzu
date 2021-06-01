package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblUserDAO;
import zucc.dorm316.anzu.entity.UserEntity;
import zucc.dorm316.anzu.service.TblUserService;

@Service
@Transactional
@Component
public class TblUserServiceImpl implements TblUserService {
    @Autowired
    private TblUserDAO tblUserDAO;

    @Override
    public UserEntity findByUserAccount(String Account) {
        return tblUserDAO.findByUserAccount(Account);
    }

    @Override
    public void deleteUserByUserid(int userid) {
        tblUserDAO.deleteUserByUserid(userid);
    }

    @Override
    public void deleteUserByUserAccount(String Account) {
        tblUserDAO.deleteUserByUserAccount(Account);
    }

    @Override
    public void addUser(String account, String password, String userName, int sex,String tel, String email, double balance) {
        tblUserDAO.addUser(account,password,userName,sex,tel,email,balance);
    }

    @Override
    public void modifyUser(int id, String account, String password, String userName, int sex,String tel, String email, double balance) {
        tblUserDAO.modifyUser(account,password,userName,sex,tel,email,balance,id);
    }
}