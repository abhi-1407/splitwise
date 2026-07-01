package com.abhilash.splitwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "settlements")
@Getter
@Setter
@NoArgsConstructor
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String settlementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debtor_id")
    private User debtor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditor_id")
    private User creditor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(nullable = false)
    private long amount;

    @CreationTimestamp
    private LocalDateTime settledAt;
}