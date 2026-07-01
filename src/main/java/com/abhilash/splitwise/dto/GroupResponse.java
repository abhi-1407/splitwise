package com.abhilash.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupResponse {

    private String groupId;
    private String groupName;
    private int totalMembers;
}