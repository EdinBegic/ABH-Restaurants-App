import EmberObject from "@ember/object";

export default EmberObject.extend({
  modelProperties: [],

  getModelProperties() {
    return this.get("modelProperties");
  },

  serialize() {
    var obj = {};
    var _modelProperties = this.getModelProperties();
    for (var property of _modelProperties) {
      obj[property] = this.get(property);
    }
    return JSON.stringify(obj);
  },
  deserialize() {}
});
