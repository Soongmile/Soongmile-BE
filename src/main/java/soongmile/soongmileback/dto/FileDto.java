package soongmile.soongmileback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDto {

    private Long id;
    private String filePath;
    private String fileName;
}
