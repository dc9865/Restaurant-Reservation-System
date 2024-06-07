package com.dancodes.restaurantreservationsystem.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dancodes.restaurantreservationsystem.dto.TableCreateRequest;
import com.dancodes.restaurantreservationsystem.dto.TableUpdateRequest;
import com.dancodes.restaurantreservationsystem.model.Restaurant;
import com.dancodes.restaurantreservationsystem.model.TableStatus;
import com.dancodes.restaurantreservationsystem.model.Tables;
import com.dancodes.restaurantreservationsystem.repository.TablesRepository;

@Service
public class TablesManagementServiceImpl implements TablesManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(TablesManagementServiceImpl.class);
    private TablesRepository tablesRepository;
    private RestaurantService restaurantService;

    public TablesManagementServiceImpl(TablesRepository tablesRepository, RestaurantService restaurantService) {
        this.tablesRepository = tablesRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public Set<Tables> getTables(Long restaurantId) {
        Set<Tables> tables = tablesRepository.findByRestaurantId(restaurantId)
                        .orElseThrow(() -> new IllegalStateException(
                            "restaurant with id " + restaurantId + "does not exist"
                        ));
        return tables;
    }

    @Override
    public Tables getTableById(Long tableId, Long restaurantId) {
        Tables table = tablesRepository.findByIdAndRestaurantId(tableId, restaurantId)
                    .orElseThrow(() -> new IllegalStateException(
                        "Table not found for the specified restaurant"
                    ));

        return table;
    }

    @Override
    public Tables createTable(Long restaurantId, TableCreateRequest request) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        LOGGER.info("restaurant with id " + restaurantId + "is found, now adding a table");
        Tables table = new Tables();
        table.setCapacity(request.getCapacity());
        table.setStatus(TableStatus.AVAILABLE);
        table.setRestaurant(restaurant);
        return tablesRepository.save(table);
    }

    @Override
    public Tables updateTable(Long tableId, Long restaurantId, TableUpdateRequest tableUpdateRequest) {
        Tables table = getTableById(restaurantId, tableId);
        LOGGER.info("Table found for the specified restaurant");
        LOGGER.info("now updating table id: " + tableId + "for restuarant id: " + restaurantId);
        if (tableUpdateRequest.getCapacity() > 0) {
            table.setCapacity(tableUpdateRequest.getCapacity());
        }

        if (tableUpdateRequest.getTableStatus() != null) {
            table.setStatus(tableUpdateRequest.getTableStatus());
        }
        
        return table;
    }

}
