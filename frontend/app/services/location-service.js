import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getAllLocations() {
    return this.ajax("GET", "/locations");
  },

  getAllCitiesForCountry(country) {
    return this.ajax("GET", `/locations/${country}`);
  },

  getAllCountries() {
    return this.ajax("GET", `/locations/distinct/countries`);
  }
});
