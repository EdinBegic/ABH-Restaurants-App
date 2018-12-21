import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";
import { set } from "@ember/object";
import moment from 'moment';
export default BaseRoute.extend({
    _reservationService: service("reservation-service"),
    session: service(),
    model(){
        return this.get('_reservationService').getReservationHistory(this.get('session.data.authenticated.user.id'),1,10);
    },

    actions: {
        didTransition() {
            let reservations = this.controller.get('model.data');
            console.log(reservations);

            for(let i = 0; i < reservations.length; i++) {
                let reservationTime = moment(
                    reservations[i].startTime,
                    "YYYY-MM-DD-HH:mm:ss"
                  ).utc(true).toDate();
                  let resTime = moment(reservationTime).format("YYYY-MM-DD-HH:mm:ss");
                set(reservations[i], "startTime", resTime);
            }
        }
    }
});
