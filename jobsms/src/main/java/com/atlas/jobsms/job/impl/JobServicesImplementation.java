package com.atlas.jobsms.job.impl;

import com.atlas.jobsms.job.Job;
import com.atlas.jobsms.job.JobRepository;
import com.atlas.jobsms.job.clients.CompanyClient;
import com.atlas.jobsms.job.clients.ReviewClient;
import com.atlas.jobsms.job.dto.JobDTO;
import com.atlas.jobsms.job.external.Company;
import com.atlas.jobsms.job.external.Review;
import com.atlas.jobsms.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServicesImplementation implements JobServices {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepo;

    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServicesImplementation(JobRepository jobRepo, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepo = jobRepo;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepo.findAll();

        return jobs.stream().map(this::convertToDTO).collect(Collectors.toList());
        /**
         * .stream() method is sequence of elements that can be processed in a pipeline.
         *
         * .map() is an operation that is applied to the stream.
         *   This method takes a function as an argument and this function argument is applied to each element of the stream.
         *
         * .collect() is used to collect the elements of the stream into a new collection.
         */
    }

    private JobDTO convertToDTO(Job job){
//        Company company = restTemplate.getForObject("http://COMPANYMS:8081/companies/" + job.getCompanyId(), Company.class);

//        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEWMS:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {});
//
//        List<Review> reviews = reviewResponse.getBody();

        Company company = companyClient.getCompany(job.getCompanyId());

        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper.mapToJobDTO(job, company, reviews);

        return jobDTO;
    }

    @Override
    public void addJob(Job job) {
        jobRepo.save(job);
    }

    @Override
    public JobDTO findJobID(Long id){
        Job job = jobRepo.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteJobID(Long id){
        if(jobRepo.existsById(id)){
            jobRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob){
        Optional<Job> optionalJob = jobRepo.findById(id);

        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
//            job.setCompanyId(updatedJob.getCompanyId());
            jobRepo.save(job);
            return true;
        }
        return false;
    }
}
