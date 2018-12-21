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
  _userService: service("user-service"),
  _swalService: service("swal-service"),
  notifications: service("toast"),
  pageSize: 9,
  pageNumber: 1,
  numOfPages: [],
  firstName: "",
  email: "",
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
        }.observes("firstName","email"),
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

    updateUser(user) {
      this.transitionToRoute("admin.users.edit",user.id);
    },

    deleteUser(user) {
      this.get("_swalService").question("Are you sure you want to delete this user?", confirm=>{
          this.get("_userService").deleteUser(user.id)
            .then(deleteResponse => {
              this.get("notifications").success("Successfully deleted user");
              // update view
              let users = this.get('model.data');
              users.removeObject(user);
              this.set("model.data",users);
          })
          .catch(errorResponse => {
            this.get("notifications").error("There was an error in the process of deleting user");
          })
        });
    },

    search: function(pageNumber = 1) {
      this.addParameter("firstName", this.get("firstName"));
      console.log(this.get("firstName"));
      this.addParameter("email", this.get("email"));
      let searchParams = this.get("searchParameters");
      this.get("_userService")
        .filterMultipleParams(searchParams, pageNumber, this.get("pageSize"))
        .then(response => {
          console.log(response.data);
          this.set("model.data", response.data);
          this.send("formatPageNumbering", response, pageNumber);
        });
      this.set("searchParameters", "");
    },

}
});
