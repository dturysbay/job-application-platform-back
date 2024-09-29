package com.turysbay.JobPostingService.services;

import com.turysbay.JobPostingService.entities.Job;
import com.turysbay.JobPostingService.repos.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServices {

    private final JobRepository jobRepository;

    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job updateJob(Long id, Job jobDetails) {
        Job job = getJobById(id);
        if (job != null) {
            job.setJobTitle(jobDetails.getJobTitle());
            job.setDescription(jobDetails.getDescription());
            job.setSalaryRange(jobDetails.getSalaryRange());
            job.setLocation(jobDetails.getLocation());
            return jobRepository.save(job);
        }
        return null;
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

}
