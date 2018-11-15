import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getAllCitiesForCountry(id) {
    return this.ajax("GET", `/locations/country/${id}`);
  },

  getAllCountries() {
    return this.ajax("GET", `/locations/distinct/countries`);
  },
  getTopLocations(size) {
    return this.ajax("GET", `/locations/top/?size=${size}`);
  }
});
