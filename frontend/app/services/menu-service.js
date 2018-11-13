import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getMenusByRestaurant(restaurantId) {
    return this.ajax("GET", `/menus/filter/?restaurantId=${restaurantId}`);
  }
});
