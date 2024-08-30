package LifeLeopard.TeamShop.Service;

import LifeLeopard.TeamShop.Models.Category;
import LifeLeopard.TeamShop.Responsibility.CategoryReps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryReps categoryReps;
    public Optional<Category> getCategoryById(int id){
        return categoryReps.findById(id);
    }
    public void Save(Category category){
        categoryReps.save(category);
    }
    public List<Category> getAllCategoryOn(){
        return categoryReps.findAllByStatus(1);
    }
    public List<Category> getAllCategory(){
        return categoryReps.findAll();
    }

}
