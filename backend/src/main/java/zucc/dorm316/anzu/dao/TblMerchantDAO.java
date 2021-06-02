package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.MerchantEntity;

@Transactional
public interface TblMerchantDAO extends JpaRepository<MerchantEntity,Integer> {
    @Query(value = "select * from merchant where account = ?",nativeQuery = true)
    MerchantEntity findByMerchantAccount(String Account);

    @Modifying
    @Query(value = "delete from merchant where id = ?",nativeQuery = true)
    void deleteMerchantByMerchantId(int id);

    @Modifying
    @Query(value = "delete from merchant where account = ?",nativeQuery = true)
    void deleteMerchantByMerchantAccount(String Account);

    @Modifying
    @Query(value = "insert into merchant(account,password,merchant_name,balance,admin_flag) values (?,?,?,?,?)",nativeQuery = true)
    void addMerchant(String account,String password,String merchant_name,double balance,int admin_flag);

    @Modifying
    @Query(value = "update merchant set account = ?, password = ?,merchant_name = ?, balance=?, admin_flag = ? where id =?",nativeQuery = true)
    void modifyMerchant(String account,String password,String merchant_name,double balance,int admin_flag, int id);
}
