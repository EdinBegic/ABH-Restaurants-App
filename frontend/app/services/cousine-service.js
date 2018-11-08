import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  findAll() {
    return this.ajax("GET", `/cousines`);
  }
});
