package com.atlas.companyms.company;


import com.atlas.companyms.company.impl.CompanyServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyControllers {
    private CompanyServices companyServices;

    public CompanyControllers(CompanyServices companyService) {
        this.companyServices = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyServices.getCompanies());
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyServices.addCompany(company);
        return new ResponseEntity<>("New Company Added", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyID(@PathVariable Long id){
        Company company = companyServices.findCompanyID(id);
        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompanyID(@PathVariable Long id){
        boolean deleteStatus = companyServices.deleteCompanyID(id);
        if(!deleteStatus){
            return new ResponseEntity<>("Job ID Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted Company with ID: "+id, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedComp){
        boolean updateStatus = companyServices.updateCompany(id, updatedComp);
        if(!updateStatus){
            return new ResponseEntity<>("Job ID Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Updated Company details with ID: "+id, HttpStatus.OK);
    }

}
