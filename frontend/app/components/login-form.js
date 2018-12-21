import Component from "@ember/component";
import { inject as service } from "@ember/service";
import { set } from "@ember/object";

export default Component.extend({
  router: service(),
  session: service(),
  _swalService: service("swal-service"),
  notifications: service("toast"),

  init() {
    this._super(...arguments);
    this.loginData = {};
  },

  actions: {
    login(shouldTransition) {
      let notifications = this.get('notifications');
      this.get("session")
        .authenticate("authenticator:application", this.loginData, data => {
          set(this, "loginData", {});
          notifications.success("Successful login");
          this.get('router').transitionTo("home");
        })
        .catch(reason => {
          notifications.error(reason.responseText);
        });
    }
  }});
