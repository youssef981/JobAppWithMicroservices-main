package com.atlas.jobsms.job.mapper;

import com.atlas.jobsms.job.Job;
import com.atlas.jobsms.job.dto.JobDTO;
import com.atlas.jobsms.job.external.Company;
import com.atlas.jobsms.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobDTO(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
