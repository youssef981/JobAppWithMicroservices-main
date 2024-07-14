package com.atlas.companyms.company.impl;

import com.atlas.companyms.company.Company;

import java.util.List;

public interface  CompanyServices {
    List<Company> getCompanies();
    void addCompany(Company company);
    Company findCompanyID(Long cId);
    boolean deleteCompanyID(Long cId);
    boolean updateCompany(Long cId, Company updatedComp);
}
