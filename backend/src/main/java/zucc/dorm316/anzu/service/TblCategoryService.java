package zucc.dorm316.anzu.service;

import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.entity.CategoryEntity;

import java.util.List;

@Transactional
public interface TblCategoryService {
    List<CategoryEntity> findAll();
    CategoryEntity findById(int id);
    void addCategory(String name);
    void modifyCategory(String name, int id);
    void deleteById(int id);
}
