import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
import moment from 'moment';
import Ember from 'ember';
export default Controller.extend({
  reservationDate: null,
  reservationTime: null,
  startDate: null,
  session: service(),
  loginForm: false,
  confirmDelete: false,
  _swalService: service("swal-service"),
  _reservationService: service("reservation-service"),
  router: service("-routing"),
  finalSittingPlaces: null,
  notifications: service("toast"),
  flashMessages: service(),
  deleteReservation:function() {
    let reservation = this.get('model.reservation');
    let extendedReservation = this.get('model.extendedReservation');
    let restaurantId = this.get('model.reservation.restaurantTable.restaurant.id');
    let currentTime = moment().utc(true).format("YYYY-MM-DD-HH:mm:ss");
    let startDate = moment(this.get('startDate')).utc(true).format("YYYY-MM-DD-HH:mm:ss");
    let notifications = this.get('notifications');
    if(currentTime > startDate && !this.get('model.reservation.confirmed') && !this.get("confirmDelete")) {
      this.get('_reservationService').delete(reservation.id).then(response =>{
        this.set("confirmDelete", true);
        if(reservation.id != extendedReservation.id) { // merged tables
          this.get('_reservationService').delete(extendedReservation.id);
        }
        notifications.info("Time for completing reservation ran out", "", {positionClass : "toast-bottom-right"});
        this.get("router").transitionTo("restaurant", [restaurantId]);
      })
    }
    Ember.run.later(this, function() {
      this.deleteReservation();
    }, 1000)
  }.on('init'),

  actions: {
    showLoginForm(val) {
      this.set("loginForm", val);
    },

    completeReservation() {
      let reservation = this.get("model.reservation");
      let extendedReservation = this.get("model.extendedReservation");
      reservation.user = this.get('session.data.authenticated.user');
      extendedReservation.user = this.get('session.data.authenticated.user');
      extendedReservation.confirmed = true;
      reservation.confirmed = true;
      let notifications = this.get('notifications');
      this.get("_reservationService")
        .update(reservation.id, reservation)
        .catch(errorResponse => {
          //  let jsonError = JSON.parse(errorResponse.responseText);
          notifications.error(errorResponse.responseText);
          this.get("router").transitionTo("restaurant", [
            reservation.restaurantTable.restaurant.id
          ]);
        });
        if(extendedReservation.id != reservation.id) {
          this.get("_reservationService")
            .update(extendedReservation.id, extendedReservation)
            .catch(errorResponse => {
              //  let jsonError = JSON.parse(errorResponse.responseText);
              console.log(errorResponse.responseText);
              if(!errorResponse.responseText.includes("model")) {
                notifications.error(errorResponse.responseText);
              }
              this.get("router").transitionTo("restaurant", [
                extendedReservation.restaurantTable.restaurant.id
              ]);
            });
        }
        notifications.success("Reservation complete");
        this.get("router").transitionTo("restaurant", [
          reservation.restaurantTable.restaurant.id
        ]);
    }
  }
});
