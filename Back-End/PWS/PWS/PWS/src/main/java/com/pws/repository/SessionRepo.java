package com.pws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pws.models.CurrentUserSession;

@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession,String>{

	public CurrentUserSession findByKey(String Key);
}
