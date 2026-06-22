package com.abhilash.splitwise.repository;


import com.abhilash.splitwise.entity.Group;

import java.util.List;

public interface GroupRepository {
    void save(Group group);
    Group findById(String id);
    List<Group> getAllGroups();
}
