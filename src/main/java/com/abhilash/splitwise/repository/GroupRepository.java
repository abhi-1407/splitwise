package com.abhilash.splitwise.repository;

import com.abhilash.splitwise.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, String> {

}