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
  },
  filter(paramName, value, pageNumber, pageSize) {
    return this.ajax(
      "GET",
      `/locations/filter/?${paramName}=${value}&pageSize=${pageSize}&pageNumber=${pageNumber}`
    );
  },
  filterMultipleParams(params, pageNumber, pageSize) {
    return this.ajax(
      "GET",
      `/locations/filter/?${params}pageSize=${pageSize}&pageNumber=${pageNumber}`
    );
  },
  getLocation(id) {
    return this.ajax("GET", `/locations/${id}`);
  },
  updateLocation(id,location) {
    return this.ajax("PUT", `/locations/${id}`, location);
  },
  deleteLocation(id) {
    return this.ajax("DELETE", `/locations/${id}`);
  },


});
