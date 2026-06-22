package com.abhilash.splitwise.repository;

import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.exception.GroupNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryGroupRepository implements GroupRepository {
    Map<String, Group> groupMap = new HashMap<>();
    @Override
    public void save(Group group) {
        groupMap.put(group.getGroupId(),group);
    }
    @Override
    public Group findById(String id) {
        if(!groupMap.containsKey(id)){
            throw new GroupNotFoundException(id);
        }
        return groupMap.getOrDefault(id,null);
    }

    @Override
    public List<Group> getAllGroups(){
        List<Group> groupList = new ArrayList<>();
        for(String key : groupMap.keySet()){
            groupList.add(groupMap.get(key));
        }
        return groupList;
    }
}
