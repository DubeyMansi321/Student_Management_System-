package com.mansi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mansi.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer> {

}
