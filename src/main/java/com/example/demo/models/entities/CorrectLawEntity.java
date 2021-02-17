package com.example.demo.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CorrectLaw")
public class CorrectLawEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "interaction_id", referencedColumnName = "id", nullable = false)
    private InteractionEntity interaction;

    @Column(name = "law_name", nullable = false)
    private String lawName;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<BackendFactEntity> violationFacts;
}
