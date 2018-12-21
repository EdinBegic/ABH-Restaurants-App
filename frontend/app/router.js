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

  this.route('admin', function() {
    this.route('users', function() {
      this.route('new');
      this.route('edit', {path: '/:id/edit'});
    });
    this.route('restaurants', function() {
      this.route('new', function() {});
      this.route('edit', {path: '/:id/edit'});
    });
    this.route('locations', function() {
      this.route('new', function() {});
      this.route('edit', {path: '/:id/edit'});
    });
  });
  this.route('admin-route');
});

export default Router;
