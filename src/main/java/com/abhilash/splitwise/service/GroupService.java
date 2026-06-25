package com.abhilash.splitwise.service;

import com.abhilash.splitwise.dto.CreateGroupRequest;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.GroupNotFoundException;
import com.abhilash.splitwise.exception.MemberNotFoundException;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.GroupRepository;
import com.abhilash.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(
            GroupRepository groupRepository,
            UserRepository userRepository) {

        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public String createGroup(
            CreateGroupRequest createGroupRequest) {

        String groupId = UUID.randomUUID().toString();
        Set<User> groupList = new HashSet<>();

        for(String id : createGroupRequest.getMemberIds()){
            User user = userRepository.findById(id);
            if(user == null){
                throw new UserNotFoundException(id);
            }
            groupList.add(user);
        }
        Group group = new Group(
                groupId,
                createGroupRequest.getGroupName(),
                groupList
        );

        groupRepository.save(group);

        return "Group Created with ID " + groupId;
    }

    public Group getGroup(String groupId) {

        Group group = groupRepository.findById(groupId);

        if(group == null) {
            throw new GroupNotFoundException(groupId);
        }

        return group;
    }

    public Set<User> fetchGroupMembers(String groupId) {

        Group group = groupRepository.findById(groupId);

        if(group == null) {
            throw new GroupNotFoundException(groupId);
        }

        return group.getMembers();
    }

    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    public String addMember(
            String groupId,
            String userId) {

        Group group = groupRepository.findById(groupId);
        User user = userRepository.findById(userId);

        if(group == null) {
            throw new GroupNotFoundException(groupId);
        }

        if(user == null) {
            throw new UserNotFoundException(userId);
        }

        group.getMembers().add(user);

        groupRepository.save(group);

        return "Member added successfully";
    }

    public String removeMember(
            String groupId,
            String userId) {

        Group group = groupRepository.findById(groupId);
        User user = userRepository.findById(userId);

        if(group == null) {
            throw new GroupNotFoundException(groupId);
        }

        if(user == null) {
            throw new UserNotFoundException(userId);
        }

        if(!group.getMembers().contains(user)) {
            throw new MemberNotFoundException(userId);
        }

        group.getMembers().remove(user);

        groupRepository.save(group);

        return "Member removed successfully";
    }
}