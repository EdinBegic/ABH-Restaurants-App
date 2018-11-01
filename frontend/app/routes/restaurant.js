import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
  _restaurantService: service("restaurant-service"),
  _reviewService: service("review-service"),
  session: service(),

  model(params) {
    return hash({
      restaurant: this.get("_restaurantService").getRestaurantById(params.id),
      review: this.get("_reviewService").createReview(),
      numOfReviews: this.get("_restaurantService").getReviewSize(params.id),
      avgRating: this.get("_restaurantService").getAvgRating(params.id),
    });
  },

});
