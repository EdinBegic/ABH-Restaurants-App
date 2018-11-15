import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  router: service(),
  session: service(),
  _swalService: service("swal-service"),
  _locationService: service("location-service"),
  _userService: service("user-service"),
  locations: null,
  countries: null,
  user: null,
  cities: null,
  divedForm: false,

  init() {
    this._super(...arguments);
  },

  actions: {
    listCitiesForCountry(country) {
      this.get("_locationService")
        .getAllCitiesForCountry(country) // Only list cities from the selected country
        .then(response => {
          this.get("cities").clear();
          this.get("cities").pushObjects(response);
          this.set("user.locationId", response[0].id);
        });
    },
    setLocationId(id) {
      this.set("user.locationId", id); // update the location of the user
    },
    createAccount() {
      this.get("_userService")
        .registerUser(this.get("user"))
        .then(response => {
          this.get("_swalService").success(
            "Registration successful",
            confirm => {
              if (this.get('shouldTransition')) {
                this.get("router").transitionTo("login");
              } else {
                this.sendAction("functionName", true);
              }
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
