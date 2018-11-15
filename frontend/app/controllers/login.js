import Ember from "ember";
import { inject as service } from "@ember/service";
import { set } from "@ember/object";
import BaseController from "./base-controller";

export default BaseController.extend({
  session: service(),
  _swalService: service("swal-service"),

  init() {
    this._super(...arguments);
    this.model = {};
  },
  actions: {
    login() {
      this.get("session")
        .authenticate("authenticator:application", this.model, data => {
          set(this, "model", {});
          this.get("_swalService").success("Successful login", confirm => {
            this.transitionToRoute("home");
          });
          this.transitionToRoute("home");
        })
        .catch(reason => {
          this.get("_swalService").error(reason.responseJSON.errorMessage);
        });
    }
  }
});
