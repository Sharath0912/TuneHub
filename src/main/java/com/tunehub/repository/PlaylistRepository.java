package com.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entities.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>
{

}
