package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.GroupDto;
import java.util.List;

public interface GroupService {
    GroupDto createGroup(GroupDto groupDto);
    GroupDto getGroupById(Long id);
    List<GroupDto> getAllGroups();
    GroupDto updateGroup(Long id, GroupDto groupDto);
    void deleteGroup(Long id);
}
