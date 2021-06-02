package zucc.dorm316.anzu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zucc.dorm316.anzu.dao.TblCategoryDAO;
import zucc.dorm316.anzu.entity.CategoryEntity;
import zucc.dorm316.anzu.service.TblCategoryService;

import java.util.List;

@Service
@Transactional
@Component
public class TblCategoryServiceImpl implements TblCategoryService {
    @Autowired
    private TblCategoryDAO tblCategoryDAO;

    @Override
    public List<CategoryEntity> findAll() {
        return tblCategoryDAO.findAll();
    }

    @Override
    public CategoryEntity findById(int id) {
        return tblCategoryDAO.findById(id);
    }

    @Override
    public void addCategory(String name) {
        tblCategoryDAO.addCategory(name);
    }

    @Override
    public void modifyCategory(String name, int id) {
        tblCategoryDAO.modifyCategory(name,id);
    }

    @Override
    public void deleteById(int id) {
        tblCategoryDAO.deleteById(id);
    }
}
