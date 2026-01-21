package com.win777.repository;

import com.win777.entity.ReferralEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReferralRepository extends JpaRepository<ReferralEntity, Long> {
    List<ReferralEntity> findByReferrerId(Long referrerId);
}
