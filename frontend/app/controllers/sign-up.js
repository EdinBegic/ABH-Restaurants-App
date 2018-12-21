import Ember from "ember";
import { inject as service } from "@ember/service";
import BaseController from "./base-controller";

export default BaseController.extend({
  _locationService: service("location-service"),
  _userService: service("user-service"),
  _swalService: service("swal-service"),
  session: service(),
});
