package org.s3todb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.s3todb.entity.ProviderEntity;

@Repository
public interface ProvidersRepository extends JpaRepository<ProviderEntity, Long> {

}
