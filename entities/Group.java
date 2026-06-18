package entities;

import java.util.List;
import java.util.Set;

public class Group {
    private final String groupId;
    private final String groupName;
    private final Set<User> members;

    public Group(String groupId,String groupName,Set<User> members){
        this.groupId = groupId;
        this.groupName = groupName;
        this.members = members;
    }

    public Set<User> getMembers(){
        return members;
    }

    public String getGroupId(){
        return groupId;
    }

    public String getGroupName(){
        return groupName;
    }
}
