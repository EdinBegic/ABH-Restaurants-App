import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
    getMenusByRestaurant(id) {
      return this.ajax("GET", `/menus/${id}`);
    },
  });
  