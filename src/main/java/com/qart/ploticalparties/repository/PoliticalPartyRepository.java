package com.qart.ploticalparties.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qart.ploticalparties.entity.PoliticalParty;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {
}
