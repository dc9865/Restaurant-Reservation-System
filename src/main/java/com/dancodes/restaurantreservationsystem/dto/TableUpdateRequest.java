package com.dancodes.restaurantreservationsystem.dto;

import com.dancodes.restaurantreservationsystem.model.TableStatus;

public class TableUpdateRequest {
    private Long tableId;
    private int capacity;
    private TableStatus tableStatus;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }
}
