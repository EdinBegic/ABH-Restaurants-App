import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";
import moment from 'moment';

export default BaseRoute.extend({
  _restaurantService: service("restaurant-service"),
  _reviewService: service("review-service"),
  _menuService: service("menu-service"),
  _menuItemService: service("menu-item-service"),
  _reservationService: service("reservation-service"),
  session: service(),
  startDate: moment().tz("Europe/Berlin").format('YYYY-MM-DD'),
  startTime: "00:00:00",
  finishTime: "23:59:00",

  resetController(controller, isExiting, transition) {
    if(isExiting && transition.targetName !== 'error') {    
      controller.set('slectedDay', moment().format('YYYY-MM-DD'));
      controller.set('presentedDay', moment().format('MMMM DD, YYYY'));
      controller.set('selectedTime', moment().format('HH:mm:ss'));
      controller.set('presentedTime', moment().format('HH:mm'));
      controller.set('suggestedReservations', null);
      controller.set('availableTables', null);
      controller.set('suggestedDates', null);
      controller.set('suggestions', null);

    }
  },
  model(params) {
    return hash({
      restaurant: this.get("_restaurantService").getRestaurantById(params.id),
      review: this.get("_reviewService").createReview(),
      numOfReviews: this.get("_reviewService").numOfReviewsForRestaurant(
        params.id
      ),
      avgRating: this.get("_reviewService").avgRatingForRestaurant(params.id),
      menus: this.get("_menuService").getMenusByRestaurant(params.id, 0, 1),
      menuItems: null,
      bookedCounter: this.get("_reservationService").
          getNumOfReservationsForPeriod(this.startDate, this.startTime, this.startDate, this.finishTime, params.id),
      reservation: this.get("_reservationService").createReservation(params.id),
    });
  },
  actions: {
    didTransition() {
      let menus = this.controller.get("model.menus");
      let items = this.get("_menuItemService")
        .getItemsByMenu(menus[0].id,1,0)
        .then(response => {
          this.controller.set("model.menuItems", response);
        });
    }
  }
});
