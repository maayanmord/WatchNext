package com.example.watchnext.models.firebase.reviews.interfaces;

import com.example.watchnext.models.entities.Review;

import java.util.List;

public interface GetReviewListByUserIdListener {
    void onComplete(List<Review> list);
}
