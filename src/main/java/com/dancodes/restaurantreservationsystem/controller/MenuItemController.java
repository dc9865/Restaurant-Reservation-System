package com.dancodes.restaurantreservationsystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dancodes.restaurantreservationsystem.model.MenuItem;
import com.dancodes.restaurantreservationsystem.service.MenuItemService;

@RestController
@RequestMapping(path = "/menuItem")
public class MenuItemController {
    private final MenuItemService menuItemService;
    
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/search")
    public List<MenuItem> getMenuItems() {
        return menuItemService.getMenuItems();
    }

    @PostMapping
    public void registerNewMenuItem(@RequestBody MenuItem menuItem) {
        menuItemService.addNewMenuItem(menuItem);
    }

    @DeleteMapping
    public void deleteMenuItem(@PathVariable("menuitem_id") Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
    } 

    @PutMapping
    public void updateMenuItem(@PathVariable("menuitem_id") Long menuItemId,
                                @RequestParam(required=false) String name,
                                @RequestParam(required=false) String description,
                                @RequestParam(required=false) double price) {

        menuItemService.updateMenuItem(menuItemId, name, description, price);
    }
    

    
    

    
}
