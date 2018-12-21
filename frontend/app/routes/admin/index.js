import AdminRoute from "../admin-route";
import {
  inject as service
} from "@ember/service";
import {
  hash
} from "rsvp";

export default AdminRoute.extend({
  _restaurantService: service("restaurant-service"),
  _locationService: service("location-service"),
  _userService: service("user-service"),

  model() {
    return hash({
      restaurants: this.get('_restaurantService').filter("", "", 0, 0),
      locations: this.get('_locationService').filter("", "", 0, 0),
      users: this.get('_userService').filter("", "", 0, 0)
    });
  }
});
