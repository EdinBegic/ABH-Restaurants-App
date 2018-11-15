import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  router: service("-routing"),

  actions: {
    reserveNow(restaurantId) {
      this.get("router").transitionTo("restaurant", [restaurantId]);
    }
  }
});
