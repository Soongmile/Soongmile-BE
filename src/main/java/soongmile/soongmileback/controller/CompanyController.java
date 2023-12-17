package soongmile.soongmileback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.response.CompanyListResponse;
import soongmile.soongmileback.service.AIService;
import soongmile.soongmileback.service.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final AIService aiService;

    @GetMapping("/all")
    public List<CompanyListResponse> getCompanyList() {
        return companyService.getCompanyList();
    }

    @GetMapping
    public List<CompanyListResponse> getCompanyListBySize(@RequestParam(name = "size") String size) {
        return companyService.getCompanyListBySize(size);
    }

    @GetMapping("/detail")
    public String getCompanyInfo(@RequestParam(value = "name") String name) {
        return aiService.getInfo(name);
    }

}
