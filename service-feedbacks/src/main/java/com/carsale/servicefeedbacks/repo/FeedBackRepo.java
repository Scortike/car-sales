package com.carsale.servicefeedbacks.repo;

import com.carsale.servicefeedbacks.repo.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepo extends JpaRepository<FeedBack, Long> {
    List<FeedBack> findBySellerId(long id);
    List<FeedBack> findByCustomerId(long id);
}
