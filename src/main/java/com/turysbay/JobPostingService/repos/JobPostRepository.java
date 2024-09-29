package com.turysbay.JobPostingService.repos;
import com.turysbay.JobPostingService.entities.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    Page<JobPost> findByTitleContainingOrLocationContaining(String title, String location, Pageable pageable);
    Page<JobPost> findByTitleContainingOrCityContaining(String title, String city, Pageable pageable);
}