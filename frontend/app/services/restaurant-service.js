import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getPopularRestaurants(size) {
    return this.ajax("GET", `/restaurants/popular?size=${size}`);
  },
  getRestaurantById(id) {
    return this.ajax("GET", `/restaurants/${id}`);
  },
  getReviewSize(id) {
    return this.ajax("GET", `/restaurants/${id}/reviews`);
  },
  getAvgRating(id) {
    return this.ajax("GET", `/restaurants/${id}/rating`);
  }
});
