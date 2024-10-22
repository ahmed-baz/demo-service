package org.demo.app.mapper;


import org.mapstruct.MappingTarget;

import java.util.List;


public interface BaseMapper<E, D> {

    List<D> entityListToDtoList(List<E> list);

    D entityToDto(E e);

    E dtoToEntity(D d);

    void updateEntity(D d, @MappingTarget E e);

    List<E> dtoListToEntityList(List<D> d);


}
