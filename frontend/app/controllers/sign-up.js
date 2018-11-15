import Ember from "ember";
import { inject as service } from "@ember/service";
import BaseController from "./base-controller";

export default BaseController.extend({
  _locationService: service("location-service"),
  _userService: service("user-service"),
  _swalService: service("swal-service"),
  session: service(),
  actions: {
    listCitiesForCountry(country) {
      this.get("_locationService")
        .getAllCitiesForCountry(country) // Only list cities from the selected country
        .then(response => {
          this.get("model.cities").clear();
          this.get("model.cities").pushObjects(response);
          this.set("model.user.locationId", response[0].id);
        });
    },
    setLocationId(id) {
      this.set("model.user.locationId", id); // update the location of the user
    },
    createAccount() {
      this.get("_userService")
        .registerUser(this.get("model.user"))
        .then(response => {
          this.get("_swalService").success(
            "Registration successful",
            confirm => {
              this.transitionToRoute("login");
            }
          );
        })
        .catch(errorResponse => {
          let jsonError = JSON.parse(errorResponse.responseText);
          this.get("_swalService").error(jsonError.error.message);
        });
    }
  }
});
