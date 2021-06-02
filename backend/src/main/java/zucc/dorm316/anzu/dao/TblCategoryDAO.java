package zucc.dorm316.anzu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.CategoryEntity;

import java.util.List;

@Transactional
public interface TblCategoryDAO extends JpaRepository<CategoryEntity,Integer> {

    @Query(value = "select * from category where id=?",nativeQuery = true)
    CategoryEntity findById(int id);

    @Query(value = "select * from category",nativeQuery = true)
    List<CategoryEntity> findAll();

    @Modifying
    @Query(value = "insert into category(category_name) values(?)",nativeQuery = true)
    void addCategory(String name);

    @Modifying
    @Query(value = "update category set category_name = ? where id = ?",nativeQuery = true)
    void modifyCategory(String name, int id);

    @Modifying
    @Query(value = "delete from category where id=?",nativeQuery = true)
    void deleteById(int id);
}
