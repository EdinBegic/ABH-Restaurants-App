package atlantbh.restaurants.repositories;

import atlantbh.restaurants.models.Category;
import atlantbh.restaurants.models.filters.CategoryFilterBuilder;
import atlantbh.restaurants.models.sortkeys.CategorySortKeys;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends BaseRepositoryImpl<Category,CategorySortKeys, CategoryFilterBuilder> {
}
