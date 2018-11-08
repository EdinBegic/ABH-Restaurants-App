import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default BaseRoute.extend({
  _restaurantService: service("restaurant-service"),
  _reviewService: service("review-service"),
  _menuService: service("menu-service"),
  _menuItemService: service("menu-item-service"),
  session: service(),

  model(params) {
    return hash({
      restaurant: this.get("_restaurantService").getRestaurantById(params.id),
      review: this.get("_reviewService").createReview(),
      numOfReviews: this.get("_reviewService").numOfReviewsForRestaurant(params.id),
      avgRating: this.get("_reviewService").avgRatingForRestaurant(params.id),
      menus: this.get("_menuService").getMenusByRestaurant(params.id),
      menuItems: null
    });
  },
  actions: {
    didTransition() {
      let menus = this.controller.get("model.menus");
      let items = this.get("_menuItemService").getItemsByMenu(menus[0].id).then(response => {
        this.controller.set('model.menuItems',response);
      });
    }
  }

});
