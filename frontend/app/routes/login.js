import Route from "@ember/routing/route";
import { inject as service } from "@ember/service";

export default Route.extend({
  session: service(),

  beforeModel() {
    // when switching pages, session stays in storage
    if (this.get("session.isAuthenticated")) {
      var previousTransition = this.get("previousTransition");
      if (previousTransition) {
        this.set("previousTransition", null);
        previousTransition.retry();
      } else {
        this.transitionTo("/");
      }
    }
  }
});
