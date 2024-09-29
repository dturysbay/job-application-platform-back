package com.turysbay.JobPostingService.controllers;

import com.turysbay.JobPostingService.entities.JobPost;
import com.turysbay.JobPostingService.repos.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/job-posts")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostRepository jobPostRepository;

    @PostMapping
    public ResponseEntity<JobPost> createJobPost(@RequestBody JobPost jobPost) {
        jobPost.setCreatedDate(LocalDate.now());
        JobPost savedJobPost = jobPostRepository.save(jobPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJobPost);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<Page<JobPost>> getJobPosts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String location,
            Pageable pageable) {
        Page<JobPost> jobPosts = jobPostRepository.findByTitleContainingOrLocationContaining(title, location, pageable);
        return ResponseEntity.ok(jobPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJobPostById(@PathVariable Long id) {
        Optional<JobPost> jobPost = jobPostRepository.findById(id);
        return jobPost.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobPost> updateJobPost(@PathVariable Long id, @RequestBody JobPost updatedJobPost) {
        if (!jobPostRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedJobPost.setId(id);
        JobPost savedJobPost = jobPostRepository.save(updatedJobPost);
        return ResponseEntity.ok(savedJobPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long id) {
        if (!jobPostRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jobPostRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
