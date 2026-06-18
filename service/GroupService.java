package service;

import repository.GroupRepository;
import entities.Group;
import entities.User;
import exceptions.MemberNotFoundException;

import java.util.*;

public class GroupService {
    private final GroupRepository groupRepository;
    public GroupService(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }
    public Group createGroup(String groupName, Set<User> members){
        String groupId = UUID.randomUUID().toString();
        Group group = new Group(groupId,groupName,members);
        groupRepository.save(group);
        return group;
    }

    public void addMember(String groupId, User member){
        Group group = groupRepository.findById(groupId);
        group.getMembers().add(member);
        //Redundant as this is in-memory
        groupRepository.save(group);
    }

    public void removeMember(String groupId, User member){
        Group group = groupRepository.findById(groupId);
        if(!group.getMembers().contains(member)){
            throw new MemberNotFoundException(member.getId());
        }
        group.getMembers().remove(member);
        groupRepository.save(group);
    }

    public boolean isMember(String groupId,User user){
        Group group = groupRepository.findById(groupId);
        return group.getMembers().contains(user);
    }

}
