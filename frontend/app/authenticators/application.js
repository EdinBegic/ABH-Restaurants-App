import Base from "ember-simple-auth/authenticators/base";
import { Promise } from "rsvp";
import { inject as service } from "@ember/service";
import { isEmpty } from "@ember/utils";
export default Base.extend({
  restaurantsHttpService: service("restaurants-http-service"),

  restore(data) {
    return new Promise((resolve, reject) => {
      if (!isEmpty(data.token)) {
        resolve(data);
      } else {
        reject();
      }
    });
  },

  authenticate(credentials, callback) {
    return this.get("restaurantsHttpService").post(
      "login",
      credentials,
      resp => {
        if (callback) {
          callback(resp);
        }

        return resp;
      }
    );
  },

  invalidate(data) {
    return Promise.resolve(data);
  }
});
