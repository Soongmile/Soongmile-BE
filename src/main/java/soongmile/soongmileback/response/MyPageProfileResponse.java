package soongmile.soongmileback.response;

import lombok.Builder;
import lombok.Data;
import soongmile.soongmileback.domain.Department;
import soongmile.soongmileback.domain.Field;
import soongmile.soongmileback.domain.Major;

@Data
public class MyPageProfileResponse {

    private String memberName;
    private Department department;
    private Major major;
    private Field field;

    @Builder
    public MyPageProfileResponse(String memberName, Department department, Major major, Field field) {
        this.memberName = memberName;
        this.department = department;
        this.major = major;
        this.field = field;
    }
}
