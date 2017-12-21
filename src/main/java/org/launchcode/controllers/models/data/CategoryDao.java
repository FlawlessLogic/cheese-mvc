package org.launchcode.controllers.models.data;

import org.launchcode.controllers.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Integer> {
}
