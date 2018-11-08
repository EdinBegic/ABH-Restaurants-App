import BaseRoute from "./base-route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";
import CONSTANTS from "../constants";
import { set } from "@ember/object";
export default BaseRoute.extend({
  _restaurantService: service("restaurant-service"),
  _locationService: service("location-service"),
  _reviewService: service("review-service"),
  _cousineService: service("cousine-service"),
  session: service(),

  model() {
    return hash({
      restaurants: this.get("_restaurantService")
        .filter(null, null, 1, CONSTANTS.POPULAR_RESTAURANTS_SIZE)
        .then(response => {
          return response.data;
        }),
      topLocations: this.get("_locationService").getTopLocations(
        CONSTANTS.TOP_LOCATIONS_SIZE
      ),
      cousines: this.get("_cousineService").findAll()
    });
  },
  createPageArray(num, pageNumber) {
    let pageArray = new Array(num);
    for (let i = 0; i < num; i++) {
      let page = new Array(1);
      page.number = i + 1;
      if (i + 1 == pageNumber) {
        page.activeStatus = true;
        this.controller.set("activePage", page);
      } else {
        page.activeStatus = false;
      }
      pageArray[i] = page;
    }
    return pageArray;
  },

  actions: {
    formatPageNumbering(response, pageNumber) {
      let numOfPages = Math.ceil(
        response.available / CONSTANTS.POPULAR_RESTAURANTS_SIZE
      );
      this.controller.set(
        "numOfPages",
        this.createPageArray(numOfPages, pageNumber)
      );
      console.log(this.controller.get("numOfPages"));
      let margin = 480 - numOfPages * 15;
      margin = margin + "px";
      this.controller.set("pageMargin", margin);
    },

    populateRatingAndSize() {
      let restaurants = this.controller.get("model.restaurants");
      for (let i = 0; i < restaurants.length; i++) {
        let restaurant = this.controller.get("model.restaurants").objectAt(i);
        this.get("_reviewService")
          .avgRatingForRestaurant(restaurants[i].id)
          .then(response => {
            set(restaurant, "avgRating", response);
          });
        this.get("_reviewService")
          .numOfReviewsForRestaurant(restaurants[i].id)
          .then(response => {
            set(restaurant, "numOfReviews", response);
          });
      }
    },

    didTransition() {
      this.send("populateRatingAndSize");
      this.get("_restaurantService")
        .filter(null, null, 1, CONSTANTS.POPULAR_RESTAURANTS_SIZE)
        .then(response => {
          this.send("formatPageNumbering", response, 1);
        });
    }
  }
});
