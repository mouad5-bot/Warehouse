package com.progresssoft.warehouse.service.impl;

import com.progresssoft.warehouse.service.IMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MapperImpl implements IMapper {

    private final ModelMapper modelMapper;

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public <D> List<D> mapList(List<?> source, Class<D> destinationType) {
        return source.stream()
                .map(item -> modelMapper.map(item, destinationType))
                .toList();
    }

}