package com.abhilash.splitwise.controller;

import com.abhilash.splitwise.dto.CreateGroupRequest;
import com.abhilash.splitwise.dto.ExpenseResponse;
import com.abhilash.splitwise.dto.GroupResponse;
import com.abhilash.splitwise.dto.MemberResponse;
import com.abhilash.splitwise.model.SimplifiedSettlement;
import com.abhilash.splitwise.service.BalanceService;
import com.abhilash.splitwise.service.DebtSimplificationService;
import com.abhilash.splitwise.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final BalanceService balanceService;
    private final DebtSimplificationService debtSimplificationService;

    public GroupController(GroupService groupService, BalanceService balanceService, DebtSimplificationService debtSimplificationService) {
        this.groupService = groupService;
        this.balanceService = balanceService;
        this.debtSimplificationService = debtSimplificationService;
    }
    @PostMapping
    public ResponseEntity<String> createGroup(@RequestBody @Valid CreateGroupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(request));
    }
    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponse> getGroup(@PathVariable String groupId) {
        return ResponseEntity.ok(groupService.getGroup(groupId));
    }
    @GetMapping("/{groupId}/members")
    public ResponseEntity<List<MemberResponse>> fetchGroupMembers(@PathVariable String groupId) {
        return ResponseEntity.ok(groupService.fetchGroupMembers(groupId));
    }
    @GetMapping("/{groupId}/expenses")
    public ResponseEntity<List<ExpenseResponse>> getGroupExpenses(@PathVariable String groupId) {
        return ResponseEntity.ok(groupService.getGroupExpenses(groupId));
    }
    @GetMapping("/{groupId}/balances")
    public ResponseEntity<Map<String, Map<String, Long>>> getGroupBalances(@PathVariable String groupId) {
        return ResponseEntity.ok(balanceService.getGroupBalances(groupId));
    }
    @GetMapping("/{groupId}/simplify")
    public ResponseEntity<List<SimplifiedSettlement>> simplifyGroupDebts(@PathVariable String groupId) {
        return ResponseEntity.ok(debtSimplificationService.getGroupSimplifiedBalance(groupId));
    }
    @PostMapping("/members/{groupId}/{userId}")
    public ResponseEntity<String> addMember(@PathVariable String groupId,
                                            @PathVariable String userId) {
        return ResponseEntity.ok(groupService.addMember(groupId, userId));
    }
    @DeleteMapping("/members/{groupId}/{userId}")
    public ResponseEntity<String> removeMember(@PathVariable String groupId, @PathVariable String userId) {
        return ResponseEntity.ok(groupService.removeMember(groupId, userId));
    }
}