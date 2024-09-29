package com.turysbay.JobPostingService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String email;
    private String coverLetter;
    private String resumeFilePath;
    private LocalDate createdDate;
    private String location;
    private String city;
}
