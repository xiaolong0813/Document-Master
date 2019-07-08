package com.translation.salestranslation.model;

import org.springframework.data.repository.CrudRepository;

// * spring 可以将接口包装成类
public interface UploadFileRepository extends CrudRepository<UploadFile, Long> {
    Iterable<UploadFile> findByStatus(Integer status);
    Iterable<UploadFile> findAllByOrderByIdDesc();
    Iterable<UploadFile> findByFolderIdOrderByIdDesc(Integer folderId);
    void deleteByFolderId(Integer id);
}
