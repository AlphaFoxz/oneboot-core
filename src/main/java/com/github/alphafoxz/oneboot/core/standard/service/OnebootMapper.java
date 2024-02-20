package com.github.alphafoxz.oneboot.core.standard.service;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public @interface OnebootMapper {
}
