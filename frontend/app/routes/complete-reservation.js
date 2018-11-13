import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";
import moment from 'moment';
import { hash } from "rsvp";

export default BaseRoute.extend({
    _reservationService: service("reservation-service"),  
    _locationService: service("location-service"),
    _userService: service("user-service"),
    session: service(),
  
    model(params) {
      return hash({
        reservation: this.get('_reservationService').getReservation(params.id),
        locations: this.get("_locationService").getAllCountries(),
        cities: this.get("_locationService").getAllCountries(),
        user: this.get("_userService").createUser()
      });
    },
    
      actions: {
        didTransition() {
          let reservationTime = moment(this.controller.get("model.reservation.startTime"), 'YYYY-MM-DD-HH:mm:ss').toDate();
          console.log(reservationTime);
          this.controller.set('reservationDate',moment(reservationTime).format('MMMM DD, YYYY'));
          this.controller.set('reservationTime',moment(reservationTime).format('HH:mm'));
          this.controller.set('startDate', moment().add(5, 'minutes'));

          let val = this.controller.get("model.locations");
          this.controller.set("model.user.locationId", val[0].id);
          this.get("_locationService")
            .getAllCitiesForCountry(val[0].country)
            .then(response => {
              // only the cities from the default country location is displayed
              this.controller.set("model.cities", response);
            });
    
        }
      }
    
});
