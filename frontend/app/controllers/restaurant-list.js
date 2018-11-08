import Controller from "@ember/controller";
import { inject as service } from "@ember/service";
import { isArray } from "@ember/array";
import CONSTANTS from "../constants";
import { set } from "@ember/object";
import EmberObject from "@ember/object";
import ClickElsewhereMixin from "../mixins/click-elsewhere";

export default Controller.extend(ClickElsewhereMixin, {
  _reviewService: service("review-service"),
  _swalService: service("swal-service"),
  _restaurantService: service("restaurant-service"),
  router: service("-routing"),
  name: "",
  cousineFilters: [],
  range: "",
  rating: "",
  searchParameters: "",
  sortAsc: false,
  checkedKey: true,
  sortKey: "",
  pageSize: CONSTANTS.POPULAR_RESTAURANTS_SIZE,
  pageNumber: 1,
  numOfPages: [],
  pageMargin: "",
  activePage: EmberObject.create({
    number: 1,
    activeStatus: true
  }),
/*
  $(document).click(function (e) {
    e.stopPropagation();
    var container = $(".sort-dropdown");
  
    //check if the clicked area is dropDown or not
    if (container.has(e.target).length === 0) {
        $('#mySortDropDown').hide();
    } else{
      $('#mySortDropDown').show();
  
    }
  }),
*/
  
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

  actions: {
    hideDropDown(element) {
      document.getElementById(element).classList.remove("show-drop");
    },
    showDropDown(element) {
      document.getElementById(element).classList.add("show-drop");
    },
    reserveNow(restaurantId) {
      this.get("router").transitionTo("restaurant", [restaurantId]);
    },
    changeCousineStatus(cousine) {
      let cousineFilters = this.get("cousineFilters");
      //let cousineIndex = this.get('model.cousines').indexOf(cousine);
      if (cousine.activeStatus) {
        // this.set("cousine.activeStatus", false);
        set(cousine, "activeStatus", false);
        // removing cousine name from filter
        let indexOfElement = cousineFilters.indexOf(cousine.name);
        if (indexOfElement > -1) {
          cousineFilters.splice(indexOfElement, 1);
        }
      } else {
        //this.set("cousine.activeStatus", true);
        set(cousine, "activeStatus", true);
        cousineFilters.push(cousine.name);
      }
      this.set("cousineFilters", cousineFilters);
    },

    setRange(range) {
      this.set("range", range);
    },
    setRating(rating) {
      this.set("rating", rating);
    },
    changeSortKey(checkedValue, sortKeyValue){
      if(checkedValue != this.get('checkedKey') ) {
        this.set('checkedKey', !this.get('checkedKey'));
      }
      this.set('sortKey', sortKeyValue);
    },
    resetFilter(){
      this.set("cousineFilters",[]);
      let cousines = this.get('model.cousines');
      for(let i = 0; i < cousines.length; i++) {
        set(cousines[i], "activeStatus", false);
      }
      this.set("name", "");
      this.set("range", "");
      this.set("rating", "");
    },
    setActivePage(page) {
      this.send("search", page.number);
    },

    nextPage(value) {
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
    search(pageNumber = 1) {
      this.addParameter("cousines", this.get("cousineFilters"));
      this.addParameter("name", this.get("name"));
      this.addParameter("priceRange", this.get("range"));
      this.addParameter("avgRating", this.get("rating"));
      this.addParameter("sortAsc", this.get("sortAsc"));
      this.addParameter("sortKey", this.get("sortKey"));
      let searchParams = this.get("searchParameters");
      this.get("_restaurantService")
        .filterMultipleParams(searchParams, pageNumber, this.get("pageSize"))
        .then(response => {
          this.set("model.restaurants", response.data);
          this.send("populateRatingAndSize");
          this.send("formatPageNumbering", response, pageNumber);
        });
      this.set("searchParameters", "");
    }
  }
});
