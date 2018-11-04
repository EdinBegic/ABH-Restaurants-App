import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";
import CONSTANTS from '../constants';

export default Route.extend({
    _restaurantService: service('restaurant-service'),
    _locationService: service('location-service'),
    _reviewService: service('review-service'),
    session: service(),

    model() {
        return hash({
            restaurants: this.get('_restaurantService').getPopularRestaurants(CONSTANTS.POPULAR_RESTAURANTS_SIZE),
            topLocations: this.get("_locationService").getTopLocations(CONSTANTS.TOP_LOCATIONS_SIZE),
            avgRatings: null,
            numOfReviews: null,
        });
    },
    deactivate: function(){
        this.controller.set('query',null);
        this.controller.set('suggestions',[]);
        this.controller.set('selectedRestaurantId',null);
        this.controller.set('showAutoComplete',false);
    },
    actions:{
        didTransition(){
            let restaurants = this.controller.get('model.restaurants');
            for(let i = 0; i < restaurants.length; i++) {
                this.get("_reviewService").avgRatingForRestaurant(restaurants[i].id).then(response =>{
                    restaurants.avgRating=response;
                })
                this.get("_reviewService").numOfReviewsForRestaurant(restaurants[i].id).then(response =>{
                    restaurants.numOfReviews=response;
                });
            }
        }
}
    
});
