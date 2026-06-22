package com.abhilash.splitwise.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
public class Group {
    private String groupId;
    private String groupName;
    private Set<User> members;

    public Group(String groupId,String groupName,Set<User> members){
        this.groupId = groupId;
        this.groupName = groupName;
        this.members = members;
    }
}
