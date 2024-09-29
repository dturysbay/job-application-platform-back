package com.turysbay.JobPostingService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String coverLetter;
    private String resumeFilePath;
    @ManyToOne
    private JobPost jobPost;
}
