package org.s3todb.filehandling.service;


import lombok.Getter;
import lombok.Setter;
import org.s3todb.entity.ProviderEntity;

@Getter
@Setter
public class Provider {
    private Long providerId;
    private String name;
    private String cloudInfo;
    public Provider(ProviderEntity entity) {
        this.cloudInfo = entity.getCloudInfo();
        this.name = entity.getName();
        this.providerId = entity.getProviderId();
    }

    public Provider(Long providerId, String name, String cloudInfo) {
        this.cloudInfo = cloudInfo;
        this.name = name;
        this.providerId = providerId;
    }
}
