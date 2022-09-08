package com.file.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file.model.Userexcel;
@Repository
public interface UserRepository extends JpaRepository<Userexcel, Long>{

}
