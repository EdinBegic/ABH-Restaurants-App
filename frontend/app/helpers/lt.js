import { helper } from "@ember/component/helper";

export function lt([value1, value2]) {
  return value1 < value2;
}

export default helper(lt);
