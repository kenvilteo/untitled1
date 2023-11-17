package com.company.untitled1.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "DEPARTMENT", indexes = {
        @Index(name = "IDX_DEPARTMENT_HR_MANAGER", columnList = "HR_MANAGER_ID")
})
@Entity
public class Department {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "HR_MANAGER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private User hrManager;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    public User getHrManager() {
        return hrManager;
    }

    public void setHrManager(User hrManager) {
        this.hrManager = hrManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}