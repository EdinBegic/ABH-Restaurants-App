import Controller from "@ember/controller";

export default Controller.extend({
    router: Ember.inject.service('-routing'),
    actions: {
        reserveNow(restaurantId) {
            this.get('router').transitionTo('restaurant',[restaurantId]);
        },
    }
});
