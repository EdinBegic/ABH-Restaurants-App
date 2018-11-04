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
  }
});
