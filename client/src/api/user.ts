import { AxiosInstance } from "axios";
import { http } from "./index";

export interface loginDto {
  userId: string;
  userPassword: string;
}

export interface signupDto {
  userId: string;
  userPassword: string;
  userName: string;
}

const login = (userInfo: loginDto): Promise<AxiosInstance> => {
  return http.post("/login", userInfo);
};

const signup = (userInfo: signupDto): Promise<AxiosInstance> => {
  return http.post("/join", userInfo);
};

const logout = (): Promise<AxiosInstance> => {
  return http.post("/logout");
};

export { login, signup, logout };
