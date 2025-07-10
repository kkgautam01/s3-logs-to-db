package org.s3todb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "providers")
public class ProviderEntity {

    @Id
    private Long providerId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String cloudInfo;



    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCloudInfo() {
        return cloudInfo;
    }

    public void setCloudInfo(String cloudInfo) {
        this.cloudInfo = cloudInfo;
    }
}

