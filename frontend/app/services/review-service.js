import BaseHttpService from "./base-http-service";
import EmberObject from "@ember/object";
export default BaseHttpService.extend({
  review: null,

  createReview() {
    let newReview = EmberObject.create({
      user: null,
      restaurant: null,
      mark: null,
      comment: null
    });
    this.set("review", newReview);
    return this.get("review");
  },

  reviewRestaurant(review) {
    return this.ajax("POST", "/reviews", review);
  },
  numOfReviewsForRestaurant(id) {
    return this.ajax("GET", `/reviews/restaurant/${id}/size`);
  },
  avgRatingForRestaurant(id) {
    return this.ajax("GET", `/reviews/restaurant/${id}/rating`);
  },
  hasReviewed(id, userId) {
    return this.ajax("GET", `/reviews/${id}/user/${userId}`);
  },
});
