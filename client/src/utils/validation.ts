const userIdRules = [
  (v: string) => !!v || "아이디를 작성해주세요!",
  (v: string) =>
    (v && v.length <= 10) || "아이디를 10글자 이내로 작성해주세요!",
  (v: string) =>
    /^[a-zA-Z0-9]*$/.test(v) || "아이디는 영어와 숫자만 가능합니다!",
];

const passwordRules = [
  (v: string) => !!v || "비밀번호를 작성해주세요!",
  (v: string) =>
    (v && v.length <= 10) || "비밀번호는 20자 이내로 작성해주세요!",
];

const usernameRules = [
  (v: string) => !!v || "이름을 작성해주세요!",
  (v: string) => (v && v.length <= 10) || "이름은 10글자 이내로 작성해주세요!",
  (v: string) => /^[ㄱ-ㅎ|가-힣|]*$/.test(v) || "이름은 한글만 가능합니다!",
];

export { userIdRules, passwordRules, usernameRules };
