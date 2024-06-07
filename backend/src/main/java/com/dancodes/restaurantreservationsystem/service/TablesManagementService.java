package com.dancodes.restaurantreservationsystem.service;

import java.util.Set;

import com.dancodes.restaurantreservationsystem.dto.TableCreateRequest;
import com.dancodes.restaurantreservationsystem.dto.TableUpdateRequest;
import com.dancodes.restaurantreservationsystem.model.Tables;

public interface TablesManagementService {

    Set<Tables> getTables(Long restaurantId);

    Tables getTableById(Long restaurantId, Long tableId);

    Tables createTable(Long restaurantId, TableCreateRequest tableCreateRequest);

    Tables updateTable(Long restaurantId, Long tableId, TableUpdateRequest tableUpdateRequest);
    

}
