package com.atlas.jobsms.job.impl;

import com.atlas.jobsms.job.Job;
import com.atlas.jobsms.job.dto.JobDTO;

import java.util.List;

public interface JobServices {
    List<JobDTO> findAll();
    void addJob(Job job);
    JobDTO findJobID(Long id);
    boolean deleteJobID(Long id);
    boolean updateJob(Long id, Job updatedJob);
}
