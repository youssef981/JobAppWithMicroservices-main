package com.atlas.jobsms.job.external;


public class Company {

    private Long id;
    private String name;
    private String description;


    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String compDescription) {
        this.description = compDescription;
    }
}
