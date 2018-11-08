import { helper } from "@ember/component/helper";

export function abs([value1]) {
  return Math.abs(value1);
}

export default helper(abs);
