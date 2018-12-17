import Ember from "ember";
import { inject as service } from "@ember/service";
import moment from "moment";
import CONSTANTS from "../constants";
import BaseController from "./base-controller";

export default BaseController.extend({
  router: service("-routing"),
  currentRating: 0,
  _reviewService: service("review-service"),
  _swalService: service("swal-service"),
  _menuItemService: service("menu-item-service"),
  _reservationService: service("reservation-service"),
  session: service(),
  tableSizes: [2, 3, 4, 6, 8, 10],
  sittingPlaces: 2,
  selectedDay: moment().format("YYYY-MM-DD"),
  presentedDay: moment().format("MMMM DD, YYYY"),
  selectedTime: null,
  presentedTime: null,
  suggestedReservations: null,
  availableTables: null,
  suggestedDates: null,
  suggestions: null,
  showSuggestions: false,
  hasReviewed: false,
  notifications: service('toast'),
  actions: {
    review(user, restId) {
      let notifications = this.get('notifications');
      this.set("model.review.user", user);
      this.set("model.review.restaurant", this.get("model.restaurant"));
      this.get("_reviewService")
        .reviewRestaurant(this.get("model.review"))
        .then(response => {
          let newReviewCount = this.get("model.numOfReviews") + 1;
          this.set("model.numOfReviews", newReviewCount);
          this.set("currentRating", 0);
          this.set("rateModal", false);
          this.get("_reviewService")
            .avgRatingForRestaurant(restId)
            .then(response => {
              this.set("model.avgRating", response);
            });
          this.set("hasReviewed", true);
          //this.get("_swalService").success("Successfuly created review");
          notifications.success("Successfuly created review","", {positionClass: 'toast-top-center'});
        })
        .catch(error => {
        //  this.get("_swalService").error(
        //    "An error ocured when saving your review. Please try again."
        //  );
        notifications.error("An error ocured when saving your review. Please try again.", "", {positionClass: 'toast-top-center'});
        });
    },
    saveRating(rating) {
      this.set("currentRating", rating);
      this.set("model.review.mark", rating);
    },
    resetModal(modal) {
      this.set("currentRating", 0);
      this.set("model.review.comment", "");
    },
    listItems(menuId) {
      this.get("_menuItemService")
        .getItemsByMenu(menuId, 1, 0)
        .then(response => {
          this.set("model.menuItems", response);
        });
    },
    changeTime(timeString, hours, minutes) {
      if (this.get("showSuggestions")) {
        this.set("showSuggestions", false);
        document.getElementById("suggested-info").classList.remove("show-info");
      }
      this.set("selectedTime", hours + ":" + minutes + ":00");
      this.set("presentedTime", hours + ":" + minutes);
    },
    changeDay(value) {
      if (this.get("showSuggestions")) {
        this.set("showSuggestions", false);
        document.getElementById("suggested-info").classList.remove("show-info");
      }
      this.set("presentedDay", moment(value).format("MMMM DD, YYYY"));
      this.set("selectedDay", moment(value).format("YYYY-MM-DD"));
    },
    changeSittingPlaces(size) {
      if (this.get("showSuggestions")) {
        this.set("showSuggestions", false);
        document.getElementById("suggested-info").classList.remove("show-info");
      }
      this.set("sittingPlaces", size);
    },
    reserve() {
      // the reservation is not confirmed
      let reservation = this.get("model.reservation");
      reservation.confirmed = false;
      reservation.userId = this.get("session.data.authenticated.user.id");
      reservation.sittingPlaces = this.get("sittingPlaces");
      reservation.startDate = this.get("selectedDay");
      reservation.startTime = this.get("selectedTime");
      this.set("model.reservation", reservation);
      let notifications = this.get('notifications');
      this.get("_reservationService")
        .create(reservation)
        .then(response => {
          this.get("router").transitionTo("complete-reservation", [
            response[0], response[1]
          ]);
        })
        .catch(errorResponse => {
          //TODO find a better way of handling exceptions
          if(errorResponse.responseText == "Requested reservation time already passed") {
            notifications.error(errorResponse.responseText, "",  {positionClass: 'toast-top-center'});
          }
          else {
            this.suggestedReservations = this.get("_reservationService")
            .getSuggestedTimes(reservation, CONSTANTS.SUGGESTED_DATES_SIZE)
            .then(response => {
              this.set("availableTables", response.availableTables);

              let suggestions = [];
              response.suggestedDates.forEach(element => {
                suggestions.push(moment(element).format("HH:mm"));
              });
              this.set("suggestions", suggestions);
              this.set("suggestedDates", response.suggestedDates);
              document.getElementById("suggested-info")
                .classList.add("show-info");
              this.set("showSuggestions", true);
            })
            .catch(error => {
              notifications.error("All tables are already reserved in that time slot", "",
                {positionClass: 'toast-top-center'});
            });
          }

        });
    },
    selectSuggestedTime(index) {
      let suggestedTime = this.get("suggestedDates")[index];
      this.set("selectedDay", moment(suggestedTime).format("YYYY-MM-DD"));
      this.set("selectedTime", moment(suggestedTime).format("HH:mm:ss"));
      this.send("reserve");
    },
  }
});
