import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getItemsByMenu(menuId) {
    return this.ajax("GET", `/menu-items/filter/?menuId=${menuId}`);
  }
});
