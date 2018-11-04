import BaseHttpService from "./base-http-service";

export default BaseHttpService.extend({
  getAllCitiesForCountry(country) {
    return this.ajax("GET", `/locations/${country}`);
  },

  getAllCountries() {
    return this.ajax("GET", `/locations/distinct/countries`);
  },
  getTopLocations(size) {
    return this.ajax("GET",`/locations/top/?size=${size}`)
  }
});
