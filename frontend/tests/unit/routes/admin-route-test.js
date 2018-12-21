import { module, test } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Route | admin-route', function(hooks) {
  setupTest(hooks);

  test('it exists', function(assert) {
    let route = this.owner.lookup('route:admin-route');
    assert.ok(route);
  });
});
