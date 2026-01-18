package com.thirdeye3.video.manager.services;

import java.util.UUID;

import com.thirdeye3.video.manager.dtos.CombinedDto;
import com.thirdeye3.video.manager.dtos.GroupDto;
import com.thirdeye3.video.manager.dtos.VideoDto;

public interface CombinedService {
	CombinedDto getVideoandActiveResources();
	GroupDto getGroupDetails();
	void updateCompleted(Boolean completed);
	void updateCurrentState(Integer currentState);
}
