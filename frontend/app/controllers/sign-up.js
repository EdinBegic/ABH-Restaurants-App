import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
  _locationService: service("location-service"),
  _userService: service("user-service"),
  _swalService: service("swal-service"),
  session: service(),
});
