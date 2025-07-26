package com.dustman.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dustman.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    public FileDto uploadFile(MultipartFile file) throws IOException {
//        Map<String, Object> options = ObjectUtils.asMap(
//            "folder", "dustman"
//    );

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap());
        return new FileDto((String) uploadResult.get("secure_url"),(String) uploadResult.get("public_id"));
    }

    public String deleteFile(String publicId) throws IOException {
        Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        return result.get("result").toString();
    }
}
