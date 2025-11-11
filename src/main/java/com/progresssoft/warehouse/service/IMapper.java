package com.progresssoft.warehouse.service;
import java.util.List;

public interface IMapper {
    <D> D map(Object source, Class<D> destinationType);
    <D> List<D> mapList(List<?> source, Class<D> destinationType);
}
