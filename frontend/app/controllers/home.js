import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
export default Controller.extend({
    router: service('-routing'),
    _restaurantService: service("restaurant-service"),
    showAutoComplete: false,
    selectedRestaurant: false,
    query:'',
    selectedRestaurantId:null,
    suggestions:[],

    search() {
        let q = this.get('query');
        console.log(q);
        if(q != ''){
            this.get('_restaurantService').filter('q',q,1,5).then(response => {
                console.log("SELECT:");
                console.log(this.get('selectedRestaurant'));
                console.log("AUTO:");
                console.log(this.get("showAutoComplete"));
                this.set('suggestions',response.data);
                this.set('showAutoComplete',true);
                this.set('selectedRestaurant', false);
               
            })
        }
        else{
            this.set('suggestions',[]);
        }
    },

    changedQuery: function() {
        Ember.run.debounce(this, this.search, 500);
    }.observes('query'),
    actions: {
        reserveNow(restaurantId) {
            this.get('router').transitionTo('restaurant',[restaurantId]);
        },

        setRestaurant(restaurant) {
            //for now while reservation module is not implemented
            this.set('query',restaurant.name + ', '+ restaurant.location.city);
            this.set('selectedRestaurantId', restaurant.id);
            this.set('showAutoComplete', false);
            this.set('selectedRestaurant', true);
        }


    }
});
