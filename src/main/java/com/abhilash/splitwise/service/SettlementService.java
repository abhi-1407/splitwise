package com.abhilash.splitwise.service;

import com.abhilash.splitwise.dto.SettleBalanceRequest;
import com.abhilash.splitwise.entity.Group;
import com.abhilash.splitwise.entity.Settlement;
import com.abhilash.splitwise.entity.User;
import com.abhilash.splitwise.exception.GroupNotFoundException;
import com.abhilash.splitwise.exception.UserNotFoundException;
import com.abhilash.splitwise.repository.GroupRepository;
import com.abhilash.splitwise.repository.SettlementRepository;
import com.abhilash.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public SettlementService(
            SettlementRepository settlementRepository,
            UserRepository userRepository,
            GroupRepository groupRepository) {

        this.settlementRepository = settlementRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public String settleUp(SettleBalanceRequest request) {

        User debtor = userRepository.findById(request.getDebtorId())
                .orElseThrow(() -> new UserNotFoundException(request.getDebtorId()));

        User creditor = userRepository.findById(request.getCreditorId())
                .orElseThrow(() -> new UserNotFoundException(request.getCreditorId()));

        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new GroupNotFoundException(request.getGroupId()));

        Settlement settlement = new Settlement();
        settlement.setDebtor(debtor);
        settlement.setCreditor(creditor);
        settlement.setGroup(group);
        settlement.setAmount(request.getAmount());

        settlementRepository.save(settlement);

        return "Settlement recorded successfully";
    }

    public List<Settlement> getGroupSettlements(String groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));

        return settlementRepository.findByGroup(group);
    }
}