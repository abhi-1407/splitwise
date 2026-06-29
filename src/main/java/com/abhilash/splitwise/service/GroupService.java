package com.abhilash.splitwise.service;

import com.abhilash.splitwise.dto.CreateGroupRequest;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.DuplicateUserException;
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
            CreateGroupRequest request) {
        Set<User> members = new HashSet<>();
        for (String userId : request.getMemberIds()) {
            members.add(getUserOrThrow(userId));
        }

        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setMembers(members);

        groupRepository.save(group);

        return "Group created with ID " + group.getGroupId();
    }

    public Group getGroup(String groupId) {
        return getGroupOrThrow(groupId);
    }

    public Set<User> fetchGroupMembers(String groupId) {
        return getGroupOrThrow(groupId).getMembers();
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public String addMember(String groupId, String userId) {
        Group group = getGroupOrThrow(groupId);
        User user = getUserOrThrow(userId);
        if(group.getMembers().contains(user)){
            throw new DuplicateUserException();
        }
        group.getMembers().add(user);

        groupRepository.save(group);

        return "Member added successfully";
    }

    public String removeMember(String groupId, String userId) {

        Group group = getGroupOrThrow(groupId);
        User user = getUserOrThrow(userId);

        if (!group.getMembers().contains(user)) {
            throw new MemberNotFoundException(userId);
        }

        group.getMembers().remove(user);

        groupRepository.save(group);

        return "Member removed successfully";
    }

    private Group getGroupOrThrow(String groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFoundException(groupId));
    }

    private User getUserOrThrow(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}