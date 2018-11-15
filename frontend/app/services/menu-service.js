import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getMenusByRestaurant(id, pageSize, pageNumber) {
    return this.ajax("GET", `/menus/filter?restaurantId=${id}&pageSize=${pageSize}&pageNumber=${pageNumber}`);
  }
});
