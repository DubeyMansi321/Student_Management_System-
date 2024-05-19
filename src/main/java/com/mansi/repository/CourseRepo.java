package com.mansi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mansi.entity.CourseEntity;

public interface CourseRepo extends JpaRepository<CourseEntity, Integer>  {

}
