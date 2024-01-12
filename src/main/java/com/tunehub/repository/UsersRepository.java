package com.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>
{
	public Users findByEmail(String email);
}
