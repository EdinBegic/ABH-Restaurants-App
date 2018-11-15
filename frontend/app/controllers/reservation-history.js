import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
import CONSTANTS from "../constants";
import EmberObject from "@ember/object";
import { set } from "@ember/object";
export default Controller.extend({
  session: service(),
  _reservationService: service("reservation-service"),

});
