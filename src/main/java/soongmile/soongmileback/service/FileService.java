package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import soongmile.soongmileback.domain.FileEntity;
import soongmile.soongmileback.dto.FileDto;
import soongmile.soongmileback.repository.FileRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final ResourceLoader resourceLoader;
    private final FileRepository fileRepository;
    private final AwsS3UploadService awsS3UploadService;

    private static final String path = "/resources/static/files/";

    public FileDto saveFile(MultipartFile file) throws IOException {

        // 파일의 원래이름 -> test.img
        String originalFilename = file.getOriginalFilename();

        // 실제로 저장될 파일 이름
        String uuid = UUID.randomUUID().toString();

        // 확장자 .img
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 저장될 형태의 파일 이름
        String saveFileName = uuid + extension;

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource1 = classLoader.getResource("");
        System.out.println(resource1.getPath());
        String realPath = resourceLoader.getResource("classpath:").getURI().getPath();


        String filePath = realPath + "/" + saveFileName;

        file.transferTo(new File(filePath));

        FileEntity save = fileRepository.save(FileEntity.builder()
                .fileName(originalFilename)
                .filePath(filePath)
                .build());

        return FileDto.builder()
                .id(save.getId())
                .fileName(originalFilename)
                .filePath(filePath)
                .build();
    }

    public FileDto save(MultipartFile multipartFile) throws IOException {
        String url = awsS3UploadService.save(multipartFile);

        FileEntity fileEntity = FileEntity.builder()
                .fileName(multipartFile.getOriginalFilename())
                .filePath(url)
                .build();

        FileEntity save = fileRepository.save(fileEntity);
        return FileDto.builder()
                .fileName(save.getFileName())
                .filePath(save.getFilePath())
                .id(save.getId())
                .build();
    }

    public List<FileDto> save(List<MultipartFile> multipartFile) throws IOException {
       List<FileDto> fileDtoList = new ArrayList<>();
        for (MultipartFile file : multipartFile) {
            fileDtoList.add(save(file));
        }

        return fileDtoList;
    }
}
