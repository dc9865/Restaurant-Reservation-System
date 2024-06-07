package com.dancodes.restaurantreservationsystem.util;

public interface Converter<DTO, Entity> {
    DTO convertToDTO(Entity entity);
    Entity convertTOEntity(DTO dto);
}
