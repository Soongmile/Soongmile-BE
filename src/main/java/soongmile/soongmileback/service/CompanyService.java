package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.domain.response.CompanyListResponse;
import soongmile.soongmileback.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<CompanyListResponse> getCompanyList() {
        return companyRepository.findAll().stream()
                .map(company ->
                        new CompanyListResponse(
                                company.getYear(),
                                company.getSize(),
                                company.getName()
                        )).collect(Collectors.toList());
    }

    public List<CompanyListResponse> getCompanyListBySize(String size) {
        return companyRepository.findBySize(size).stream()
                .map(company ->
                        new CompanyListResponse(
                                company.getYear(),
                                company.getSize(),
                                company.getName()
                        )).collect(Collectors.toList());
    }
}
