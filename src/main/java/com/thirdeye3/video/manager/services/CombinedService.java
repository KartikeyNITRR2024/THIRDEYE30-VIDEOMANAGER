package com.thirdeye3.video.manager.services;

import java.util.UUID;

import com.thirdeye3.video.manager.dtos.CombinedDto;
import com.thirdeye3.video.manager.dtos.GroupDto;

public interface CombinedService {
	CombinedDto getVideoandActiveResources(UUID uuid);

	GroupDto getGroupDetails(UUID uuid);
}
