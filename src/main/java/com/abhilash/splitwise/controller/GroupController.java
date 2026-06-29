package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.dto.CreateGroupRequest;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public String createGroup(@RequestBody @Valid CreateGroupRequest createGroupRequest) {
        return groupService.createGroup(createGroupRequest);
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{groupId}")
    public Group getGroup(@PathVariable String groupId) {
        return groupService.getGroup(groupId);
    }

    @GetMapping("/{groupId}/members")
    public Set<User> fetchGroupMembers(@PathVariable String groupId) {
        return groupService.fetchGroupMembers(groupId);
    }

    @PostMapping("/members/{groupId}/{userId}")
    public String addMember(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.addMember(groupId, userId);
    }

    @DeleteMapping("/members/{groupId}/{userId}")
    public String removeMember(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.removeMember(groupId, userId);
    }
}