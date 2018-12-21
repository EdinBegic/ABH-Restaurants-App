import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getItemsByMenu(id, pageNumber, pageSize) {
    return this.ajax("GET", `/menu-items//filter?menuId=${id}&pageSize=${pageSize}&pageNumber=${pageNumber}`);
  }
});
