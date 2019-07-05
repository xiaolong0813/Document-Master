package com.translation.salestranslation.model;

import org.springframework.data.repository.CrudRepository;

public interface TextRepository extends CrudRepository<Text, Long> {
    void deleteByFolderId(Integer id);
}
