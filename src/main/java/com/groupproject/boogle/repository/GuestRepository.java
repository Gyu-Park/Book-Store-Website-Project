package com.groupproject.boogle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupproject.boogle.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
