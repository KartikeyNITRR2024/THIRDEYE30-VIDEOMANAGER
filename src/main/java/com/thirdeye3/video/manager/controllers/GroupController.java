package com.thirdeye3.video.manager.controllers;

import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.services.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vm/groups")
public class GroupController {

	@Autowired
    private GroupService groupService;

    @PostMapping
    public Response<GroupDto> createGroup(@RequestBody GroupDto GroupDto) {
        return new Response<>(true, 0, null, groupService.createGroup(GroupDto));
    }

    @GetMapping("/{id}")
    public Response<GroupDto> getGroupById(@PathVariable Long id) {
        return new Response<>(true, 0, null, groupService.getGroupById(id));
    }

    @GetMapping
    public Response<List<GroupDto>> getAllGroups() {
        return new Response<>(true, 0, null, groupService.getAllGroups());
    }

    @PutMapping("/{id}")
    public Response<GroupDto> updateGroup(@PathVariable Long id, @RequestBody GroupDto GroupDto) {
        return new Response<>(true, 0, null, groupService.updateGroup(id, GroupDto));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return new Response<>(true, 0, null, null);
    }
}