import Ember from "ember";
import { inject as service } from "@ember/service";
import { set } from "@ember/object";
import BaseController from "./base-controller";

export default BaseController.extend({
  session: service(),
});
