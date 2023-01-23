package com.jee.instalite.web;



import com.jee.instalite.model.Image;
import com.jee.instalite.service.ImageService;
import com.jee.instalite.service.UserService;
import com.jee.instalite.web.dto.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

/*
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces="text/html")

    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file , @RequestParam("user_id") Long userId) throws IOException {



        String uploadImage = imageService.uploadImage(file ,userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);

    }
*/

/*    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=imageService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }*/


    @PostMapping("/upload")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image")MultipartFile file , ImageDto imageDto) throws IOException {

        String uploadImage = imageService.uploadImageToFileSystem(file , imageDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

/*    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }*/


    @GetMapping("")

    public List<ImageDto> getAllImages() {
        List<Image> images = imageService.getAllImages();
        List<ImageDto> imageDtos = new ArrayList<>();

        for(Image image : images) {
            ImageDto imageDto = new ImageDto();
            imageDto.setName(image.getName());
            imageDto.setDescription(image.getDescription());
            imageDto.setUserId(image.getUserId());
            imageDto.setType(image.getType());
            imageDto.setFilePath(image.getFilePath());
            imageDto.setId(image.getId());
            imageDtos.add(imageDto);
        }

        return imageDtos;
    }


    @PutMapping("/{imageId}")
    public ResponseEntity<Object> updateImage(@RequestParam("image") MultipartFile file, ImageDto imageDto, @PathVariable Long imageId) throws IOException {
        try {
            imageService.updateImage(file, imageDto, imageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")

    public Image getImageById(@PathVariable Long id) {

        Image image = imageService.getImageById(id);


        return image ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) throws IOException {
        imageService.deleteImage(id);


        return ResponseEntity.noContent().build();

    }




    /*
    @RequestMapping(value = "/images/{id}", method = RequestMethod.DELETE)

    public void deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
    }
*/


/*    @RequestMapping(value = "/private-images", method = RequestMethod.GET)
    public List<ImageDto> getPrivateImages(@RequestHeader("Authorization") String authorization) {
        String[] credentials = decodeBasicAuthHeader(authorization);
        String email = credentials[0];
        String password = credentials[1];
        if (userService.authenticateUser(email, password)) {
            User user = (User) userService.loadUserByUsername(email);
            Long userId = user.getId();
            List<Image> images = imageService.getImagesByUser(userId);
            List<ImageDto> imageDTOs = new ArrayList<>();
            for (Image image : images) {
                ImageDto imageDto = new ImageDto();
                imageDto.setFileName(image.getFileName());
                imageDto.setCaption(image.getCaption());
                imageDto.setTags(image.getTags());
                imageDTOs.add(imageDto);
            }
            return imageDTOs;
        } else {
            throw new UnauthorizedException();
        }
    }

    private String[] decodeBasicAuthHeader(String header) {
        String[] credentials = new String[2];
        String encodedCredentials = header.split(" ")[1];
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));
        credentials[0] = decodedCredentials.split(":")[0];
        credentials[1] = decodedCredentials.split(":")[1];
        return credentials;
    }*/




}
