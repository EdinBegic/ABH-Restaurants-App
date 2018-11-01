import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";
import CONSTANTS from '../constants';

export default Route.extend({
    _restaurantService: service('restaurant-service'),
    _locationService: service('location-service'),
    session: service(),

    model() {
        return hash({
            restaurants: this.get('_restaurantService').getPopularRestaurants(CONSTANTS.POPULAR_RESTAURANTS_SIZE),
            locations: this.get("_locationService").getAllLocations(),
            topLocations: this.get("_locationService").getTopLocations(CONSTANTS.TOP_LOCATIONS_SIZE),
            avgRatings: null,
            numOfReviews: null,
        });
    },
    actions:{
        didTransition(){
            let restaurants = this.controller.get('model.restaurants');
            for(let i = 0; i < restaurants.length; i++) {
                this.get("_restaurantService").getAvgRating(restaurants[i].id).then(response =>{
                    restaurants.avgRating=response;
                })
                this.get("_restaurantService").getReviewSize(restaurants[i].id).then(response =>{
                    restaurants.numOfReviews=response;
                });
            }
        }
}
    
});
