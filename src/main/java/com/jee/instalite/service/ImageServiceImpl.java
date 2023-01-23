package com.jee.instalite.service;

import com.jee.instalite.exceptions.FileStorageException;
import com.jee.instalite.model.Image;
import com.jee.instalite.repositories.ImageRepository;
import com.jee.instalite.repositories.ImageRepositoryV1;
import com.jee.instalite.repositories.UserRepository;
import com.jee.instalite.web.dto.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepositoryV1 imageRepositoryV1;

    @Autowired
    private ImageRepository imageRepository;


    private final String fileStorageLocation;

    @Autowired
    public ImageServiceImpl(String fileStorageLocation) {
        this.fileStorageLocation = fileStorageLocation;

        try {
            Files.createDirectories(Paths.get(fileStorageLocation));
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }


/*    @Override
    public Image uploadImage(Image image , Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            image.setUser(user);
            image.setUserId(userId);

            return imageRepository.save(image);
        }else {
            throw new EntityNotFoundException();
        }

    }*/

/*    public String uploadImage(MultipartFile file , Long userId) throws IOException {
        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                        .userId(userId)
                .imageData(ImageUtils.compressImage(file.getBytes())).build());


        if (image != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }*/

/*
    public byte[] downloadImage(String fileName) {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
*/



    public String uploadImageToFileSystem(MultipartFile file , ImageDto imageDto) throws IOException {
        String filePath= fileStorageLocation + File.separator + file.getOriginalFilename();

        Image image = imageRepository.save(Image.builder()
                        .name(imageDto.getName())
                        .description(imageDto.getDescription())
                        .type(file.getContentType())
                        .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (image != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Image> fileData = imageRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }



    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }


    public void updateImage(MultipartFile file, ImageDto imageDto, Long imageId) throws IOException {
        Optional<Image> imageData = imageRepository.findById(imageId);
        if (imageData.isPresent()) {
            Image image = imageData.get();
            image.setName(imageDto.getName());
            image.setDescription(imageDto.getDescription());
            image.setType(file.getContentType());
            imageRepository.save(image);
            String filePath = imageData.get().getFilePath();
            Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        }
    }


    public void deleteImage(Long id) throws IOException {
        Optional<Image> imageData = imageRepository.findById(id);
        if (imageData.isPresent()) {
            imageRepository.delete(imageData.get());
            String filePath = imageData.get().getFilePath();
            Files.delete(Paths.get(filePath));
        }
    }

/*    @Override
    public List<Image> getImagesByUser(Long userId) {
        return imageRepository.findByUserId(userId);
    }*/

}
