import Controller from '@ember/controller';
import {
  inject as service
} from "@ember/service";
import {
  isArray
} from "@ember/array";
import {
  set
} from "@ember/object";
import EmberObject from "@ember/object";

export default Controller.extend({
  searchParameters: "",
  _locationService: service("location-service"),
  _restaurantService: service("restaurant-service"),
  _swalService: service("swal-service"),
  notifications: service("toast"),
  pageSize: 9,
  pageNumber: 1,
  numOfPages: [],
  country: "",
  city: "",
  pageMargin: "",
  activePage: EmberObject.create({
    number: 1,
    activeStatus: true
  }),
  addParameter(paramName, paramValue) {
    let searchParam = this.get("searchParameters");
    searchParam += paramName;
    searchParam += "=";
    if (isArray(paramValue)) {
      for (let i = 0; i < paramValue.length; i++) {
        searchParam += paramValue[i] + ","; // comma on last value won't make a difference
      }
    } else {
      searchParam += paramValue;
    }
    searchParam += "&";
    this.set("searchParameters", searchParam);
  },
  filter() {
    this.send("search", 1);
  },
  changedQuery: function() {
          Ember.run.debounce(this, this.filter, 500);
        }.observes("country","city"),
  actions: {
    setActivePage(page) {
      this.send("search", page.number);
      window.scrollTo(0, 0);
    },
    nextPage(value) {
      console.log(value);
      let activePage = this.get("activePage");
      let numOfPages = this.get("numOfPages");
      if (
        (activePage.number == 1 && value == -1) ||
        (activePage.number == numOfPages.length && value == 1)
      ) {
        return;
      }
      set(activePage, "number", activePage.number + value);
      this.send("search", activePage.number);
    },

    updateLocation(location) {
      console.log(location.id);
      this.transitionToRoute("admin.locations.edit",location.id);
    },

    deleteLocation(location) {
      this.get("_swalService").question("Are you sure you want to delete this location (All restaurants in this location will be deleted)?", confirm=>{
            this.get("_locationService").deleteLocation(location.id)
              .then(deleteResponse => {
                this.get("notifications").success("Successfully deleted location");
                // update view
                let locations = this.get('model.data');
                locations.removeObject(location);
                this.set("model.data",locations);
            })
            .catch(errorResponse => {
              this.get("notifications").error("There was an error in the process of deleting location");
            });
        });
    },

    search: function(pageNumber = 1) {
      this.addParameter("country", this.get("country"));
      this.addParameter("city", this.get("city"));
      let searchParams = this.get("searchParameters");
      this.get("_locationService")
        .filterMultipleParams(searchParams, pageNumber, this.get("pageSize"))
        .then(response => {
          this.set("model.data", response.data);
          this.send("formatPageNumbering", response, pageNumber);
        });
      this.set("searchParameters", "");
    },

}
});
