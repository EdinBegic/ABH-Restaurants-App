import Component from "@ember/component";
import { inject as service } from "@ember/service";

export default Component.extend({
  session: service("session"),
  router: service(),
  _swalService: service("swal-service"),
  notifications: service("toast"),
  classNames: ["main-navigation"],

  actions: {
    logout() {
      this.get("_swalService").question(
        "Are you sure you want to log out",
        confirm => {
          let notifications = this.get("notifications");
          notifications.success("Successfully logged out");
          this.get("session").invalidate();
          this.get("router").transitionTo("home");
        }
      );
    }
  }
});
