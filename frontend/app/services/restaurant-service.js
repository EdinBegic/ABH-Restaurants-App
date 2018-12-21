import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getPopularRestaurants(size) {
    return this.ajax("GET", `/restaurants/popular?size=${size}`);
  },
  getRestaurantById(id) {
    return this.ajax("GET", `/restaurants/${id}`);
  },
  filter(paramName, value, pageNumber, pageSize) {
    return this.ajax(
      "GET",
      `/restaurants/filter/?${paramName}=${value}&pageSize=${pageSize}&pageNumber=${pageNumber}`
    );
  },
  filterMultipleParams(params, pageNumber, pageSize) {
    return this.ajax(
      "GET",
      `/restaurants/filter/?${params}pageSize=${pageSize}&pageNumber=${pageNumber}`
    );
  },
  getNearestRestaurants(latitude, longitude) {
    return this.ajax(
      "GET",
      `/restaurants/nearest/?latitude=${latitude}&longitude=${longitude}`
    );
  },
  getCoordinates(id) {
    return this.ajax("GET", `/restaurants/${id}/coordinates`);
  },
  deleteWithLocationId(locationId) {
    return this.ajax("DELETE", `/restaurants/location/${locationId}`);
  },
  getRestaurant(id) {
    return this.ajax("GET", `/restaurants/${id}`);
  },
  updateRestaurant(id, restaurant) {
    return this.ajax("PUT", `/restaurants/${id}`, restaurant);
  },
  deleteRestaurant(id) {
    return this.ajax("DELETE", `/restaurants/${id}`);
  },

});
