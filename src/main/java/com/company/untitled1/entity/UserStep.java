package com.company.untitled1.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "USER_STEP", indexes = {
        @Index(name = "IDX_USER_STEP_USER", columnList = "USER_ID"),
        @Index(name = "IDX_USER_STEP_STEP", columnList = "STEP_ID")
})
@Entity
public class UserStep {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @NotNull
    @JoinColumn(name = "STEP_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Step step;

    @NotNull
    @Column(name = "DUE_DATE", nullable = false)
    private LocalDate dueDate;

    @Column(name = "COMPLETED_DATE")
    private LocalDate completedDate;

    @NotNull
    @Column(name = "SORT_VALUE", nullable = false)
    private Integer sortValue;

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}