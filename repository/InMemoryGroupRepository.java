package repository;

import src.main.java.com.abhilash.splitwise.entity.Group;
import exceptions.GroupNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGroupRepository implements GroupRepository {
    Map<String,Group> groupMap = new HashMap<>();
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
