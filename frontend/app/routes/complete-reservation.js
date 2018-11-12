import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";
import moment from 'moment';
export default BaseRoute.extend({
    _reservationService: service("reservation-service"),  
    session: service(),
    
    model(params) {
        return this.get('_reservationService').getReservation(params.id)
      },
    
      actions: {
        didTransition() {
          let reservationTime = moment(this.controller.get("model.startTime"), 'YYYY-MM-DD-HH:mm:ss').toDate();
          console.log(reservationTime);
          this.controller.set('reservationDate',moment(reservationTime).format('MMMM DD, YYYY'));
          this.controller.set('reservationTime',moment(reservationTime).format('HH:mm'));
          this.controller.set('startDate', moment().add(5, 'minutes'));
        }
      }
    
});
