import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getItemsByMenu(id) {
    return this.ajax("GET", `/menu-items/menu/${id}`);
  }
});
