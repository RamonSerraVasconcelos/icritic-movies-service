package com.icritic.movies.dataprovider.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    private Long userId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
