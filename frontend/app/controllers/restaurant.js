import Ember from "ember";
import { inject as service } from "@ember/service";
import BaseController from "./base-controller";

export default BaseController.extend({
  currentRating: 0,
  _reviewService: service("review-service"),
  _swalService: service("swal-service"),
  _menuItemService: service("menu-item-service"),
  session: service(),
  actions: {
    review(user, restId) {
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
          this.get("_swalService").success("Successfuly created review");
        })
        .catch(error => {
          this.get("_swalService").error(
            "An error ocured when saving your review. Please try again."
          );
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
        .getItemsByMenu(menuId)
        .then(response => {
          this.set("model.menuItems", response);
        });
    }
  }
});
