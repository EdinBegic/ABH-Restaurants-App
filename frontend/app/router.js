import EmberRouter from '@ember/routing/router';
import config from './config/environment';

const Router = EmberRouter.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('login');
  this.route('sign-up');
  this.route('home');
  this.route('restaurant', {path: '/:id/restaurant'});
  this.route('restaurant-list');
  this.route('complete-reservation', {path: '/:id1/:id2/complete-reservation'});
  this.route('reservation-history');
});

export default Router;
