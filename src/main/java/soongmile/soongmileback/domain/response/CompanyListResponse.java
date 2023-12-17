package soongmile.soongmileback.domain.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyListResponse {

    private int year;
    private String size;
    private String name;

    @Builder
    public CompanyListResponse(int year, String size, String name) {
        this.year = year;
        this.size = size;
        this.name = name;
    }
}
