package com.upload.service;

import com.upload.entity.UploadEntity;
import com.upload.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UploadService {
    @Autowired
    private UploadRepository uploadRepository;
    public UploadEntity saveFile(MultipartFile file) {
        String docname = file.getOriginalFilename();
        try {
            UploadEntity doc = new UploadEntity(docname, file.getContentType(), file.getBytes());
            return uploadRepository.save(doc);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Optional<UploadEntity> getFile(long id) {
        return uploadRepository.findById(id);
    }
    public List<UploadEntity> getFile() {
        return uploadRepository.findAll();
    }
}
