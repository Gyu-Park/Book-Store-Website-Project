package com.groupproject.boogle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupproject.boogle.model.Guest;
import com.groupproject.boogle.repository.GuestRepository;

@Service("GuestService")
public class GuestService {
	
	@Autowired
	GuestRepository guestRepository;
	
	public Guest addGuestintoDatabase(Guest guest) {
		return guestRepository.saveAndFlush(guest);
	}
	
}
