package com.turysbay.JobPostingService.controllers;

import com.turysbay.JobPostingService.entities.JobApplication;
import com.turysbay.JobPostingService.entities.JobPost;
import com.turysbay.JobPostingService.repos.JobApplicationRepository;
import com.turysbay.JobPostingService.repos.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/job-application")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobPostRepository jobPostRepository;
    private final JobApplicationRepository jobApplicationRepository;

    @PostMapping("/{jobPostId}")
    public ResponseEntity<JobApplication> applyForJob(@PathVariable Long jobPostId, @RequestBody JobApplication jobApplication) {
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job Post not found"));
        jobApplication.setJobPost(jobPost);
        JobApplication savedApplication = jobApplicationRepository.save(jobApplication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedApplication);
    }

    @GetMapping("/{jobPostId}")
    public ResponseEntity<List<JobApplication>> getApplicationsForJob(@PathVariable Long jobPostId) {
        List<JobApplication> applications = jobApplicationRepository.findByJobPostId(jobPostId);
        return ResponseEntity.ok(applications);
    }
}
