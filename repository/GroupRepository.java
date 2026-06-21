package repository;

import src.main.java.com.abhilash.splitwise.entity.Group;

public interface GroupRepository {
    void save(Group group);
    Group findById(String id);
}
