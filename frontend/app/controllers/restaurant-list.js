import Controller from "@ember/controller";
import { inject as service } from "@ember/service";

export default Controller.extend({
    _reviewService: service("review-service"),
    _swalService: service("swal-service"),
    _restaurantService: service("restaurant-service"),

    actions:{
        myFunction(){
            document.getElementById("myDropdown").classList.toggle("show-drop");
        }
    }
});