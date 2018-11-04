import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getPopularRestaurants(size) {
    return this.ajax("GET", `/restaurants/popular?size=${size}`);
  },
  getRestaurantById(id) {
    return this.ajax("GET", `/restaurants/${id}`);
  },
  filter(paramName, value, pageNumber, pageSize) {
    return this.ajax("GET", `/restaurants/filter/?${paramName}=${value}&pageSize=${pageSize}&pageNumber=${pageNumber}`);
  }
});
