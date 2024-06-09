package com.dancodes.restaurantreservationsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dancodes.restaurantreservationsystem.dto.TableCreateRequest;
import com.dancodes.restaurantreservationsystem.dto.TableUpdateRequest;
import com.dancodes.restaurantreservationsystem.exceptions.TableNotFound;
import com.dancodes.restaurantreservationsystem.model.Tables;
import com.dancodes.restaurantreservationsystem.service.TablesManagementService;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/restaurants/{restaurantId}/tables")
public class TablesManagementRestController {

    private final TablesManagementService tablesManagementService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantRestController.class);

    public TablesManagementRestController(TablesManagementService tablesManagementService) {
        this.tablesManagementService = tablesManagementService;
    }

    @GetMapping("/search")
    public ResponseEntity<Set<Tables>> findTables(@PathVariable("restaurantId") Long restaurantId) {
        LOGGER.info("getTables() is invoked");
        try {
            Set<Tables> tables = tablesManagementService.getTables(restaurantId);
            return new ResponseEntity<>(tables, HttpStatus.OK);
        } catch (TableNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{tableId}")
    public ResponseEntity<Tables> findTableById(@PathVariable("tableId") Long tableId, @PathVariable("restaurantId") Long restaurantId) {
        LOGGER.info("getTableById is invoked for restaurnat id " + restaurantId + " and table id: " + tableId);
        try {
            Tables table = tablesManagementService.getTableById(tableId, restaurantId);
            return new ResponseEntity<>(table, HttpStatus.OK);
        } catch (TableNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Tables> createTable(@PathVariable("restaurantId") Long restaurantId, @RequestBody TableCreateRequest tableCreateRequest) {
        LOGGER.info("createTable() is invoked for restaurantId " + restaurantId + " and " + tableCreateRequest.toString());
        try {
            Tables table = tablesManagementService.createTable(restaurantId, tableCreateRequest);
            return new ResponseEntity<>(table, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{tableId}")
    public ResponseEntity<Tables> updateTable(@PathVariable("tableId") Long tableId, @PathVariable("restaurantId") Long restaurantId, @RequestBody TableUpdateRequest tableUpdateRequest) {
        LOGGER.info("updateTable() is invoked for restaurantId " + restaurantId + " and " + tableUpdateRequest.toString());
        try {
            Tables table = tablesManagementService.updateTable(tableId, restaurantId, tableUpdateRequest);
            return new ResponseEntity<>(table, HttpStatus.OK);
        } catch (TableNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}