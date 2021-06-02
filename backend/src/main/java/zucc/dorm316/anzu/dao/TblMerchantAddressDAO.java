package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.MerchantAddressEntity;
import zucc.dorm316.anzu.entity.MerchantEntity;
import java.util.List;

@Transactional
public interface TblMerchantAddressDAO extends JpaRepository<MerchantEntity,Integer> {
    @Query(value = "select * from merchant_address where merchant_id = ?",nativeQuery = true)
    List<MerchantAddressEntity> findAllByMerchantId(int merchant_id);

    @Query(value = "select * from merchant_address where id = ?",nativeQuery = true)
    MerchantAddressEntity findById(int id);

    @Modifying
    @Query(value = "insert into merchant_address(merchant_id,prov,city,area,detail,name,tel) values (?,?,?,?,?,?,?)",nativeQuery = true)
    void addMerchantAddress(int merchant_id,String prov,String city,String area,String detail,String name,String tel);

    @Modifying
    @Query(value = "delete from merchant_address where id = ?",nativeQuery = true)
    void deleteMerchantAddressById(int id);

    @Modifying
    @Query(value = "update merchant_address set merchant_id = ?,prov=?,city=?,area=?,detail=?,name=?,tel=? where  id= ?",nativeQuery = true)
    void modifyMerchantAddress(int merchant_id,String prov,String city,String area,String detail,String name,String tel,int id);
}
