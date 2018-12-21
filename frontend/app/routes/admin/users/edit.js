import AdminRoute from "../../admin-route";
import {
  inject as
  service
} from "@ember/service";
import { hash } from "rsvp";
export default AdminRoute.extend({
  _locationService: service("location-service"),
  _userService: service("user-service"),
  session: service(),

  model(params) {
    return hash({
      locations: this.get("_locationService").getAllCountries(),
      cities: this.get("_locationService").getAllCountries(),
      user: this.get("_userService").getUser(params.id)
    });
  },

  actions: {
    didTransition() {
      // when sing-up page is loaded, the default location is saved in the user model
      let val = this.controller.get("model.locations");
      this.controller.set("model.user.locationId", val[0].id);
      this.get("_locationService")
        .getAllCitiesForCountry(val[0].country)
        .then(response => {
          // only the cities from the default country location is displayed
          this.controller.set("model.cities", response);
        });
    }
  }

});
