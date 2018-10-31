package atlantbh.restaurants.services;

import atlantbh.restaurants.models.Category;
import atlantbh.restaurants.models.filters.CategoryFilterBuilder;
import atlantbh.restaurants.models.sortkeys.CategorySortKeys;
import atlantbh.restaurants.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends  BaseService<Category, CategorySortKeys, CategoryFilterBuilder, CategoryRepository> {
}
