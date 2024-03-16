package com.selimsahin.userservice.mapper;

import com.selimsahin.userservice.dto.request.UserCreateRequest;
import com.selimsahin.userservice.dto.response.UserResponse;
import com.selimsahin.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author selimsahindev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User mapUserCreateRequestToUser(UserCreateRequest request);

    UserResponse mapToUserResponse(User user);
}
