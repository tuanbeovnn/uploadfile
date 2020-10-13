package com.upload.api;

import com.upload.entity.UploadEntity;
import com.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class uploadAPI {
    @Autowired
    private UploadService uploadService;
    @GetMapping("/api/list")
    public String getList(Model model) {
        List<UploadEntity> uploadEntities = uploadService.getFile();
        model.addAttribute("docs", uploadEntities);
        return "doc";
    }
    @PostMapping("/api/upload")
    public String uploadFile(@RequestParam("files")MultipartFile[] files) {
        for (MultipartFile file : files) {
            uploadService.saveFile(file);
        }
        return "redirect:/";
    }

    @GetMapping("/api/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable long id) {
        UploadEntity uploadEntity = uploadService.getFile(id).get();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadEntity.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+uploadEntity.getName()+"\"")
                .body(new ByteArrayResource(uploadEntity.getData()));
    }
}
