package soongmile.soongmileback.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AwsS3UploadService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String save(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), objectMetadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }

    public Map<String, String> save(List<MultipartFile> multipartFile) throws IOException {
        // key: fileName, value: url
        Map<String, String> map = new HashMap<>();

        for (MultipartFile file : multipartFile) {
            String url = save(file);
            map.put(file.getOriginalFilename(), url);
        }

        return map;
    }
}
