import EmberObject from "@ember/object";
import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  currentUser: null,
  createUser() {
    let newUser = EmberObject.create({
      firstName: "",
      lastName: "",
      email: "",
      phoneNumber: "",
      password: "",
      confirmedPassword: "",
      locationId: null
    });
    this.set("currentUser", newUser);
    return this.get("currentUser");
  },
  registerUser: function(user) {
    return this.ajax("POST", "/users/register", user);
  },
  filterMultipleParams(params, pageNumber, pageSize) {
    return this.ajax(
      "GET",
      `/users/filter/?${params}pageSize=${pageSize}&pageNumber=${pageNumber}`
    );
  },
  filter(paramName, value, pageNumber, pageSize) {
    return this.ajax(
      "GET",
      `/users/filter/?${paramName}=${value}&pageSize=${pageSize}&pageNumber=${pageNumber}`
    );
  },
  getUser(id) {
    return this.ajax("GET", `/users/${id}`);
  },
  updateUser(id,user) {
    return this.ajax("PUT", `/users/${id}`, user);
  },
  deleteUser(id) {
    return this.ajax("DELETE", `/users/${id}`);
  },

});
