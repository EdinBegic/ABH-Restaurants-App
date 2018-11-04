import Route from '@ember/routing/route';
import { inject as service } from "@ember/service";
import { hash } from "rsvp";
import CONSTANTS from '../constants';

export default Route.extend({
    _restaurantService: service("restaurant-service"),
    _locationService: service('location-service'),
    _reviewService: service("review-service"),
    session: service(),

    model() {
        return hash({
            restaurants: this.get('_restaurantService').getPopularRestaurants(CONSTANTS.POPULAR_RESTAURANTS_SIZE),
            topLocations: this.get("_locationService").getTopLocations(CONSTANTS.TOP_LOCATIONS_SIZE),
            avgRatings: null,
            numOfReviews: null,
        });
    },

});
