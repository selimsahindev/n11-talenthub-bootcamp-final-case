package com.selimsahin.userservice.mapper;

import com.selimsahin.userservice.dto.UserReviewCreateRequest;
import com.selimsahin.userservice.dto.UserReviewDetailDTO;
import com.selimsahin.userservice.entity.UserReview;
import com.selimsahin.userservice.enums.UserRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

/**
 * @author selimsahindev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserReviewMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "rate", target = "rate", qualifiedByName = "mapIntToUserRating")
    @Mapping(source = "comment", target = "comment")
    UserReview mapUserReviewCreateRequestToUserReview(UserReviewCreateRequest request);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "comment", target = "comment")
    UserReviewDetailDTO mapUserReviewToUserReviewDetailDTO(UserReview userReview);


    @Named("mapIntToUserRating")
    default UserRating mapIntToUserRating(int value) {
        return UserRating.fromValue(value);
    }
}
