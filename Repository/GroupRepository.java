package Repository;

import entities.Group;

public interface GroupRepository {
    void save(Group group);
    Group findById(String id);
}
