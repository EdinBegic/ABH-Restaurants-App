import Controller from "@ember/controller";
import {inject as service} from "@ember/service";

export default Controller.extend({

    currentRating: 0,
    _reviewService: service('review-service'),
    session: service(),
    actions:{
        
        review(user){
            this.set('model.review.user',user);
            this.set('model.review.restaurant',this.get('model.restaurant'));
            this.get('_reviewService').reviewRestaurant(this.get('model.review')).then( response => {
                let newReviewCount = this.get('model.numOfReviews') + 1;
                this.set('model.numOfReviews', newReviewCount);
                this.set('currentRating',0);
                })
            .catch( error => {
                console.log("Error: " + error);
            })
        },
        saveRating(rating) {
            this.set('currentRating',rating);
            this.set('model.review.mark',rating);
        },
        resetModal(){
            this.set('currentRating',0);
            this.set('rateModal',false);
            this.set('model.review.comment','');
        }
    }
});
