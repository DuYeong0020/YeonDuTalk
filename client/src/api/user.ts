import { AxiosInstance } from "axios";
import { http } from "./index";

interface loginDto {
  userId: string;
  password: string;
}

interface signinDto {
  userId: string;
  password: string;
  username: string;
}

const login = (userInfo: loginDto): Promise<AxiosInstance> => {
  return http.post("/login", userInfo);
};

const signin = (userInfo: signinDto): Promise<AxiosInstance> => {
  return http.post("/signin", userInfo);
};

const logout = (): Promise<AxiosInstance> => {
  return http.post("logout");
};

export { login, signin, logout };
