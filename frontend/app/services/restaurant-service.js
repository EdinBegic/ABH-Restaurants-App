import BaseHttpService from './base-http-service';

export default BaseHttpService.extend({
    getAllRestaurants(){
        return this.ajax("GET","/restaurants");
    },
})