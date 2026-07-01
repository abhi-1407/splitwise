package com.abhilash.splitwise.service;

import com.abhilash.splitwise.dto.*;
import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.DuplicateUserException;
import com.abhilash.splitwise.exception.GroupNotFoundException;
import com.abhilash.splitwise.exception.MemberNotFoundException;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.ExpenseRepository;
import com.abhilash.splitwise.repository.GroupRepository;
import com.abhilash.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public GroupService(
            GroupRepository groupRepository,
            UserRepository userRepository,
            ExpenseRepository expenseRepository) {

        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public String createGroup(CreateGroupRequest request) {

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

    public GroupResponse getGroup(String groupId) {
        Group group = getGroupOrThrow(groupId);
        return new GroupResponse(group.getGroupId(),group.getGroupName(),group.getMembers().size());
    }

    public List<GroupResponse> getAllGroups() {
        return groupRepository.findAll().stream().map(group -> new GroupResponse(group.getGroupId(),group.getGroupName(),group.getMembers().size())).toList();
    }

    public List<MemberResponse> fetchGroupMembers(String groupId) {

        Group group = getGroupOrThrow(groupId);

        return group.getMembers()
                .stream()
                .map(user -> new MemberResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmailId()
                ))
                .toList();
    }

    public String addMember(String groupId, String userId) {

        Group group = getGroupOrThrow(groupId);
        User user = getUserOrThrow(userId);

        if (group.getMembers().contains(user)) {
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

    // NEW API - Expense History
    public List<ExpenseResponse> getGroupExpenses(String groupId) {

        Group group = getGroupOrThrow(groupId);

        return expenseRepository.findByGroup(group)
                .stream()
                .map(this::mapToExpenseResponse)
                .toList();
    }

    private ExpenseResponse mapToExpenseResponse(Expense expense) {

        List<SplitResponse> splitResponses = expense.getSplits()

                .stream()

                .map(split -> new SplitResponse(

                        split.getUser().getId(),

                        split.getUser().getName(),

                        split.getAmount()

                ))

                .toList();

        return new ExpenseResponse(

                expense.getExpenseId(),

                expense.getAmount(),

                expense.getPaidBy().getId(),

                expense.getPaidBy().getName(),

                expense.getGroup() != null ? expense.getGroup().getGroupId() : null,

                expense.getGroup() != null ? expense.getGroup().getGroupName() : null,

                splitResponses

        );

    }

    private Group getGroupOrThrow(String groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }

    private User getUserOrThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}