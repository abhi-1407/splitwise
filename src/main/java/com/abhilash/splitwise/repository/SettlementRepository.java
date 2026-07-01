package com.abhilash.splitwise.repository;

import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.Settlement;
import com.abhilash.splitwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementRepository extends JpaRepository<Settlement,String> {

    List<Settlement> findByGroup(Group group);

    List<Settlement> findByDebtor(User user);

    List<Settlement> findByCreditor(User user);

}
