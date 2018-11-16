import BaseHttpService from "./base-http-service";
import EmberObject from '@ember/object';
export default BaseHttpService.extend({
    currentReservation: null,

    createReservation(restaurantId) {
        let newReservation = EmberObject.create({
          userId: null,
          sittingPlaces: null,
          restaurantId: restaurantId,
          startDate: "",
          startTime: "",
          confirmed: null,
        });
        this.set("currentReservation", newReservation);
        return this.get("currentReservation");
      },
    getReservation(id) {
        return this.ajax("GET", `/reservations/${id}`);
    },    
    create(reservation) {
        return this.ajax("POST", `/reservations/request`, reservation);
    },
    getSuggestedTimes(reservation, numOfDates) {
        return this.ajax("POST", `/reservations/suggested-times?numOfDates=${numOfDates}`, reservation);
    },
    getReservationHistory(userId, pageNumber, pageSize) {
        return this.ajax("GET", `/reservations/history?userId=${userId}&pageNumber=${pageNumber}&pageSize=${pageSize}`);
    },
    getNumOfReservationsForPeriod(startDate, startTime, finishDate, finishTime, restaurantId) {
        return this.ajax("GET", `/reservations/size-for-period?startDate=${startDate}&startTime=${startTime}&finishDate=${finishDate}&finishTime=${finishTime}&restaurantId=${restaurantId}`);
    },
    update(id,reservation) {
        return this.ajax("PUT", `/reservations/${id}`, reservation);
    },
    delete(id) {
        return this.ajax("DELETE", `/reservations/${id}`);
    }
});
