import AdminRoute from "../../admin-route";
import {inject as service} from "@ember/service";
import {hash} from 'rsvp';
export default AdminRoute.extend({
  _restaurantService: service("restaurant-service"),
  model() {
    return this.get('_restaurantService').filter(null, null, 1, 9);
  },
  createPageArray(num, pageNumber) {
    let pageArray = new Array(num);
    for (let i = 0; i < num; i++) {
      let page = new Array(1);
      page.number = i + 1;
      if (i + 1 == pageNumber) {
        page.activeStatus = true;
        console.log(page);
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
        response.available / 9
      );
      this.controller.set(
        "numOfPages",
        this.createPageArray(numOfPages, pageNumber)
      );
      let margin = 650 - numOfPages * 15;
      margin = margin + "px";
      this.controller.set("pageMargin", margin);
    },
    didTransition() {
      this.get("_restaurantService")
        .filter(null, null, 1, 9)
        .then(response => {
          this.send("formatPageNumbering", response, 1);
        });
    }
  }

});
