package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.UserAddressEntity;

import java.util.List;
@Transactional
public interface TblUserAddressDAO extends JpaRepository<UserAddressEntity,Integer> {
    @Query(value = "select * from user_address where user_id = ?",nativeQuery = true)
    List<UserAddressEntity> findAllByUserid(int user_id);

    @Query(value = "select * from user_address where id = ?",nativeQuery = true)
    UserAddressEntity findById(int id);

    @Modifying
    @Query(value = "insert into user_address(user_id,prov,city,detail,name,tel,sign) values (?,?,?,?,?,?,?)",nativeQuery = true)
    void addUserAddress(int user_id,String prov,String city,String detail,String name,String tel);

    @Modifying
    @Query(value = "delete from user_address where id = ?",nativeQuery = true)
    void deleteUserAddressById(int id);

    @Modifying
    @Query(value = "update user_address set user_id = ?,prov=?,city=?,detail=?,name=?,tel=? where  id= ?",nativeQuery = true)
    void modifyUserAddress(int user_id,String prov,String city,String detail,String name,String tel,int id);
}
