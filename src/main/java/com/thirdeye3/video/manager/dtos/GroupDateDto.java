package com.thirdeye3.video.manager.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupDateDto {
	GroupDto groupDto;
	LocalDateTime localDateTime;
}
