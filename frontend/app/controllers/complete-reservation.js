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

  deleteReservation:function() {
    let reservation = this.get('model.reservation');
    let restaurantId = this.get('model.reservation.restaurantTable.restaurant.id');
    let currentTime = moment().utc(true).format("YYYY-MM-DD-HH:mm:ss");
    let startDate = moment(this.get('startDate')).utc(true).format("YYYY-MM-DD-HH:mm:ss");
    if(currentTime > startDate && !this.get('model.reservation.confirmed') && !this.get("confirmDelete")) {
      this.get('_reservationService').delete(reservation.id).then(response =>{
        this.set("confirmDelete", true);
        this.get("_swalService").info("Time for completing reservation ran out", confirm => {
          this.get("router").transitionTo("restaurant", [restaurantId]);
        });
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

    completeReservation(userId) {
      let reservation = this.get("model.reservation");
      reservation.userId = userId;
      reservation.confirmed = true;
      this.get("_reservationService")
        .update(reservation.id, reservation)
        .then(response => {
          this.get("_swalService").success("Reservation complete", confirm => {
            this.get("router").transitionTo("restaurant", [
              reservation.restaurantTable.restaurant.id
            ]);
          });
        })
        .catch(errorResponse => {
          //  let jsonError = JSON.parse(errorResponse.responseText);
          this.get("_swalService").error(
            errorResponse.responseText,
            confirm => {
              this.get("router").transitionTo("restaurant", [
                reservation.restaurantTable.restaurant.id
              ]);
            }
          );
        });
    }
  }
});
