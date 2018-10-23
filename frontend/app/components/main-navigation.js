import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  session: service("session"),
  router: service("-routing"),
  _swalService: service("swal-service"),
  classNames: ["main-navigation"],

  actions: {
    logout() {
      this.get("_swalService").question(
        "Are you sure you want to log out",
        confirm => {
          this.get("_swalService").success(
            "Successfuly logged out",
            confirm => {
              this.get("session").invalidate();
              this.transitionToRoute("home");
            }
          );
        }
      );
    }
  }
});
