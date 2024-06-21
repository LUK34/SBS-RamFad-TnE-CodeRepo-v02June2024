package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.WikiMediaData;

public interface WikiMediaDataRepo extends JpaRepository<WikiMediaData, Long> {

}
