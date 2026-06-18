package repository;

import entities.Group;
import exceptions.GroupNotFoundException;

import java.util.Map;

public class InMemoryGroupRepository implements GroupRepository {
    Map<String,Group> groupMap;
    @Override
    public void save(Group group) {
        groupMap.put(group.getGroupId(),group);
    }
    @Override
    public Group findById(String id) {
        if(groupMap.containsKey(id)){
            throw new GroupNotFoundException(id);
        }
        return groupMap.getOrDefault(id,null);
    }
}
