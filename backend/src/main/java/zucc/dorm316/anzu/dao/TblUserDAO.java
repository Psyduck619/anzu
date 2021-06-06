package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.UserEntity;

@Transactional
public interface TblUserDAO extends JpaRepository<UserEntity,Integer> {

    @Query(value = "select * from user where id = ?",nativeQuery = true)
    UserEntity findByUserId(int id);

    @Query(value = "select * from user where account = ?",nativeQuery = true)
    UserEntity findByUserAccount(String Account);

    @Modifying
    @Query(value = "delete from user where id = ?",nativeQuery = true)
    void deleteUserByUserid(int userid);

    @Modifying
    @Query(value = "delete from user where account = ?",nativeQuery = true)
    void deleteUserByUserAccount(String Account);

    @Modifying
    @Query(value = "insert into user(account,password,username,balance) values (?,?,?,?)",nativeQuery = true)
    void addUser(String account,String password,String userName,double balance);

    @Modifying
    @Query(value = "update user set account = ?, password = ?,username=?,balance=? where id =?",nativeQuery = true)
    void modifyUser(String account,String password,String userName,double balance,int id);
}
