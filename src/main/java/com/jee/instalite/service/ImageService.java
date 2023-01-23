package com.jee.instalite.service;

import com.jee.instalite.model.Image;
import com.jee.instalite.web.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {




    String uploadImageToFileSystem(MultipartFile file , ImageDto imageDto) throws IOException;


      Image getImageById(Long id);

      List<Image> getAllImages();
      void updateImage(MultipartFile image , ImageDto imageDto ,Long imageId) throws IOException ;

    byte[] downloadImageFromFileSystem(String fileName) throws IOException;

    void deleteImage(Long id ) throws IOException ;



/*    List<Image> getImagesByUser(Long userId);*/

}
