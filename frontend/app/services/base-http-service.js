import $ from "jquery";

export default Ember.Service.extend({
  ajax(method, url, data, token) {
    return $.ajax({
      url: url,
      method: method,
      data: data ? JSON.stringify(data) : null,
      contentType: "application/json",
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Authorization", token);
      }
    });
  }
});
