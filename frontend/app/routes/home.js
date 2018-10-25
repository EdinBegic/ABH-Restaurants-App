import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";
import { hash } from "rsvp";

export default Route.extend({
    _restaurantService: service('restaurant-service'),
    _locationService: service('location-service'),
    session: service(),

    model() {
        return hash({
            restaurants: this.get('_restaurantService').getAllRestaurants(),
            locations: this.get("_locationService").getAllLocations(),
        });
    },
    
});
