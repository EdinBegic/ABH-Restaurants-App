import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  findAll(pageNumber, pageSize) {
    return this.ajax("GET", `/cousines/filter?pageSize=${pageSize}&pageNumber=${pageNumber}`);
  }
});
