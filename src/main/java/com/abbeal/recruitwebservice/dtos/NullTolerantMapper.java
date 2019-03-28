package com.abbeal.recruitwebservice.dtos;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NullTolerantMapper extends ModelMapper{

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        Object tmpSource = source;
        if(source == null){
            tmpSource = new Object();
        }

        return super.map(tmpSource, destinationType);
    }
}