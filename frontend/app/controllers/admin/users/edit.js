import Controller from '@ember/controller';
import {
  inject as
  service
} from "@ember/service";

export default Controller.extend({
  _locationService: service("location-service"),
  _userService: service("user-service"),
  notifications: service("toast"),
  flashMessages: service(),
  actions: {
    listCitiesForCountry(country) {
      this.get("_locationService")
        .getAllCitiesForCountry(country) // Only list cities from the selected country
        .then(response => {
          this.get("model.cities").clear();
          this.get("model.cities").pushObjects(response);
          this.set("model.user.locationId", response[0].id);
        });
    },
    setLocationId(id) {
      this.set("model.user.locationId", id); // update the location of the user
    },
    updateAccount() {
      let notifications = this.get('notifications');
    /*  this.set('user.firstName', this.getProperties('firstName'));
      this.set('user.lastName', this.getProperties('lastName'));
      this.set('user.email', this.getProperties('email'));
      this.set('user.phoneNumber', this.getProperties('phoneNumber'));
      this.set('user.password', this.getProperties('password'));
      this.set('user.confirmedPassword', this.getProperties('confirmedPassword'));
    */
      let locationId = this.get('model.user.locationId');
      this.get("_userService")
        .updateUser(this.get("model.user.id"), this.get("model.user"))
        .then(response => {
          notifications.success("Successfully updated account.");
          this.transitionToRoute("admin.users.index");
        })
        .catch(errorResponse => {
          //notifications.error(errorResponse.responseText);
          let flashMessages = this.get('flashMessages');
          flashMessages.danger(errorResponse.responseText);
        });
    }
  }
});
