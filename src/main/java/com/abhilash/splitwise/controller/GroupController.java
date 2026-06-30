package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.dto.CreateGroupRequest;
import com.abhilash.splitwise.dto.ExpenseResponse;
import com.abhilash.splitwise.dto.MemberResponse;
import com.abhilash.splitwise.entity.Expense;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.model.Settlement;
import com.abhilash.splitwise.model.SimplifiedSettlement;
import com.abhilash.splitwise.service.BalanceService;
import com.abhilash.splitwise.service.DebtSimplificationService;
import com.abhilash.splitwise.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final BalanceService balanceService;
    private final DebtSimplificationService debtSimplificationService;
    GroupController(GroupService groupService, BalanceService balanceService, DebtSimplificationService debtSimplificationService) {
        this.groupService = groupService;
        this.balanceService = balanceService;
        this.debtSimplificationService = debtSimplificationService;
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
    public List<MemberResponse> fetchGroupMembers(@PathVariable String groupId) {
        return groupService.fetchGroupMembers(groupId);
    }
    @GetMapping("/{groupId}/expenses")
    public List<ExpenseResponse> getGroupExpenses(@PathVariable String groupId) {
        return groupService.getGroupExpenses(groupId);
    }
    @GetMapping("/{groupId}/balances")
    public Map<String, Map<String, Long>> getGroupBalances(@PathVariable String groupId) {
        return balanceService.getGroupBalances(groupId);
    }
    @PostMapping("/members/{groupId}/{userId}")
    public String addMember(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.addMember(groupId, userId);
    }
    @DeleteMapping("/members/{groupId}/{userId}")
    public String removeMember(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.removeMember(groupId, userId);
    }
    @GetMapping("/{groupId}/simplify")
    public List<SimplifiedSettlement> simplifyGroupDebts(@PathVariable String groupId) {
        return debtSimplificationService.getGroupSimplifiedBalance(groupId);
    }
}