package com.selimsahin.userreviewservice.entity;

import com.selimsahin.userreviewservice.entity.common.Auditable;
import com.selimsahin.userreviewservice.enums.UserRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author selimsahindev
 */
@Entity
@Table(name = "user_reviews")
@Getter
@Setter
public class UserReview extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID is required.")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull(message = "Rating is required.")
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rate", length = 30, nullable = false)
    private UserRating rate;

    @NotBlank(message = "Comment is required.")
    @Column(name = "comment", length = 200, nullable = false)
    private String comment;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }

        UserReview userReview = (UserReview) obj;

        if (!userId.equals(userReview.userId)) { return false; }
        if (rate != userReview.rate) { return false; }
        return comment.equals(userReview.comment);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + rate.hashCode();
        result = 31 * result + comment.hashCode();
        return result;
    }
}
