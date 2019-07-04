package com.translation.salestranslation.model;

import org.springframework.data.repository.CrudRepository;

// * spring 可以将接口包装成类
public interface PDFRepository extends CrudRepository<PDF, Long> {

    Iterable<PDF> findByStatus(Integer status);
    Iterable<PDF> findAllByOrderByIdDesc();

}
