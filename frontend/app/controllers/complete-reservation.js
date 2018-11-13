import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
export default Controller.extend({
  reservationDate: null,
  reservationTime: null,
  startDate: null,
  session: service(),
  loginForm: false,
  _swalService: service("swal-service"),
  _reservationService: service("reservation-service"),
  router: service("-routing"),
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
