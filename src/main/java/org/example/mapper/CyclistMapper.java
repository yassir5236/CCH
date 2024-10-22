package org.example.mapper;


import org.example.dtos.CyclistDTO;
import org.example.entity.Cyclist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CyclistMapper {
    CyclistDTO toDto(Cyclist cyclist);
    Cyclist toEntity(CyclistDTO cyclistDTO);
}