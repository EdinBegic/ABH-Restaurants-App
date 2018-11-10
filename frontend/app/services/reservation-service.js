import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
    create(reservation) {
        return this.ajax("POST", `/reservations`, reservation);
    },
    getSuggestedTimes(reservation, numOfDates) {
        return this.ajax("GET", `/reservations/suggested-times?numOfDates=${numOfDates}`, reservation);
    },
    getReservationHistory(userId, pageNumber, pageSize) {
        return this.ajax("GET", `/reservations/history?userId=${userId}
        &pageNumber=${pageNumber}&pageSize=${pageSize}`);
    },
    getNumOfReservationsForPeriod(startDate, startTime, finishDate, finishTime) {
        return this.ajax("GET", `/reservations/size-for-period?startDate=${startDate}
        &startTime=${startTime}&finishDate=${finishDate}&finishTime=${finishTime}`);
    },
});
