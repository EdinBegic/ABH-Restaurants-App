import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";

export default BaseRoute.extend({
    _reservationService: service("reservation-service"),
    session: service(),
    model(){
        return this.get('_reservationService').getReservationHistory(this.get('session.data.authenticated.user.id'),1,10);
    }
});
