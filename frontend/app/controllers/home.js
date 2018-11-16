import BaseController from "./base-controller";
import moment from 'moment';
import { inject as service } from "@ember/service";
export default BaseController.extend({
  router: service("-routing"),
  _restaurantService: service("restaurant-service"),
  _reservationService: service("reservation-service"),
  showAutoComplete: false,
  selectedRestaurant: false,
  query: "",
  selectedRestaurantId: null,
  suggestions: [],
  sittingPlaces: 2,
  tableSizes: [2,3,4,6,8,10],
  selectedDay: moment().format("YYYY-MM-DD"),
  presentedDay: moment().format("MMMM DD, YYYY"),
  selectedTime: moment().format("HH:mm:ss"),
  presentedTime: moment().format("HH:mm"),

  search() {
    let q = this.get("query");
    if (q != "") {
      this.get("_restaurantService")
        .filter("q", q, 1, 5)
        .then(response => {
          this.set("suggestions", response.data);
          this.set("showAutoComplete", true);
          this.set("selectedRestaurant", false);
        });
    } else {
      this.set("suggestions", []);
    }
  },

  changedQuery: function() {
    Ember.run.debounce(this, this.search, 500);
  }.observes("query"),
  actions: {
    reserveNow(restaurantId) {
      this.get("router").transitionTo("restaurant", [restaurantId]);
    },

    setRestaurant(restaurant) {
      //for now while reservation module is not implemented
      this.set("query", restaurant.name + ", " + restaurant.location.city);
      this.set("selectedRestaurantId", restaurant.id);
      this.set("showAutoComplete", false);
      this.set("selectedRestaurant", true);
    },

    changeTime(timeString, hours, minutes) {
      this.set("selectedTime", hours + ":" + minutes + ":00");
      this.set("presentedTime", hours + ":" + minutes);
    },
    changeDay(value) {
      this.set("presentedDay", moment(value).utc(true).format("MMMM DD, YYYY"));
      this.set("selectedDay", moment(value).utc(true).format("YYYY-MM-DD"));
    },
    changeSittingPlaces(size) {
      this.set("sittingPlaces", size);
    },

    findTable() {
      let reservation = this.get("_reservationService")
      .createReservation(this.get('selectedRestaurantId'));
      reservation.confirmed = false;
      reservation.userId = this.get("session.data.authenticated.user.id");
      reservation.sittingPlaces = this.get("sittingPlaces");
      reservation.startDate = this.get("selectedDay");
      reservation.startTime = this.get("selectedTime");
      this.get("_reservationService").create(reservation)
      .then(response => {
        this.get("router").transitionTo("complete-reservation", [response.id]);
      })
      .catch(error => {
        let suggestion = {};
        suggestion.name=error.responseText;
        this.set("suggestions", [suggestion]);
        console.log(this.get("suggestions"));
        this.set("showAutoComplete", true);
      })

    }

  }

  
});
