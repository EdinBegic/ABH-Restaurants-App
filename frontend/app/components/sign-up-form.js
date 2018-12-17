import Component from "@ember/component";
import { inject as service } from "@ember/service";
import { set } from "@ember/object";
import {
    validator,
    buildValidations
}
from 'ember-cp-validations';

var Validations = buildValidations({
    firstName: validator('presence', {
        presence: true
    }),
    lastName: validator('presence', {
        presence: true
    }),
    email: [
        validator('presence', {
            presence: true
        }),
        validator('format', {
            type: 'email'
        }),
      //  validator('unique-email')
    ],
    phoneNumber: [
      validator('presence', {
        presence: true
      }),
      validator('format', {
        regex: /^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/,
        message: 'Not a valid phone number.'
      })
    ],
    password: [
        validator('presence', {
            presence: true,
        }),
        validator('length', {
            min: 8
        }),
    ],
    confirmedPassword: [
        validator('presence', {
            presence: true
        }),
        validator('confirmation', {
            on: 'password'
        })
    ]
}, {
    debounce: 500
});

export default Component.extend(Validations, {
  router: service(),
  session: service(),
  _locationService: service("location-service"),
  _userService: service("user-service"),
  notifications: service("toast"),
  flashMessages: Ember.inject.service(),
  locations: null,
  countries: null,
  user: null,
  cities: null,
  divedForm: false,

  init() {
    this._super(...arguments);
    this.loginData = {};
  },

  actions: {
    listCitiesForCountry(country) {
      this.get("_locationService")
        .getAllCitiesForCountry(country) // Only list cities from the selected country
        .then(response => {
          this.get("cities").clear();
          this.get("cities").pushObjects(response);
          this.set("user.locationId", response[0].id);
        });
    },
    setLocationId(id) {
      this.set("user.locationId", id); // update the location of the user
    },
    createAccount() {
      let notifications = this.get('notifications');
    /*  this.set('user.firstName', this.getProperties('firstName'));
      this.set('user.lastName', this.getProperties('lastName'));
      this.set('user.email', this.getProperties('email'));
      this.set('user.phoneNumber', this.getProperties('phoneNumber'));
      this.set('user.password', this.getProperties('password'));
      this.set('user.confirmedPassword', this.getProperties('confirmedPassword'));
    */
      let self = this;
      let locationId = this.get('user.locationId');
      let userData = this.getProperties('firstName', 'lastName', 'email',
        'phoneNumber', 'password', 'confirmedPassword');
      console.log(userData);
      this.get("_userService")
        .registerUser(this.get("user"))
        .then(response => {
          notifications.success("Registration successfull. You are now logged in.");
          this.loginData.email = this.get('user.email');
          this.loginData.password = this.get('user.password');
          this.get("session")
            .authenticate("authenticator:application", this.loginData, data => {
              set(this, "loginData", {});
              console.log("USAO");
              this.get('router').transitionTo("home");
            })
        })
        .catch(errorResponse => {
          notifications.error(errorResponse.responseText, "",
            {positionClass: 'toast-top-center'});
        });
    }
  }
});
