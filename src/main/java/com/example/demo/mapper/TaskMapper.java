package com.example.demo.mapper;

import com.example.demo.dto.TaskDTO;
import com.example.demo.model.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    TaskDTO entityToDto(TaskEntity taskEntity);

    TaskEntity dtoToEntity(TaskDTO taskDto);

    List<TaskDTO> entityListToDtoList(List<TaskEntity> taskEntityList);
}
