package com.hiretrack.entity_manager.repo;

import com.hiretrack.entity_manager.entity.Shortlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortlistRepo extends JpaRepository<Shortlist, Long> {
}
