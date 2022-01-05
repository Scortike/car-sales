package com.carsale.serviceassortment.repo;

import com.carsale.serviceassortment.repo.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepo extends JpaRepository<Ad, Long> {
}
