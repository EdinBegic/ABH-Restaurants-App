import Component from '@ember/component';

export default Component.extend({
    numOfTimes: Ember.computed('times', function() {
        const times = parseInt(this.get('times'));
        return new Array(times);
    })
});
