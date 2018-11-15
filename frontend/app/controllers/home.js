import BaseController from "./base-controller";

import { inject as service } from "@ember/service";
export default BaseController.extend({
  router: service("-routing"),
  _restaurantService: service("restaurant-service"),
  showAutoComplete: false,
  selectedRestaurant: false,
  query: "",
  selectedRestaurantId: null,
  suggestions: [],

  search() {
    let q = this.get("query");
    if (q != "") {
      this.get("_restaurantService")
        .filter("q", q, 1, 5)
        .then(response => {
          this.set("suggestions", response.data);
          this.set("showAutoComplete", true);
          this.set("selectedRestaurant", false);
        });
    } else {
      this.set("suggestions", []);
    }
  },

  changedQuery: function() {
    Ember.run.debounce(this, this.search, 500);
  }.observes("query"),
  actions: {
    reserveNow(restaurantId) {
      this.get("router").transitionTo("restaurant", [restaurantId]);
    },

    setRestaurant(restaurant) {
      //for now while reservation module is not implemented
      this.set("query", restaurant.name + ", " + restaurant.location.city);
      this.set("selectedRestaurantId", restaurant.id);
      this.set("showAutoComplete", false);
      this.set("selectedRestaurant", true);
    }
  }
});
