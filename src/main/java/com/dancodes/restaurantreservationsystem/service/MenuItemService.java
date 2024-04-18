package com.dancodes.restaurantreservationsystem.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.model.MenuItem;
import com.dancodes.restaurantreservationsystem.repository.MenuItemRepository;

import jakarta.transaction.Transactional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getMenuItems() {
        return menuItemRepository.findAll();
    }

    public void addNewMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        Boolean id_exists = menuItemRepository.existsById(id);
        if (!id_exists) {
            throw new IllegalStateException();
        }
        menuItemRepository.deleteById(id);
    }

    @Transactional
    public void updateMenuItem(Long menuItemId, String name, String description, double price) {
        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                            .orElseThrow(() -> new IllegalStateException(
                                "menu item with id " + menuItemId + " does not exist"
                            ));
        if ((name != null) && (name.length() > 0) && !Objects.equals(menuItem.getName(), name)) {
            menuItem.setName(name);
            System.out.println(name);
        }
        if ((description != null) && (description.length() > 0) && !Objects.equals(menuItem.getDescription(), description)) {
            menuItem.setDescription(description);
        }                   
        if ((price > 0) && (!Objects.equals(menuItem.getPrice(), price))) {
            menuItem.setPrice(price);
        }
    }
    
    
}
