import Component from "@ember/component";
import { inject as service } from "@ember/service";
import { set } from "@ember/object";

export default Component.extend({
  router: service(),
  session: service(),
  _swalService: service("swal-service"),

  init() {
    this._super(...arguments);
    this.loginData = {};
  },
  
  actions: {
    login(shouldTransition) {
      this.get("session")
        .authenticate("authenticator:application", this.loginData, data => {
          set(this, "loginData", {});
          this.get("_swalService").success("Successful login", confirm => {
            if(shouldTransition) {
              this.get('router').transitionTo("home");
            }
          });
          if(shouldTransition) {
            this.get('router').transitionTo("home");

          }
        })
        .catch(reason => {
          this.get("_swalService").error(reason.responseJSON.errorMessage);
        });
    }
  }});
