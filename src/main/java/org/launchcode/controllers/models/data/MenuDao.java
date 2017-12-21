package org.launchcode.controllers.models.data;

import org.launchcode.controllers.models.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuDao extends CrudRepository<Menu, Integer> {
}
