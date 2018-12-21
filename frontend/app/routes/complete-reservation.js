import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";
import moment from "moment";
import { hash } from "rsvp";

export default BaseRoute.extend({
  _reservationService: service("reservation-service"),
  _locationService: service("location-service"),
  _userService: service("user-service"),
  session: service(),
  notifications: service("toast"),
  flashMessages: service(),
  model(params) {
    console.log(params);
    return hash({
      reservation: this.get("_reservationService").getReservation(params.id1),
      extendedReservation: this.get("_reservationService").getReservation(params.id2),
      locations: this.get("_locationService").getAllCountries(),
      cities: this.get("_locationService").getAllCountries(),
      user: this.get("_userService").createUser()
    });
  },

  actions: {
    didTransition() {
      this.get('flashMessages').success("samo neki test");
      let reservationTime = moment(
        this.controller.get("model.reservation.startTime"),
        "YYYY-MM-DD-HH:mm:ss"
      ).utc(true).toDate();
      this.controller.set(
        "reservationDate",
        moment(reservationTime).format("MMMM DD, YYYY")
      );
      this.controller.set(
        "reservationTime",
        moment(reservationTime).format("HH:mm")
      );
      let creationDate = this.controller.get("model.reservation.createdAt");
      this.controller.set("startDate", moment(creationDate).add(5, "minutes"));

      let val = this.controller.get("model.locations");
      this.controller.set("model.user.locationId", val[0].id);
      this.get("_locationService")
        .getAllCitiesForCountry(val[0].country)
        .then(response => {
          // only the cities from the default country location is displayed
          this.controller.set("model.cities", response);
          this.controller.set("confirmDelete", false);
        });
      let finalSittingPlaces = this.controller.get("model.reservation.restaurantTable.sittingPlaces");
      console.log(finalSittingPlaces);
      console.log(this.controller.get("model.reservation.id"));
      console.log(this.controller.get("model.extendedReservation.id"));
        if(this.controller.get("model.reservation.id")
          != this.controller.get("model.extendedReservation.id")) {
            finalSittingPlaces = finalSittingPlaces
              + "+" + this.controller.get("model.extendedReservation.restaurantTable.sittingPlaces");
            this.get('notifications').info("Reservation only possible by merging two tables");
        }
      this.controller.set("finalSittingPlaces", finalSittingPlaces);

    }
  }
});
