package com.selimsahin.userservice.mapper;

import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;
import com.selimsahin.userservice.entity.UserReview;
import com.selimsahin.userservice.enums.UserRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

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
    @Mapping(source = "restaurantId", target = "restaurantId")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "comment", target = "comment")
    UserReviewResponse mapUserReviewToUserReviewResponse(UserReview userReview);

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "restaurantId", target = "restaurantId")
    @Mapping(source = "rate", target = "rate")
    @Mapping(source = "comment", target = "comment")
    List<UserReviewResponse> mapUserReviewsToUserReviewResponses(List<UserReview> userReviews);

    @Named("mapIntToUserRating")
    default UserRating mapIntToUserRating(int value) {
        return UserRating.fromValue(value);
    }
}
