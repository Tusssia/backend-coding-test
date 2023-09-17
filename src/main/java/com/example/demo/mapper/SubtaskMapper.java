package com.example.demo.mapper;

import com.example.demo.dto.SubtaskDTO;
import com.example.demo.model.SubtaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubtaskMapper {

    SubtaskMapper MAPPER = Mappers.getMapper(SubtaskMapper.class);

    SubtaskDTO entityToDto(SubtaskEntity subtaskEntity);

    SubtaskEntity dtoToEntity(SubtaskDTO subtaskDTO);
}
