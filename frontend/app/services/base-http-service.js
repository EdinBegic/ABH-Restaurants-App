import $ from "jquery";
import Service from "@ember/service";
import config from 'frontend/config/environment';
export default Service.extend({
  ajax(method, url, data, token) {
    return $.ajax({
      url: `${config.apiHost}/${url}`,
      method: method,
      data: data ? JSON.stringify(data) : null,
      contentType: "application/json",
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Authorization", token);
      }
    });
  }
});
